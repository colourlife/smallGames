package com.game;

import com.game.shudu.ShuDu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShuDuRunner {

    private static Logger logger = LoggerFactory.getLogger(ShuDuRunner.class);

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            ShuDu shuDu = new ShuDu(3);
            shuDu.setMaxStepAllowed(100000);
            shuDu.setMaxRetryTimes(5);
//            shuDu.addFixedValue(2, 1, 1);
//            shuDu.addFixedValue(2, 2, 2);
//            shuDu.addFixedValue(2, 3, 3);
//            shuDu.addFixedValue(2, 4, 4);
//            shuDu.addFixedValue(2, 5, 5);
//            shuDu.addFixedValue(2, 6, 6);
//            shuDu.addFixedValue(2, 7, 7);
//            shuDu.addFixedValue(2, 8, 8);
//            shuDu.addFixedValue(2, 9, 9);
            boolean success = shuDu.process();
            if(!success){
                logger.error("cannot process in max allowed steps");
            }else if (shuDu.validation(false)) {
                logger.info("process success with a valid result:\n" + shuDu.getFormatResult());
            } else {
                logger.error("not a valid shuDu result");
            }

            try{ Thread.sleep(1000l); }catch(Exception e){}
        }
    }
}
