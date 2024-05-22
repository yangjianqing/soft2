package cn.lanqiao.common.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class OrderNumberGenerator {
    private static final String PREFIX = "222";
    private static final int LENGTH = 8;
    private static final Set<String> usedOrderNumbers = new HashSet<>();
    private static final Random random = new Random();

    public static synchronized String generateOrderNumber() {
        String orderNumber;
        do {
            StringBuilder sb = new StringBuilder(PREFIX);
            for (int i = 2; i < LENGTH; i++) {
                sb.append(random.nextInt(10)); // 生成0到9之间的随机数字
            }
            orderNumber = sb.toString();
        } while (usedOrderNumbers.contains(orderNumber)); // 如果订单号已存在，则重新生成

        usedOrderNumbers.add(orderNumber); // 添加到已使用的订单号集合中
        return orderNumber;
    }
}