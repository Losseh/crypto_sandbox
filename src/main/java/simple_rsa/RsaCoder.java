package simple_rsa;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class RsaCoder {
    public static int code(final int c, final PublicKey publicKey) {
        return exponentiate(c, publicKey.getE(), publicKey.getN());
    }

    public static List<Integer> code(final List<Integer> message, final PublicKey publicKey) {
        final UnaryOperator<Integer> codeFoo = c -> exponentiate(c, publicKey.getE(), publicKey.getN());
        return message.stream().map(codeFoo).collect(Collectors.toList());
    }

    public static int decode(final int c, final PrivateKey privateKey) {
        return exponentiate(c, privateKey.getD(), privateKey.getN());
    }

    public static List<Integer> decode(final List<Integer> message, final PrivateKey privateKey) {
        final UnaryOperator<Integer> codeFoo = c -> exponentiate(c, privateKey.getD(), privateKey.getN());
        return message.stream().map(codeFoo).collect(Collectors.toList());
    }

    public static PrivateKey breakCode(final List<Integer> plain, final PublicKey publicKey) {
        final List<Integer> coded = code(plain, publicKey);
        for (int i = 0; i < publicKey.getN(); i++) {
            final PrivateKey privCandidate = new PrivateKey(i, publicKey.getN());
            final List<Integer> decoded = decode(coded, privCandidate);
            if (decoded.equals(plain)) {
                return privCandidate;
            }
        }

        return null;
    }

    static int exponentiate(final int long_char, final long exponent, final long modulo) {
        long result = 1;
        // this isn't 100% efficient. One can group exponents like 7 = 4 + 2 + 1
        for (int i = 0; i < exponent; i++) {
            result = (result * long_char) % modulo;
        }
        return (int) result;
    }
}
