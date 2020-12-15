package simple_rsa;

/**
 * TODO ASZ: comment me!
 */
public class PrivateKey {
    private final int d;
    private final int n;

    public PrivateKey(final int d, final int n) {
        this.d = d;
        this.n = n;
    }

    public int getD() {
        return d;
    }

    public int getN() {
        return n;
    }
}
