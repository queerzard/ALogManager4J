package com.github.sebyplays.logmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * This function returns the current date in the format of MM_dd_yyyy
     *
     * @return The current date in the format MM_dd_yyyy
     */
    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM_dd_yyyy");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);
        return currentDate;
    }

    /**
     * It returns the current time in the format of HH:mm:ss
     *
     * @return The current time in the format HH:mm:ss
     */
    public String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        return currentTime;
    }

}
