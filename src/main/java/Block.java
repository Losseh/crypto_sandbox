import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Block {

    final String data;
    final String sha256;

    public Block(final String data) {
        this.data = data;
        this.sha256 = Hashing.sha256().hashString(data, StandardCharsets.UTF_8).toString();
    }

    public String sha256() {
        return sha256;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Block{" +
                "data='" + data + '\'' + ", " +
                "sha256=" + sha256 +
                '}';
    }
}
