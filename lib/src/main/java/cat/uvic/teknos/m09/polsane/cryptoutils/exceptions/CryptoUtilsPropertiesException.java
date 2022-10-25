package cat.uvic.teknos.m09.polsane.cryptoutils.exceptions;

public class CryptoUtilsPropertiesException extends RuntimeException{
    public CryptoUtilsPropertiesException() {
    }

    public CryptoUtilsPropertiesException(String message) {
        super(message);
    }

    public CryptoUtilsPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoUtilsPropertiesException(Throwable cause) {
        super(cause);
    }

    public CryptoUtilsPropertiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
