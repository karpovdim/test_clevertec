package by.karpov.test_clevertec.services;

import by.karpov.test_clevertec.dto.OrderDto;
import by.karpov.test_clevertec.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<String> getReceipt(OrderDto orderDto);

    Optional<Order> findById(Long id);
}
