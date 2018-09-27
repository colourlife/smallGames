package com.game.utils;

import java.lang.Math;

/**
 * Created by lee on 2017-09-03.
 */
public class StringUtil {

    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 2;
    public static final int ALIGN_RIGHT = 3;

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

    public static CharSequence printNumber(int number, int printWidth) {
        return printNumber(number, printWidth, ALIGN_CENTER);
    }

    /**
     * 按宽度打印数字
     * @param number 待打印数字
     * @param printWidth 打印宽度
     * @return
     */
    public static CharSequence printNumber(int number, int printWidth, int align){
        // 获得数字长度
        int numLen = String.valueOf(number).length();
        // 构造返回串
        StringBuilder buffer = new StringBuilder(printWidth);
        int suffixLen = 0;
        if(align == ALIGN_LEFT) {
            suffixLen = printWidth - numLen;
        } else if(align == ALIGN_CENTER) {
            suffixLen = (int) Math.ceil((printWidth - numLen) / 2.0);
        }
        buffer.append(printCharTimes(' ', printWidth - suffixLen - numLen))
                .append(number)
                .append(printCharTimes(' ', suffixLen));
        return buffer;
    }

}
