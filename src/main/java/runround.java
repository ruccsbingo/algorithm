import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: runround
*/
public class runround {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("runround.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
//        long start = System.currentTimeMillis();
        long n = Long.parseLong(f.readLine());
        out.println(next(n));
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static long next(long start) {
        long tmp = start;
        while (true) {
            tmp += 1;
            if (isRunRound(tmp)) {
                return tmp;
            }
        }
    }

    static boolean isRunRound(Long number) {
        List<Integer> l = new ArrayList<>();
        char[] chs = number.toString().toCharArray();
        for (char ch : chs) {
            l.add(Integer.parseInt(ch+""));
        }

        int pos = 0;
        while (true) {
            pos = getNextPos(l.size(), pos, l.get(pos));
            currentPos = pos;
            if (isTerminal()) {
                return true;
            }
            if (!isValid(pos)) {
                return false;
            }
        }
    }

    static int getNextPos(int length, int current, int v) {
        return (current + v) % length;
    }

    static int currentPos;
    static Map<Integer, Boolean> reached = new HashMap<>();

    static boolean isTerminal() {
        if (currentPos == 0) {
            return true;
        }
        return false;
    }

    static boolean isValid(int pos) {
        if (pos == 0) {
            return true;
        }

        if (reached.get(pos) != null && reached.get(pos)) {
            return false;
        }
        reached.put(pos, true);
        return true;
    }

}
