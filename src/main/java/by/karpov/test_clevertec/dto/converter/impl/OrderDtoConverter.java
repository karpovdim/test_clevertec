package by.karpov.test_clevertec.dto.converter.impl;

import by.karpov.test_clevertec.dto.OrderDto;
import by.karpov.test_clevertec.dto.converter.DtoConverter;
import by.karpov.test_clevertec.exception.NoSuchEntityException;
import by.karpov.test_clevertec.model.Order;
import by.karpov.test_clevertec.model.Product;
import by.karpov.test_clevertec.repositories.DiscountCardRepository;
import by.karpov.test_clevertec.repositories.ProductRepository;
import by.karpov.test_clevertec.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static by.karpov.test_clevertec.constant.Constants.EMPTY_CARD;

@Component("orderRequestConverter")
public class OrderDtoConverter implements DtoConverter<Order, OrderDto> {
    private final ProductRepository productRepository;
    private final DiscountCardRepository discountCardRepository;
    private final Validator validator;

    @Autowired
    public OrderDtoConverter(ProductRepository productRepository, DiscountCardRepository discountCardRepository, Validator validator) {
        this.productRepository = productRepository;
        this.discountCardRepository = discountCardRepository;
        this.validator = validator;
    }


    @Override
    public Order convertToEntity(OrderDto dto) {
        return Order.builder()
                .dateOfPurchase(LocalDateTime.now())
                .products(getProductsList(dto))
                .discount(discountCardRepository.findByCardNumber(dto.getDiscountCardNumber())
                        .orElse(discountCardRepository.findByCardNumber(EMPTY_CARD)
                                .orElseThrow(() -> new NoSuchEntityException("message.discount.card.missing"))))
                .totalPrice(new BigDecimal(100))
                .build();
    }

    @Override
    public OrderDto convertToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .dateOfPurchase(order.getDateOfPurchase())
                .discountCardNumber(order.getDiscount().getCardNumber())
                .ShoppingCart(ordersListToMap(order.getProducts()))
                .build();
    }

    private HashMap<Long, Integer> ordersListToMap(List<Product> products) {
        HashMap<Long, Integer> processingMap = new HashMap<>();
        for (Product product : products) {
            if (processingMap.containsKey(product.getId())) {
                continue;
            } else {
                processingMap.put(product.getId(), Collections.frequency(products, product));
            }
        }
        return processingMap;
    }


    private List<Product> getProductsList(OrderDto dto) {
        HashMap<Long, Integer> shoppingCart = dto.getShoppingCart();
        List<Product> products = new ArrayList<>();
        for (Long idProduct : shoppingCart.keySet()) {
            addProductToList(shoppingCart, products, idProduct);
        }
        return products;
    }

    private void addProductToList(HashMap<Long, Integer> shoppingCart, List<Product> products, Long idProduct) {
        validator.validateValue(idProduct);
        Product byId = productRepository.findById(idProduct).orElseThrow(
                () -> new NoSuchEntityException("message.product.missing"));
        validator.validateValue(shoppingCart.get(idProduct));
        for (int i = 0; i < shoppingCart.get(idProduct); i++) {
            products.add(byId);
        }
    }
}
