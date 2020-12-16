package simple_rsa;

import static com.google.common.primitives.Ints.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * TODO ASZ: comment me!
 */
public class RsaCoderTest {
    @Test
    public void exponentationTest() {
        assertEquals(10, RsaCoder.exponentiate(10, 1, 11));
        assertEquals(1, RsaCoder.exponentiate(10, 2, 11));
        assertEquals(10, RsaCoder.exponentiate(10, 3, 11));
    }

    @Test
    public void codeTest() {
        // p=3, q=11, d=3, e=7, n=33
        final PublicKey publicKey = new PublicKey(7, 33);
        final PrivateKey privateKey = new PrivateKey(3, 33);
        final int beforeCoding = 15;

        final int afterCoding = RsaCoder.code(beforeCoding, publicKey);
        assertEquals(afterCoding, 27);

        final int decoded = RsaCoder.decode(afterCoding, privateKey);
        assertEquals(beforeCoding, decoded);
    }

    @Test
    public void codeListTest() {
        // p=3, q=11, d=3, e=7, n=33
        final PublicKey publicKey = new PublicKey(7, 33);
        final PrivateKey privateKey = new PrivateKey(3, 33);
        final List<Integer> msg = asList(1, 2, 3, 5, 3, 1, 6, 7, 4, 2, 1, 6, 21, 13, 11);

        final List<Integer> codedMsg = RsaCoder.code(msg, publicKey);
        final List<Integer> decodedMsg = RsaCoder.decode(codedMsg, privateKey);
        assertEquals(decodedMsg, msg);
    }

    @Test
    public void breakCodeTest() {
        // p=3, q=11, d=3, e=7, n=33
        final PublicKey publicKey = new PublicKey(7, 33);
        final PrivateKey expectedPrivateKey = new PrivateKey(3, 33);
        final List<Integer> msg = asList(1, 2, 3, 5, 3, 1, 6, 7, 4, 2, 1, 6, 21, 13, 11);

        final PrivateKey brokenPrivateKey = RsaCoder.breakCode(msg, publicKey);
        assertEquals(brokenPrivateKey, expectedPrivateKey);
    }
}
