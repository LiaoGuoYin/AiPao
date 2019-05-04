package com.liaoguoyin.aipao.api;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class utils {
    /**
     * 传入一个一个范围[min, max]，返回范围内的随机数
     *
     * @param min 随机数的最小值
     * @param max 随机数的最大值
     * @return 返回范围内的随机数
     */
    static int randomUtils(int min, int max) {
        return (int) (min + ((max - min) * Math.random()));
    }

    static int randomUtils(double min, double max) {
        return (int) (min + ((max - min) * Math.random()));
    }
    /**
     * 将数字转换成字母，编码的时候不同数字对应的字母唯一
     *
     * @param i 待转数字
     * @return 加密处理后的字符串
     */
    static String encrypt(int i) {
        String encryptOrigin = "xfvdmyirsg";// 任意10个不同的字符，加密源(注意！！不是完全任意，需要自己抓一次包来确定)
        StringBuilder result = new StringBuilder();
        char[] chars = String.valueOf(i).toCharArray();

        for (char each : chars) {
            result.append(encryptOrigin.charAt(each - '0'));
        }
        return result.toString();
    }

    /**
     * @param s inputstring
     * @return 返回对应的md5值
     * @throws NoSuchAlgorithmException md5算法错误
     */
    public static String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(s.getBytes());
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }
}
