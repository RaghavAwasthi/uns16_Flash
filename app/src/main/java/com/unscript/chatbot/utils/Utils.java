package com.unscript.chatbot.utils;

import com.google.firebase.auth.FirebaseAuth;

public class Utils {
//
//    public static long getCurrentDateTime() {
//        return Instant.now().toEpochMilli();
//    }
//
//    public static LocalDateTime getLocalDateTime(long datetime) {
//        return LocalDateTime.ofInstant(Instant.ofEpochMilli(datetime), ZoneId.systemDefault());
//    }
//
//
//    public static String getDateTime(long date) {
//        String mdate = "";
//        try {
//            LocalDateTime dateTime = getLocalDateTime(date);
//            mdate = dateTime.getMonth() + " " + dateTime.getDayOfMonth() + "," + dateTime.getYear() + " " + dateTime.getHour() + ":" + dateTime.getMinute();
//
//        } catch (ZoneRulesException e) {
//
//        }
//
//        return mdate;
//    }

    public static String getUserName() {
        String name = "Unknown";
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null)
            name = auth.getCurrentUser().getDisplayName();

        return name;


    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    public static String getServiceCode(int val) {
        String res = "";
        switch (val) {
            case 0:
                res = "Created";
                break;
            case 1:
                res="Processed";
                break;
        }
        return res;
    }

    public static String getAgentID() {
        return FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();

    }


}
