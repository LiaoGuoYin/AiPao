package com.liaoguoyin.aipao.api;


class utils {
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

    /**
     * 将数字转换成字母，编码的时候不同数字对应的字母唯一
     *
     * @param i 待转数字
     * @return 加密处理后的字符串
     */
    static String encrypt(int i) {
        String encryptOrigin = "czplgyznba";// 任意10个不同的字符，加密源
        StringBuilder result = new StringBuilder();
        char[] chars = String.valueOf(i).toCharArray();

        for (char each : chars) {
            result.append(encryptOrigin.charAt(each - '0'));
        }
        return result.toString();
    }
}
