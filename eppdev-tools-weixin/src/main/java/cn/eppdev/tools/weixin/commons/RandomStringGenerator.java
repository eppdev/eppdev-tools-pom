package cn.eppdev.tools.weixin.commons;

import java.util.Random;

/**
 * Created by haojinlong on 16-4-4.
 */
public class RandomStringGenerator {
    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取长度为32的随机字符串
     * @return 长度为32的随机字符串
     */
    public static String getRandomString(){
        return getRandomStringByLength(32);
    }
}
