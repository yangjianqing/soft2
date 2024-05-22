package cn.lanqiao.common.utils;
import java.util.Random;

public class OrderNumberGenerator {
    private static final long PREFIX = 222L;

    public static long generate() {
        Random random = new Random();
        long randomNumber = random.nextLong() % 900000L + 100000L;
        return PREFIX * 100000L + Math.abs(randomNumber);
    }
}