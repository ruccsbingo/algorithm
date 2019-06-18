import java.io.*;
import java.util.Arrays;

/*
ID: soldier7
LANG: JAVA
TASK: palsquare
*/
public class palsquare {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        writer = out;

        int base = Integer.parseInt(f.readLine());
        output(base);
        out.close();
    }
    static PrintWriter writer;

    static void output(int base) {
        char[] chars;
        for(int i=1; i<=300; i++) {
            chars = expressBy(i*i, base);
//            System.out.println(Arrays.toString(chars));
            if (isPalindrome(chars)) {
                writer.println(toString(expressBy(i, base)) + " " + toString(chars));
//                System.out.println();
            }
        }
    }

    static char[] expressBy(int data, int base) {
        int i = 0;
        int tmp;
        char ch;
        char[] tmpResult = new char[1000000];
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
