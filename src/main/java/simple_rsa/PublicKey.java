package simple_rsa;

/**
 * TODO ASZ: comment me!
 */
public class PublicKey {
    private final int e;
    private final int n;

    public PublicKey(final int e, final int n) {
        this.e = e;
        this.n = n;
    }

    public int getE() {
        return e;
    }

    public int getN() {
        return n;
    }
}
