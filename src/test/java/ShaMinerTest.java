import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class ShaMinerTest {
    @Test
    public void mine() {
        final ShaMiner m = new ShaMiner(firstCharsZero(5));

        final Block mine = m.mine();
        System.out.println(mine);
    }

    private static Predicate<String> firstCharsZero(final int n) {
        return s -> {
            for (int i = 0; i < n; i++) {
                if (!(s.charAt(i) == '0')) {
                    return false;
                }
            }
            return true;
        };
    }
}
