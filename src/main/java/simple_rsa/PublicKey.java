package simple_rsa;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PublicKey publicKey = (PublicKey) o;
        return e == publicKey.e && n == publicKey.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(e, n);
    }
}
