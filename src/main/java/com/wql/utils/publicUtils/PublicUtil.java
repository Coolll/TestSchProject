package com.wql.utils.publicUtils;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class PublicUtil {
    private static   TimeZone timeZone;
    private static SimpleDateFormat dateFormat;
    public PublicUtil(){

    }

    public static String loadBeijingTime(){
        int timeOffset = 8*60*60*1000;

        if (timeZone == null){
            timeZone = new SimpleTimeZone(timeOffset,TimeZone.getAvailableIDs(timeOffset)[0]);
        }
        if (dateFormat == null){
            dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        }
        dateFormat.setTimeZone(timeZone);

        return dateFormat.format(new Date());
    }

    public static Integer loadBeijingTimeSeconds(){

        Integer time = (int)Math.floor(System.currentTimeMillis()/1000);

        return time;
    }

    public static String loadYesterdayDate(){

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        return yesterday;
    }
    public static Integer loadTodayZeroStamp(){
        Integer greenTimeSeconds = (int)Math.floor(System.currentTimeMillis()/1000);

        Integer timestamp =  (int)Math.floor(greenTimeSeconds - (greenTimeSeconds+8*3600)%(24*3600));
        return timestamp;

    }


}
