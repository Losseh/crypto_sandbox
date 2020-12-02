import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class ShaMiner {
    private final String allowedChars = "abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPRSTQUVWXYZ1234567890";
    private final Predicate<String> predicate;
    private final Set<String> forbiddenStrings = new HashSet<>();
    {
        forbiddenStrings.add("s");
        forbiddenStrings.add("u");
        forbiddenStrings.add("M");
        forbiddenStrings.add("aB");
    }

    public ShaMiner(final Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public Block mine() {
        final int charsAmount = allowedChars.length();
        long counter = 0;

        List<String> shorterCandidates = asList("z123Z");
        List<String> tempCandidates = new ArrayList<>();

        while (counter < 1000000000L) {
            for (final String shorterCand : shorterCandidates) {
                for (int i = 0; i < charsAmount; i++) {
                    counter++;

                    final char c = allowedChars.charAt(i);
                    final String candidate = shorterCand + c;
                    tempCandidates.add(candidate);
                    final Block b = new Block(candidate);

                    if (!forbiddenStrings.contains(candidate) && predicate.test(b.sha256())) {
                        System.out.println("found solution in " + counter + " iterations=" + b.toString());
                        //return b;
                    }
                }
            }

            System.out.println("#previousCandidates=" + tempCandidates.size());

            shorterCandidates = tempCandidates;
            tempCandidates = new ArrayList<>();
        }

        return null;
    }
}
