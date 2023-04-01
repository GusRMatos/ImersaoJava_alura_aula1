package exception;

public class HttpClientException extends RuntimeException {

    public HttpClientException(String errorMessage) {
        super(errorMessage);
    }
}

