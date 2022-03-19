package com.gitir.reading.util;

//Class date utils
public class DateUtils {
    private static String mon[]={"January", "February", "March","April", "May", "June", "July", "August", "September", "October", "November", "December"};

    /**
     * Method to get Year from result
     *
     * @param val
     * @return
     */
    public static String getYear(String val) {
        String year = val.substring(val.length() - 4);
        return year;
    }

    /**
     * Method to get Month from result
     *
     * @param val
     * @return
     */
    public static String getMonth(String val) {
        String month = val.substring(0, val.length() - 5);
        return mon[Integer.parseInt(month) - 1];
    }
}
