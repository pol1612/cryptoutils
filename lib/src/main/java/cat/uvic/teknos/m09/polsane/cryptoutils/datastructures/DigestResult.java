package cat.uvic.teknos.m09.polsane.cryptoutils.datastructures;

/** A class that contains a hash from that the hash() method of , and the salt and algorithm used to create the gradle
 * @author pol sañé jové
 */
public class DigestResult {
    private byte[] hash;
    private byte[] salt;
    private String algorithm;

    /**Allows to create a DigestResult with hash, salt and algorithm
     * @param hash a byte array that is the hash from the method hash()
     * @param salt a byte array that is the salt used to create the hash from the method hash()
     * @param algorithm a String that is the algorithm used to generate the salt from the method hash()
     */
    public DigestResult(byte[] hash, byte[] salt, String algorithm) {
        this.hash = hash;
        this.salt = salt;
        this.algorithm = algorithm;
    }
    /**Allows to create a DigestResult with hash and algorithm, as this hash was created without salt
     * @param hash a byte array that is the hash from the method hashs
     * @param algorithm a String that is the algorithm used to generate the salt from the method hash()
     */
    public DigestResult(byte[] hash,  String algorithm) {
        this.hash = hash;
        this.salt = null;
        this.algorithm = algorithm;
    }

    /**
     *Allows you to get the hash property from the DigestResult
     * @return a byte array that is the hash property from the DigestResult
     */
    public byte[] getHash() {
        return hash;
    }
    /**
     *Allows you to get the salt property from the DigestResult
     * @return a byte array that is the salt used when creating the hash, if there was no salt used during the creation of the hash, it returns null
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     *Allows you to get the algorithm property from the DigestResult
     * @return a String that is the algorithm (that is the property from the DigestResult) that is used to create the hash in the method hash() p
     */
    public String getAlgorithm() {
        return algorithm;
    }

}