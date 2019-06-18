import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: soldier7
LANG: JAVA
TASK: dualpal
*/
public class dualpal {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens

        int n = Integer.parseInt(st.nextToken());    // first integer
        int s = Integer.parseInt(st.nextToken());    // second integer
        List<Integer> r = output(n,s);
        StringBuilder builder = new StringBuilder();
        for (int i : r) {
            builder.append(i);
            builder.append('\n');
        }
        out.print(builder.toString());
        out.close();
    }

    static List<Integer> output(int n, int s) {
        List<Integer> result = new ArrayList<>();
        char[] chars;
        int counter=0;
        int sum = 0;
        for(int i=s+1; i<Integer.MAX_VALUE; i++) {
            sum = 0;
            for (int b=2; b<=10; b++) {
                chars = expressBy(i, b);
                if (isPalindrome(chars)) {
                    sum++;
                }
                if (sum >= 2) {
                    result.add(i);
                    counter++;
                    break;
                }
            }
            if (counter>=n) {
                break;
            }
        }
        return result;
    }

    static char[] expressBy(int data, int base) {
        int i = 0;
        int tmp;
        char ch;
        char[] tmpResult = new char[10000];
        while (true) {
            if (data == 0) break;
            tmp = data % base;
            if (tmp < 10) {
                ch = (char) ('0' + tmp);
            } else {
                ch = (char) ('A' + tmp - 10);
            }
            tmpResult[i] = ch;
            data = data / base;
            i++;
        }
        char[] result = new char[i];
        for (int j=0; j<result.length; j++) {
            result[j] = tmpResult[result.length-1-j];
        }
        return result;
    }

    static boolean isPalindrome(char[] data) {
        int i=0;
        int j= data.length-1;
        for (; i<j; i++, j--) {
            if (data[i] != data[j]) {
                return false;
            }
        }
        return true;
    }

    static String toString(char[] chars) {
        StringBuilder builder = new StringBuilder();
        for(char ch : chars) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
