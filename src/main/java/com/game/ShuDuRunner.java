package com.game;

import com.game.shudu.ShuDu;

public class ShuDuRunner {

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            ShuDu shuDu = new ShuDu(4);
            shuDu.setMaxStepAllowed(10000);
            shuDu.setMaxRetryTimes(5);
            shuDu.addFixedValue(2, 1, 1);
            shuDu.addFixedValue(2, 2, 2);
            shuDu.addFixedValue(2, 3, 3);
            shuDu.addFixedValue(2, 4, 4);
            shuDu.addFixedValue(2, 5, 5);
            shuDu.addFixedValue(2, 6, 6);
            shuDu.addFixedValue(2, 7, 7);
            shuDu.addFixedValue(2, 8, 8);
            shuDu.addFixedValue(2, 9, 9);
            boolean success = shuDu.process();
            if(!success){
                System.err.println("cannot process in max allowed steps");
            }else if (shuDu.validation(false)) {
                System.out.println(shuDu.getFormatResult());
            } else {
                System.err.println("not a valid shuDu result");
            }

            try{ Thread.sleep(1000l); }catch(Exception e){}
        }
    }
}
