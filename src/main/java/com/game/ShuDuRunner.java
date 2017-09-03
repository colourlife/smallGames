package com.game;

import com.game.shudu.ShuDu;

public class ShuDuRunner {

    public static void main(String[] args) {
        ShuDu shuDu = new ShuDu(3);
        shuDu.addFixedValue(1, 0, 1);
        shuDu.addFixedValue(1, 1, 2);
        shuDu.addFixedValue(1, 2, 3);
        shuDu.addFixedValue(1, 3, 4);
        shuDu.addFixedValue(1, 4, 5);
        shuDu.addFixedValue(1, 5, 6);
        shuDu.addFixedValue(1, 6, 7);
        shuDu.addFixedValue(1, 7, 8);
        shuDu.addFixedValue(1, 8, 9);
        shuDu.process();
        if(shuDu.validation(false)){
            System.out.println(shuDu.getFormatResult());
        }else{
            System.err.println("not a valid shuDu result");
        }
    }
}
