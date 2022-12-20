package by.karpov.test_clevertec.services.impl;

import by.karpov.test_clevertec.constant.Constants;
import by.karpov.test_clevertec.dto.OrderDto;
import by.karpov.test_clevertec.dto.ProductDto;
import by.karpov.test_clevertec.dto.converter.impl.OrderDtoConverter;
import by.karpov.test_clevertec.exception.NoSuchEntityException;
import by.karpov.test_clevertec.model.Order;
import by.karpov.test_clevertec.repositories.OrderRepository;
import by.karpov.test_clevertec.services.OrderService;
import by.karpov.test_clevertec.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static by.karpov.test_clevertec.constant.Constants.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Qualifier("orderRepository")
    private final OrderRepository orderRepository;
    @Qualifier("orderDtoConverter")
    private final OrderDtoConverter orderDtoConverter;
    @Qualifier("productServiceImpl")
    private final ProductService productServiceImpl;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDtoConverter orderDtoConverter, ProductService productServiceImpl) {
        this.orderRepository = orderRepository;
        this.orderDtoConverter = orderDtoConverter;
        this.productServiceImpl = productServiceImpl;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderDtoConverter.convertToEntity(orderDto);
        return orderDtoConverter.convertToDto(orderRepository.save(order));
    }

    @Override
    public List<String> getReceipt(OrderDto orderDto) {
       Order order = orderRepository.findById(orderDto.getId()).orElseThrow(()-> new NoSuchEntityException("message.order.missing"));
        HashMap<Long, Integer> shoppingCart = orderDtoConverter.convertToDto(order).getShoppingCart();
        List<String> lineTxt = new ArrayList<>();
        String dateOrdering = getDateOrdering(order);
        String discountCard = getDiscountCardInformation(order);
        lineTxt.add(TITLE);
        lineTxt.add(dateOrdering);
        lineTxt.add(discountCard);
        lineTxt.add(SEPARATE_LINE);
        lineTxt.add(TITLE_TABLE);
        lineTxt.add(SEPARATE_LINE);
        BigDecimal sumTotalPrice = BIG_DECIMAL;
        sumTotalPrice = getTotalPrice(order, shoppingCart, lineTxt, sumTotalPrice);
        String total = Constants.TOTAL + sumTotalPrice;
        lineTxt.add(SEPARATE_LINE);
        lineTxt.add(total);
        return lineTxt;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    private String getDiscountCardInformation(Order order) {
        if (!order.getDiscount().getCardNumber().equals(EMPTY_CARD)) {
            return String.format(PERCENT_DISCOUNT,
                    order.getDiscount().getCardNumber(),
                    order.getDiscount().getDiscount());
        }
        return DISCOUNT_CARD_DOES_NOT_EXIST;
    }

    private String getDateOrdering(Order order) {
        return DATE_ORDER
                + order.getDateOfPurchase().toLocalDate()
                + TIME_ORDER + order.getDateOfPurchase().toLocalTime().toString().substring(0, 8)
                + NEW_LINE;
    }

    private BigDecimal getTotalPriceByproductWithAllDiscount(Order order, Long productId, Integer qty) {
        BigDecimal totalPrice;
        ProductDto productDto = productServiceImpl.getById(productId);
        totalPrice = productDto.getPrice().multiply(new BigDecimal(qty));
        if (productDto.getStock() && qty >= QTY_FOR_GET_DISCOUNT) {
            totalPrice = totalPrice.multiply(BIG_DECIMAL_DISCOUNT_FOR_STOCK_PRODUCT);
        } else if (order.getDiscount() != null) {
            totalPrice = totalPrice.subtract(totalPrice.multiply(order.getDiscount().getDiscount().divide(DIVISOR)));
        }
        return totalPrice;
    }

    private BigDecimal getTotalPrice(Order order, HashMap<Long, Integer> shoppingCart, List<String> lineTxt, BigDecimal sumTotalPrice) {
        for (Long productId : shoppingCart.keySet()) {
            var product = productServiceImpl.getById(productId);
            Integer qty = shoppingCart.get(productId);
            BigDecimal totalPriceByproductWithAllDiscount = getTotalPriceByproductWithAllDiscount(order, productId, qty);
            sumTotalPrice = sumTotalPrice.add(totalPriceByproductWithAllDiscount);
            String productElementLine = String.format(PRODUCT_ELEMENT_FORMAT, qty, product.getName(), product.getPrice(), totalPriceByproductWithAllDiscount);
            lineTxt.add(productElementLine);
        }
        return sumTotalPrice;
    }
}
