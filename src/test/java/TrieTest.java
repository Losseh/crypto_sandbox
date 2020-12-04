import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TrieTest {
    @Test
    public void firstInsert() {
        final Trie<Integer> trie = new Trie<>();

        trie.insert("one", 1);
        System.out.println(trie.toString());

        {
            final Optional<Trie<Integer>.Node> lookup = trie.lookup("one");
            assertTrue(lookup.isPresent());
            assertEquals(1, lookup.get().getValue());
        }
    }

    @Test
    public void twoInserts() {
        final Trie<Integer> trie = new Trie<>();

        trie.insert("on/e", 1);
        trie.insert("on/i", 12);

        {
            final Optional<Trie<Integer>.Node> lookup = trie.lookup("on/e");
            assertTrue(lookup.isPresent());
            assertEquals(1, lookup.get().getValue());
        }
        {
            final Optional<Trie<Integer>.Node> lookup = trie.lookup("on/i");
            assertTrue(lookup.isPresent());
            assertEquals(12, lookup.get().getValue());
        }
    }

    @Test
    public void plentyOfInserts() {
        final Trie<Integer> trie = new Trie<>();

        trie.insert("one/two/e", 1);
        trie.insert("one/two/i", 1211);
        trie.insert("one/two/g", 13);
        trie.insert("one/two/h", 1222);
        trie.insert("one/three/e", 11);
        trie.insert("one/three/i", 152);
        trie.insert("one/three/2e", 41);
        trie.insert("one/four/i", 131312);
        trie.insert("one/four/1", 11);
        trie.insert("one/four/123", 1332);

        System.out.println(trie);
        {
            final Optional<Trie<Integer>.Node> lookup = trie.lookup("one/four/123");
            assertTrue(lookup.isPresent());
            assertEquals(1332, lookup.get().getValue());
        }
        {
            final Optional<Trie<Integer>.Node> lookup = trie.lookup("one/four/1233");
            assertFalse(lookup.isPresent());
        }
    }

    @Test
    public void triesComparison() {
        final Trie<Integer> a = new Trie<>();
        a.insert("one/two/e", 1);
        a.insert("one/two/i", 1211);
        a.insert("one/two/g", 13);
        a.insert("one/two/h", 1222);
        a.insert("one/three/e", 11);
        a.insert("one/three/i", 152);
        a.insert("one/three/2e", 41);
        a.insert("one/four/i", 131312);
        a.insert("one/four/1", 11);
        a.insert("one/four/123", 1332);

        final Trie<Integer> b = new Trie<>();
        b.insert("one/two/e", 1);
        b.insert("one/two/i", 1211);
        b.insert("one/two/g", 13);
        b.insert("one/two/h", 1222);
        b.insert("one/three/e", 11);
        b.insert("one/three/i", 152);
        b.insert("one/three/2e", 41);
        b.insert("one/four/i", 131312);
        b.insert("one/four/1", 11);
        b.insert("one/four/1243", 13312);

        assertNotEquals(a.hashCode(), b.hashCode());
        {
            final Optional<Trie<Integer>.Node> lookupA = a.lookup("one/three");
            final Optional<Trie<Integer>.Node> lookupB = b.lookup("one/three");

            assertTrue(lookupA.isPresent());
            assertTrue(lookupB.isPresent());
            assertEquals(lookupA.get().hashCode(), lookupB.get().hashCode());
        }

        System.out.println(a);
    }

    @Test
    public void noMatch() {
        final Trie<Integer> trie = new Trie<>();

        trie.insert("one", 1);

        assertFalse(trie.lookup("two").isPresent());
    }
}
