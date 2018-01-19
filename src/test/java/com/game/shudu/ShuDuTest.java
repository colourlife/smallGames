package com.game.shudu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShuDuTest {

    private static Logger logger = LoggerFactory.getLogger(ShuDuTest.class);

    @Test
    public void testProcess1() throws Exception{
        for(int i=0; i<10; i++){
            ShuDu shuDu = new ShuDu(3);
            boolean success = shuDu.process();
            printProcessResult(shuDu, success);
            Thread.sleep(1000l);
        }
    }

    @Test
    public void testProcess2() throws Exception{
        for(int i=0; i<10; i++){
            ShuDu shuDu = new ShuDu(4);
            shuDu.setMaxStepAllowed(10000);
            shuDu.setMaxRetryTimes(5);
            boolean success = shuDu.process();
            printProcessResult(shuDu, success);
            Thread.sleep(1000l);
        }
    }

    @Test
    public void testProcess3() throws Exception{
        for(int i=0; i<10; i++){
            ShuDu shuDu = new ShuDu(5);
            shuDu.setMaxStepAllowed(30000000);
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
            printProcessResult(shuDu, success);
            Thread.sleep(1000l);
        }
    }

    private void printProcessResult(ShuDu shuDu, boolean success){
        if(!success){
            logger.error("cannot process in max allowed steps");
        }else if (shuDu.validation(false)) {
            logger.info("process success with a valid result:\n" + shuDu.getFormatResult());
        } else {
            logger.error("not a valid shuDu result");
        }
    }
}
