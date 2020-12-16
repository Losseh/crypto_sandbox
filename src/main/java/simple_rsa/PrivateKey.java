package simple_rsa;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PrivateKey that = (PrivateKey) o;
        return d == that.d && n == that.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(d, n);
    }
}
