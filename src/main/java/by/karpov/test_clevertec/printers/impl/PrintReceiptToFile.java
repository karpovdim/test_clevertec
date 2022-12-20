package by.karpov.test_clevertec.printers.impl;

import by.karpov.test_clevertec.dto.OrderDto;
import by.karpov.test_clevertec.printers.PrintReceipt;
import by.karpov.test_clevertec.services.FilenameService;
import by.karpov.test_clevertec.services.OrderService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static by.karpov.test_clevertec.constant.Constants.*;

@Component

public class PrintReceiptToFile implements PrintReceipt {

    private final FilenameService filenameServiceImpl;
   // private final OrderService orderServiceImpl;

    @Autowired
    public PrintReceiptToFile(FilenameService filenameServiceImpl) {
        this.filenameServiceImpl = filenameServiceImpl;

    }

    public void printReceipt(OrderDto orderDto, List<String> txtLines) {
        writeToFile(filenameServiceImpl.getFilename(ORDER_ID + orderDto.getId().toString() + SPACE + orderDto.getDateOfPurchase().toLocalDate()), txtLines);
    }

    @SneakyThrows
    private void writeToFile(String fileName, List<String> stringsForTxt) {
        Path path = Files.createFile(Paths.get(fileName));
        Files.write(path, stringsForTxt, StandardCharsets.UTF_8);
        System.out.println(FILE_SAVE_AS + fileName + TXT);
    }
}
