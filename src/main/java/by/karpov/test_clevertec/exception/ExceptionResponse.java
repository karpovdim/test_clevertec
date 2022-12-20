package by.karpov.test_clevertec.exception;

public class ExceptionResponse extends Throwable {

    private final String errorMessage;
    private final int errorCode;


    public ExceptionResponse(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
