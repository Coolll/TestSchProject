package com.wql.utils.quartz;


public class JobTest {
    public static void main(String[] args) {
        try {
            QuartzUtil.addJob("wqlJob","triggerWQL",SQLJob.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
