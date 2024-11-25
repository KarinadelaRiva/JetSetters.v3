package org.jetsettersv2.utilities;

public class TimerUtil {
    public static long measureTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }
}