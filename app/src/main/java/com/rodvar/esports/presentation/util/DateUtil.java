package com.rodvar.esports.presentation.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rodrigo on 01/12/16.
 */
public class DateUtil {

    private static final String TAG = DateUtil.class.getSimpleName();
    private static final String AUSSIE_SYDNEY_TZ = "Australia/Sydney";

    public static String parseAussieTime(String dateStr) {
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            input.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            output.setTimeZone(TimeZone.getTimeZone(AUSSIE_SYDNEY_TZ));

            Date date = input.parse(dateStr);
            return output.format(date);
        } catch (ParseException e) {
            Log.e(TAG, "Failed to parse date", e);
            return dateStr;
        }
    }
}
