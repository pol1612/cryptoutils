package cat.uvic.teknos.m09.polsane.cryptoutils.exceptions;


/**An exception class that extends RuntimeException and is called when the properties attribute of CryptoUtils.java cannot find a cryptoutils.properties in the '/cryptoutils.properties' location
 * @author pol
 * @see java.lang.RuntimeException
 */
public class CryptoUtilsPropertiesException extends RuntimeException{
    /**A constructor without parameters
     */
    public CryptoUtilsPropertiesException() {
    }
    /**
     * constructor with a message parameter
     * @param message String that is the exception's message
     */
    public CryptoUtilsPropertiesException(String message) {
        super(message);
    }
    /**
     * constructor with a message and a cause parameter
     * @param message String that is the exception's message
     * @param cause Throwable that is the exeception's cause
     */
    public CryptoUtilsPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }
    /**constructor with a cause parameter
     * @param cause Throwable that is the exeception's cause
     */
    public CryptoUtilsPropertiesException(Throwable cause) {
        super(cause);
    }
    /**
     * constructor with a message, a cause parameter, a enableSuppresion parameter and a writableSuppression parameter
     * @param message String that is the exception's message
     * @param cause Throwable that is the exeception's cause
     * @param enableSuppression boolean that is enableSuppression
     * @param writableStackTrace boolean that is writableStackTrace
     */
    public CryptoUtilsPropertiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
