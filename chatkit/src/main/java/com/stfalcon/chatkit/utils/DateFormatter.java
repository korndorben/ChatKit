package com.stfalcon.chatkit.utils;

import android.content.Context;

import com.stfalcon.chatkit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by troy379 on 07.09.16.
 */
public final class DateFormatter {
    private DateFormatter() { throw new AssertionError(); }

    public static String format(Date date, Template template) {
        if (date == null) return "";
        return new SimpleDateFormat(template.get(), Locale.getDefault())
                .format(date);
    }

    public static String getDateString(Context context, Date date) {
        if (date == null) return "";
        else if (isToday(date)) {
            return context.getString(R.string.chat_date_today);
        } else if (isYesterday(date)) {
            return context.getString(R.string.chat_date_yesterday);
        } else return new SimpleDateFormat(Template.STRING_MONTH.get(), Locale.getDefault())
                .format(date);
    }

    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    public static boolean isYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return isSameDay(date, cal.getTime());
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public enum Template {
        STRING_MONTH("dd MMMM yyyy"),
        TIME("HH:mm");

        private String template;

        Template(String template) {
            this.template = template;
        }

        public String get() {
            return template;
        }
    }
}
