package hello.jdbc.repository.ex;

public class MyDbException extends RuntimeException { // RuntimeException 을 상속받았기 때문에, Unchecked Exception 이다.

    public MyDbException() {
    }

    public MyDbException(String message) {
        super(message);
    }

    public MyDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDbException(Throwable cause) {
        super(cause);
    }

}
