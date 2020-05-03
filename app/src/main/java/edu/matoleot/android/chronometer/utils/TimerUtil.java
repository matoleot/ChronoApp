package edu.matoleot.android.chronometer.utils;

public class TimerUtil {

    public static final String CRONO_FORMAT_MILLIS = "00:00:00.000";
    public static final String CRONO_FORMAT_WITHOUT_MILLIS = "00:00:00";

    /**
     * Method to get a String time format.
     *
     * @param cronoFormat
     * @param millis
     * @return
     */
    public static String getCronoStyleTextFromMillis(String cronoFormat, long millis) {
        String result;
        long tempCrono = millis;
        long mm = (millis % 1000);
        tempCrono = tempCrono / 1000;
        long sec = tempCrono % 60;
        tempCrono = tempCrono / 60;
        long min = tempCrono % 60;
        tempCrono = tempCrono / 60;
        long hours = tempCrono;
        if (hours > 23)
            hours = hours % 24;

        String hourString = Long.toString(hours);
        String minString = Long.toString(min);
        String secString = Long.toString(sec);
        String mmString = Long.toString(mm);
        if (hourString.length() == 1)
            hourString = "0" + hourString;
        if (minString.length() == 1)
            minString = "0" + minString;
        if (secString.length() == 1)
            secString = "0" + secString;
        if (mmString.length() == 1)
            mmString = "00" + mmString;
        else if (mmString.length() == 2)
            mmString = "0" + mmString;

        switch (cronoFormat) {
            case CRONO_FORMAT_MILLIS:
                result = hourString + ":" + minString + ":" + secString + "." + mmString;
                break;
            case CRONO_FORMAT_WITHOUT_MILLIS:
            default:
                result = hourString + ":" + minString + ":" + secString;
                break;
        }
        return result;
    }
}
