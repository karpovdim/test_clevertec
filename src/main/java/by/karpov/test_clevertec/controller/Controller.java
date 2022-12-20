package by.karpov.test_clevertec.controller;


import by.karpov.test_clevertec.dto.OrderDto;
import by.karpov.test_clevertec.model.Order;
import by.karpov.test_clevertec.printers.PrintReceipt;
import by.karpov.test_clevertec.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class Controller {

    private final OrderService orderServiceImpl;
    private final PrintReceipt printReceiptToFile;

    @Autowired
    public Controller(OrderService orderServiceImpl, PrintReceipt printReceiptToFile) {
        this.orderServiceImpl = orderServiceImpl;
        this.printReceiptToFile = printReceiptToFile;
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    public void getByParam(@RequestBody(required = false) OrderDto orderDto) {
        OrderDto order = orderServiceImpl.createOrder(orderDto);
        printReceiptToFile.printReceipt(order,orderServiceImpl.getReceipt(order));
    }
}
