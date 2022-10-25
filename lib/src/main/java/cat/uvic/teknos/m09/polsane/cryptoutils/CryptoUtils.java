/**
 * This library contains methods related to cryptography and cyber-security.
 * It has a Properties attribute, initialized by a static initializer that
 * reads a cryptoutils.properties file.
 * @author pol.sane@uvic.cat
 * @author pol sañé jové
 */

package cat.uvic.teknos.m09.polsane.cryptoutils;

import cat.uvic.teknos.m09.polsane.cryptoutils.datastructures.DigestResult;
import cat.uvic.teknos.m09.polsane.cryptoutils.exceptions.AlgorithmNotFoundException;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
public class CryptoUtils {
    public static Properties properties;
    static {
        try {
            properties=new Properties();
            properties.load(CryptoUtils.class.getResourceAsStream("/cryptoUtils.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**Generates and returns a hash from a message
     * @param message A byte[] from which we wish to generate a hash
     * @return A DigestResult object that contains the hash (byte[]) generated from the message, and the salt (byte[]) and algorithm (String) that we used to generate it
     */
    public static DigestResult hash(byte[] message)  {
        byte[] salt =null;
        DigestResult digestResult;

        var hashAlgorithm= (String) properties.get("hash.algorithm");
        boolean hashSalt=Boolean.parseBoolean((String) properties.get("hash.salt"));

        var dataBytes = message;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new AlgorithmNotFoundException("The algorithm of cryptoutils.properties does not exist.ck if it's written correctly and if before there is 'hash.algorithm=' and nothing else \n");
        }
        if(hashSalt) {
            salt = getSalt();
            messageDigest.update(salt);
        }
        var digest =messageDigest.digest(message);
        if(hashSalt) {
            digestResult = new DigestResult(digest, salt, hashAlgorithm);
        }else{
            digestResult = new DigestResult(digest, hashAlgorithm);
        }
        return digestResult;
    }
    private static byte[] getSalt(){
        var securerandom= new SecureRandom();
        var salt=new byte[16];
        securerandom.nextBytes(salt);
        return salt;
    }

    /**Gets the static Properties attribute of the class
     * @return A Properties object that configures how the hashes are generated
     */
    public static Properties getProperties() {
        return properties;
    }
}
