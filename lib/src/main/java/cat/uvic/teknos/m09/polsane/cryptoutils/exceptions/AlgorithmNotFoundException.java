package cat.uvic.teknos.m09.polsane.cryptoutils.exceptions;

/**An exception class that extends RuntimeException and is called when the algorithm used in hash(), set in cryptoutils.properties
 * @author pol
 * @see java.lang.RuntimeException
 */
public class AlgorithmNotFoundException extends RuntimeException{
    /**A constructor without parameters
     */
    public AlgorithmNotFoundException() {
    }

    /**
     * constructor with a message parameter
     * @param message String that is the exception's message
     */
    public AlgorithmNotFoundException(String message) {
        super(message);
    }

    /**
     * constructor with a message and a cause parameter
     * @param message String that is the exception's message
     * @param cause Throwable that is the exeception's cause
     */
    public AlgorithmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**constructor with a cause parameter
     * @param cause Throwable that is the exeception's cause
     */
    public AlgorithmNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * constructor with a message, a cause parameter, a enableSuppresion parameter and a writableSuppression parameter
     * @param message String that is the exception's message
     * @param cause Throwable that is the exeception's cause
     * @param enableSuppression boolean that is enableSuppression
     * @param writableStackTrace boolean that is writableStackTrace
     */
    public AlgorithmNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
