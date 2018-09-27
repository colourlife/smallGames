package com.game.shudu;

import com.game.shudu.ShuDuParser;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShuDuParserTest {

    private static Logger logger = LoggerFactory.getLogger(ShuDuParserTest.class);

    @Test
    public void testParse() throws Exception {
        
        ShuDu shudu = ShuDuParser.parse(ShuDuParserTest.class.getClassLoader().getResourceAsStream("shudu/shudu-very_hard-2.map"));
        shudu.setMaxStepAllowed(1000000);
        shudu.setMaxRetryTimes(100);
        boolean success = shudu.process();
        printProcessResult(shudu, success);
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