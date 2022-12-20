package by.karpov.test_clevertec.integration.service;

import by.karpov.test_clevertec.model.Order;
import by.karpov.test_clevertec.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class OrderServiceImplIT {
    public static final long    ID = 10L;
    @Autowired
private OrderService orderServiceImpl;
    @Test
    void findById(){
       var actual = orderServiceImpl.findById(ID);
        var expected = Optional.of(Order.builder().id(ID).build());
        actual.ifPresent(ac-> Assertions.assertEquals(expected,actual));

    }
}
