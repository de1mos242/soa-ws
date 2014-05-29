package com.github.gkislin.common.stat;


import com.github.gkislin.common.LoggerWrapper;

/**
 * User: GKislin
 * Date: 06.08.2009
 */
public class Statistics {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(Statistics.class);

    public enum RESULT {
        SUCCESS, FAIL
    }

    public static void count(String payload, long startTime, RESULT result) {
        long now = System.currentTimeMillis();
        int ms = (int) (now - startTime);
        LOGGER.info(payload + " " + result.name() + " execution time(ms): " + ms);
        // place for statistics staff
    }
}

