package by.karpov.test_clevertec.printers;

import by.karpov.test_clevertec.dto.OrderDto;
import by.karpov.test_clevertec.model.Order;

import java.util.List;

public interface PrintReceipt {
     void printReceipt(OrderDto order, List<String> lineTxt);
}
