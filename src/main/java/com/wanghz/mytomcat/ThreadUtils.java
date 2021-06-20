package com.wanghz.mytomcat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {
    public static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

}
