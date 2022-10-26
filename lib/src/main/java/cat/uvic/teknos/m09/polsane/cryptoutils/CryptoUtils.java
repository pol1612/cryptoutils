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
import cat.uvic.teknos.m09.polsane.cryptoutils.exceptions.CryptoUtilsPropertiesException;

import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
public class CryptoUtils {
    private static Properties properties;
    private static byte[] symmetricEncriptionIvByteArr;

    static {
        try {
            properties=new Properties();
            properties.load(CryptoUtils.class.getResourceAsStream("/cryptoutils.properties"));
        } catch (IOException e) {
            throw new CryptoUtilsPropertiesException("Unable to load the properties of cryptoutils.properties. Be sure to check if it's in the location '/' and its name is 'cryptoutils.properties'");
        }
    }

    /**Generates and returns a hash from a message
     * @param message A byte[] from which we wish to generate a hash
     * @return A DigestResult object that contains the hash (byte[]) generated from the message, and the salt (byte[]) and algorithm (String) that we used to generate it
     */
    public static DigestResult hash(byte[] message)  {
        byte[] salt =null;
        DigestResult digestResult;

        String hashAlgorithm= (String) properties.get("hash.algorithm");
        boolean hashSalt=Boolean.parseBoolean((String) properties.get("hash.salt"));

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
    public static byte[] encrypt(byte[] plainText, String password){
        var secretKey=getPrivateKeyFromPassword(password);
        var cipherAlgorithm=properties.getProperty("symmetric.cipherAlgorithm");
        try {
            var cipher = Cipher.getInstance(cipherAlgorithm);

            byte[] bytes=new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(bytes);
            symmetricEncriptionIvByteArr=bytes;

            var iv =new IvParameterSpec(symmetricEncriptionIvByteArr);


            cipher.init(Cipher.ENCRYPT_MODE,secretKey,iv);

            var cipherText = cipher.doFinal(plainText);

            return cipherText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }


    }
    public static byte[] decrypt(byte[] cipherText,String password){
        var secretKey=getPrivateKeyFromPassword(password);
        var cipherAlgorithm=properties.getProperty("symmetric.cipherAlgorithm");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(cipherAlgorithm);

            var iv =new IvParameterSpec(symmetricEncriptionIvByteArr);

            cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);

            var plainText=cipher.doFinal(cipherText);

            return plainText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    private static Key getPrivateKeyFromPassword(String password){
        String saltStr=properties.getProperty("symmetric.secretKeySalt");

        byte[] salt= saltStr.getBytes();


        int iterationCount=Integer.parseInt(properties.getProperty("symmetric.iterations"));

        int keyLenght=Integer.parseInt(properties.getProperty("symmetric.keyLenght"));

        String secretKeyFactoryAlgorithm=properties.getProperty("symmetric.secretKeyFactoryAlgorithm");

        String secretKeySpecAlgorithm=properties.getProperty("symmetric.secretKeySpecAlgorithm");


        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLenght);
        SecretKey pbeKey = null;
        try {
            pbeKey = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm).generateSecret(pbeKeySpec);
            return new SecretKeySpec(pbeKey.getEncoded(), secretKeySpecAlgorithm);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
