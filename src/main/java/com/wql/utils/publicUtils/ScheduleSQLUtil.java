package com.wql.utils.publicUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleSQLUtil {

    public void startAction(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("result");

                    }
                },1,3600, TimeUnit.SECONDS
        );
    }
}
