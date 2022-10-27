package cat.uvic.teknos.m09.polsane.cryptoutils.exceptions;


public class AlgorithmNotFoundException extends RuntimeException{
    public AlgorithmNotFoundException() {
    }

    public AlgorithmNotFoundException(String message) {
        super(message);
    }

    public AlgorithmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlgorithmNotFoundException(Throwable cause) {
        super(cause);
    }

    public AlgorithmNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
