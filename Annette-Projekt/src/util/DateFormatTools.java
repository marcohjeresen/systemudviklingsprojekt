package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateFormatTools {

    /**
     * Constructor, creates a new object of the class.
     *
     * @author Dennis
     */
    public DateFormatTools() {
    }

    public Calendar getDateNowCal() {
        Calendar c = Calendar.getInstance();
        return c;
    }

    /**
     * Method, gets the current date.
     *
     * @return date as a String.
     */
    public String getDateNowString() {
        Calendar c = Calendar.getInstance();
        String year = "" + c.get(Calendar.YEAR);
        String month = "" + (c.get(Calendar.MONTH) + 1);
        String day = "" + c.get(Calendar.DAY_OF_MONTH);
        String hour = "" + c.get(Calendar.HOUR_OF_DAY);
        String minut = "" + c.get(Calendar.MINUTE);
        String second = "" + c.get(Calendar.SECOND);
        String date = "" + year + "-" + month + "-" + day + " " + hour + ":" + minut + ":" + second;
        return date;
    }

    /**
     * Method, gets the current date in a shorter String.
     *
     * @return date as a String.
     */
    public String getDateNowShortString() {
        Calendar c = Calendar.getInstance();
        String year = "" + c.get(Calendar.YEAR);
        String month = "" + (c.get(Calendar.MONTH) + 1);
        String day = "" + c.get(Calendar.DAY_OF_MONTH);
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String date = "" + year + "-" + month + "-" + day;
        return date;
    }

    /**
     * Method, gets the current day from Calender.
     *
     * @param calendar
     * @return date as a String.
     */
    public String getDateFromCal(Calendar calendar) {
        String year = "" + calendar.get(Calendar.YEAR);
        String month = "" + (calendar.get(Calendar.MONTH) + 1);
        String day = "" + calendar.get(Calendar.DAY_OF_MONTH);
        String hour = "" + calendar.get(Calendar.HOUR_OF_DAY);
        String minut = "" + calendar.get(Calendar.MINUTE);
        String second = "" + calendar.get(Calendar.SECOND);
        String date = "" + year + "-" + month + "-" + day + " " + hour + ":" + minut + ":" + second;
        return date;
    }

    /**
     * Method, gets a short date.
     *
     * @param date
     * @return c as a Calender object.
     * @throws ParseException
     */
    public Calendar getShortDate(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format1.parse(date));
        return c;
    }

    /**
     * Method, gets a short date from Calender.
     *
     * @param c
     * @return date as a String.
     */
    public String getShortDateFromCal(Calendar c) {
        String year = "" + c.get(Calendar.YEAR);
        String month = "";
        String day = "";
        if ((c.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            month = "" + (c.get(Calendar.MONTH) + 1);
        }
        if (c.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + c.get(Calendar.DAY_OF_MONTH);
        } else {
            day = "" + c.get(Calendar.DAY_OF_MONTH);
        }
        String date = "" + year + "-" + month + "-" + day;
        return date;
    }

    /**
     * Method, formats stringdate to a calender.
     *
     * @param date
     * @return datecal as a Calender object.
     */
    public Calendar getDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dateCal = Calendar.getInstance();
        try {
            Date d = formatter.parse(date);
            dateCal.setTime(d);
        } catch (ParseException ex) {
            System.out.println("utility - DateFormatTools - getDateFromString(): Date parse error" + ex.getLocalizedMessage());
        }
        return dateCal;
    }

    /**
     * Method, jumps a chosen number of days forward in the calender.
     *
     * @param calendar
     * @param days
     * @return calender as a Calender object.
     */
    public Calendar getNextday(Calendar calendar, int days) {
        calendar.roll(Calendar.DAY_OF_YEAR, days);
        return calendar;
    }

    /**
     * Method, is initialized with a string date and formats it to a calender
     * and sets hour, minutes and seconds to 0.
     *
     * @param date
     * @return dateCal as a Calender object.
     */
    public Calendar getStartDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar dateCal = Calendar.getInstance();
        try {
            Date d = formatter.parse(date);
            dateCal.setTime(d);
        } catch (ParseException ex) {
            System.out.println("utility - DateFormatTools - getStartDateFromString(): Date parse error" + ex.getLocalizedMessage());
        }
        dateCal.set(Calendar.HOUR_OF_DAY, 00);
        dateCal.set(Calendar.MINUTE, 00);
        dateCal.set(Calendar.SECOND, 00);
        return dateCal;
    }

    /**
     * Method, is initialized with a string date and formats it to a calender
     * and sets hour to 23, minutes to 59 and seconds to 59.
     *
     * @param date
     * @return dateCal as a Calender object.
     */
    public Calendar getEndDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar dateCal = Calendar.getInstance();
        try {
            Date d = formatter.parse(date);
            dateCal.setTime(d);
        } catch (ParseException ex) {
            System.out.println("utility - DateFormatTools - getEndDateFromString(): Date parse error" + ex.getLocalizedMessage());
        }
        dateCal.set(Calendar.HOUR_OF_DAY, 23);
        dateCal.set(Calendar.MINUTE, 59);
        dateCal.set(Calendar.SECOND, 59);
        return dateCal;
    }

    /**
     * Method, shows the day name, month and date.
     *
     * @param date
     * @return theDate as a String.
     */
    public String getDay(String date) {
        String theDate = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = formatter.parse(date);
            Calendar fromDate = Calendar.getInstance();
            fromDate.setTime(d);
            SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMM");
            theDate = format.format(fromDate.getTime());
            theDate = theDate.substring(0, 1).toUpperCase() + theDate.substring(1);
        } catch (ParseException ex) {
            System.out.println("utility - DateFormatTools - getDay(): Date parse error" + ex.getLocalizedMessage());
        }
        return theDate;
    }

    /**
     * Method, shows the name of the day and the date of the month.
     *
     * @param date
     * @return theDate as a String.
     */
    public String getDayLetter(String date) {
        String theDate = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = formatter.parse(date);
            Calendar fromDate = Calendar.getInstance();
            fromDate.setTime(d);
            SimpleDateFormat format = new SimpleDateFormat("EEEE dd");
            theDate = format.format(fromDate.getTime());
            theDate = theDate.substring(0, 1).toUpperCase() + theDate.substring(1);
        } catch (ParseException ex) {
            System.out.println("utility - DateFormatTools - getDayLetter(): Date parse error" + ex.getLocalizedMessage());
        }
        return theDate;
    }

    public String getDayLetters(Calendar c) {
        String days = "";
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.MMM");
        String dateToUpper = format.format(c.getTime());
        days = (dateToUpper.substring(0, 1).toUpperCase() + dateToUpper.substring(1));
        return days;
    }
}
