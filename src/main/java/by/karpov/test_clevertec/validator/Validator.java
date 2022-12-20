package by.karpov.test_clevertec.validator;

import by.karpov.test_clevertec.exception.ExceptionResponse;
import by.karpov.test_clevertec.exception.InvalidParametersException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public void validateValue(Object value){
        if(value == null){
            throw new InvalidParametersException("message.validation.fail");
        }
    }
}
