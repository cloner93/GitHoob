package com.milad.common;

public class numberUtils {


    public static String format(Long number) {
        String[] suffix = new String[]{"K", "M", "B", "T"};
        int size = (number.intValue() != 0) ? (int) Math.log10(number) : 0;
        if (size >= 3) {
            while (size % 3 != 0) {
                size = size - 1;
            }
        }
        double notation = Math.pow(10, size);
        return (size >= 3) ? +(Math.round((number / notation) * 100) / 100.0d) + suffix[(size / 3) - 1] : +number + "";
    }

}
