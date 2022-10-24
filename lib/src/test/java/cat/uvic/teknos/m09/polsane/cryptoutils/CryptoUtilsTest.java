/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cat.uvic.teknos.m09.polsane.cryptoutils;

import cat.uvic.teknos.m09.polsane.cryptoutils.CryptoUtils;
import com.google.common.base.Ascii;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilsTest {
    @Test void When_Hash1SameAlgorithmAndNoSaltAsHash2_Expect_Hash1EqualsHash2AsTrue() {
        synchronized (CryptoUtils.class) {
            CryptoUtils.getProperties().setProperty("hash.salt","false");
            var message = "message";
            var digestResult1 = CryptoUtils.hash(message.getBytes());
            var digestResult2=CryptoUtils.hash(message.getBytes());
            assertTrue(Arrays.equals(digestResult1.getHash(),digestResult2.getHash()));
        }
    }
    @Test void When_Hash1SameAlgorithmAsHash2AndHasSaltAndHash2HasNoSalt_Expect_Hash1EqualsHash2AsFalse() {
        synchronized (CryptoUtils.class) {
            CryptoUtils.getProperties().setProperty("hash.salt","true");
            var message = "message";
            var digestResult1 = CryptoUtils.hash(message.getBytes());
            CryptoUtils.getProperties().setProperty("hash.salt","false");
            var digestResult2=CryptoUtils.hash(message.getBytes());
            assertFalse(Arrays.equals(digestResult1.getHash(),digestResult2.getHash()));
        }
    }
}
