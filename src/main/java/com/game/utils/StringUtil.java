package com.game.utils;

/**
 * Created by lee on 2017-09-03.
 */
public class StringUtil {

    /**
     * 循环打印字符
     * @param ch 待打印字符
     * @param times 次数
     * @return
     */
    public static CharSequence printCharTimes(char ch, int times){
        StringBuilder buffer = new StringBuilder(times);
        for(int i=0; i<times; i++){
            buffer.append(ch);
        }
        return buffer;
    }

    /**
     * 按宽度打印数字
     * @param number 待打印数字
     * @param printWidth 打印宽度
     * @return
     */
    public static CharSequence printNumber(int number, int printWidth){
        // 获得数字长度
        int numLen = String.valueOf(number).length();
        // 构造返回串
        StringBuilder buffer = new StringBuilder(printWidth);
        int suffixLen = (printWidth - numLen) / 2;
        buffer.append(printCharTimes(' ', printWidth - suffixLen - numLen))
                .append(number)
                .append(printCharTimes(' ', suffixLen));
        return buffer;
    }

}
