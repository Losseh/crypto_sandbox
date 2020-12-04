import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// TODO ASZ: try to remember hashes
public class Trie<V> {
    private final Map<String, Node> children;

    public Trie() {
        children = new HashMap<>();
    }

    public void insert(final String path, final V value) {
        final String[] paths = path.split("/");

        Node node = null;
        Map<String, Node> nodes = this.children;
        for (final String partPath : paths) {
            node = nodes.get(partPath);
            if (node == null) {
                node = new Node(null);
                nodes.put(partPath, node);
            }
            nodes = node.children;
        }

        Objects.requireNonNull(node, "something went wrong by inserting. node must not be null at this point");
        node.value = value;
    }

    public Optional<Node> lookup(final String path) {
        final String[] paths = path.split("/");

        Node found = null;
        Map<String, Node> nodes = this.children;
        for (final String partPath : paths) {
            found = nodes.get(partPath);
            if (found == null) {
                return Optional.empty();
            } else {
                nodes = found.children;
            }
        }

        return Optional.ofNullable(found);
    }

    public class Node {
        private final Map<String, Node> children;
        private V value;

        public Node(final V value) {
            this.children = new HashMap<>();
            this.value = value;
        }

        public Map<String, Node> getChildren() {
            return Collections.unmodifiableMap(children);
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            String result = "Node{";

            if (!children.isEmpty()) {
                result += "children=" + children;

                if (value != null) {
                    result+= ", value=" + value;
                }

                return result + "}";
            } else {
                return result + "value=" + value + "}";
            }
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Node node = (Node) o;
            return Objects.equals(children, node.children) && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(children, value);
        }
    }

    @Override
    public String toString() {
        return "Trie{" + "children=" + children + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Trie<?> trie = (Trie<?>) o;
        return Objects.equals(children, trie.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }
}
