import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BlockTest {
    @Test
    public void basics() {
        final Block a = new Block("some data stored");
        final Block b = new Block("some data stored");

        System.out.println(a);
        System.out.println(b.sha256().length());

        assertEquals(a.sha256(), b.sha256());
    }
}
