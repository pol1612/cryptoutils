package cat.uvic.teknos.m09.polsane.cryptoutils.datastructures;
public class DigestResult {
    private byte[] hash;
    private byte[] salt;
    private String algorithm;

    public DigestResult(byte[] hash, byte[] salt, String algorithm) {
        this.hash = hash;
        this.salt = salt;
        this.algorithm = algorithm;
    }
    public DigestResult(byte[] hash,  String algorithm) {
        this.hash = hash;
        this.salt = null;
        this.algorithm = algorithm;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}