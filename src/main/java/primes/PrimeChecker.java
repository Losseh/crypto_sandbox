package primes;

import java.math.BigInteger;

public class PrimeChecker {
    public static void generatePrimes(final BigInteger min, final BigInteger max) {

        BigInteger primeCandidate = min;
        BigInteger curNumberOfPrimes = new BigInteger("0");

        while (primeCandidate.compareTo(max) < 0) {
            boolean result = true;

            for (BigInteger i = new BigInteger("2"); (i.pow(2)).compareTo(primeCandidate) <= 0; i = inc(i)) {
                if (primeCandidate.mod(i).compareTo(BigInteger.ZERO) == 0) {
                    result = false;
                    break;
                }
            }

            if (result) {
                System.out.println(primeCandidate);
                curNumberOfPrimes = inc(curNumberOfPrimes);
            }

            primeCandidate = inc(primeCandidate);
        }
    }

    public static BigInteger sqrt(final BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }

    public static BigInteger inc(final BigInteger num) {
        return num.add(new BigInteger("1"));
    }
}
