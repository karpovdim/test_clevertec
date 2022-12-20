package by.karpov.test_clevertec.services.impl;

import by.karpov.test_clevertec.services.FilenameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class FilenameServiceImpl implements FilenameService {
    @Override
    public  String getFilename(String fileName) {

        while (Files.exists(Paths.get(fileName))) {
            fileName = fileName + "1";
        }
        return fileName;
    }
}
