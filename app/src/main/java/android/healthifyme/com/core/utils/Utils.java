package android.healthifyme.com.core.utils;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Niranjan on 4/12/2016.
 */
public class Utils {
    Context context;
    public Utils(Context context) {
        this.context = context;
    }
    public Utils() {

    }


    public String getWeekDay(int week) {
        switch (week) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
            default:
                return "";
        }
    }

    public String getMonthString(int i) {
        switch (i) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "";
        }

    }

    public String getDurationString(String startTime, String endTime, String timeState) {
        return startTime.split(" ")[1].substring(0, 5) + " " + timeState + " - " + endTime.split(" ")[1].substring(0, 5)+ " " + timeState ;
    }

    public Calendar getDate(String dateKey) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
           calendar.setTime(sdf.parse(dateKey));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
