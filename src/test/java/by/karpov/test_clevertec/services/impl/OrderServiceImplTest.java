package by.karpov.test_clevertec.services.impl;

import by.karpov.test_clevertec.dto.converter.impl.OrderDtoConverter;
import by.karpov.test_clevertec.model.Order;
import by.karpov.test_clevertec.repositories.OrderRepository;
import by.karpov.test_clevertec.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private static final Long ORDER_ID =1L;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderDtoConverter orderDtoConverter;
    @Mock
    private ProductService productServiceImpl;
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    void findById() {
        Order order = Order.builder().id(ORDER_ID).build();
        Mockito.when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(order));

        var actualResult = orderServiceImpl.findById(ORDER_ID);

        assertTrue(actualResult.isPresent());
//        verify(orderRepository).findById(anyLong());
    }
}