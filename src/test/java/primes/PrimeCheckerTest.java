package primes;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class PrimeCheckerTest {
    @Test
    public void isPrimeTest() {

        final BigInteger min = new BigInteger("1000");
        final BigInteger max = new BigInteger("2000");
        PrimeChecker.generatePrimes(min, max);
    }
}
