/*
ID: soldier7
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class friday {

    static String deadline;

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("friday.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());    // first integer
        deadline = deadline(n);

        Map<Integer, Integer> weekdayNumber = new HashMap<>();
        for (int i=0; i<7; i++) {
            weekdayNumber.put(i, 0);
        }
        String currentDate = "1900-01-13";
        Integer currentWeekday = 6;
        String nextDate = currentDate;
        int weekday = currentWeekday;
        while (true) {
            weekdayNumber.put(weekday, weekdayNumber.get(weekday) + 1);
            weekday = weekday(nextDate, weekday);
            nextDate = nextDate(nextDate);
            if (!isValidDate(nextDate)) {
                break;
            }
        }
        for (int i=6; i<13; i++) {
            out.print(weekdayNumber.get(i % 7));
            if (i < 12) {
                out.print(" ");
            }
        }
        out.println();
        out.close();
    }

    static String deadline(int n) {
        return (1900 + n - 1) + "-12" + "-31";
    }

    static String nextDate(String currentDate) {
        String[] strs = currentDate.split("-");
        int[] dates = new int[3];
        for (int i=0; i<strs.length; i++) {
            dates[i] = Integer.parseInt(strs[i]);
        }
        if (dates[1] == 12) {
            dates[0] = dates[0] + 1;
            dates[1] = 1;
        } else {
            dates[1] = dates[1] + 1;
        }


        if (dates[1] < 10) {
            return dates[0] + "-0" + dates[1] + "-" + dates[2];
        } else {
            return dates[0] + "-" + dates[1] + "-" + dates[2];
        }
    }

    static boolean isValidDate(String date) {
        if (date.compareTo(deadline) < 0) {
            return true;
        }
        return false;
    }

    static int weekday(String previousDate, Integer previosWeekday) {
        int days = getDays(previousDate);
        return (previosWeekday + days) % 7;
    }

    static int getDays(String previousDate) {
        String[] strs = previousDate.split("-");
        int[] dates = new int[3];
        for (int i=0; i<strs.length; i++) {
            dates[i] = Integer.parseInt(strs[i]);
        }
        switch (dates[1]) {
            case 2:
                if (isLeap(dates[0])) {
                    return 29;
                } else {
                    return 28;
                }
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return 0;
    }

    static boolean isLeap(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        if (year % 4 == 0) {
            return true;
        }
        return false;
    }
}
