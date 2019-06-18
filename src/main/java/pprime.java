import java.io.*;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
ID: soldier7
LANG: JAVA
TASK: pprime
*/
public class pprime {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
//        long start = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        find(out, a, b);

//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static void find(PrintWriter out, int a, int b) {
        for (int i=a; i<=b; i++) {
            if (i<10000000 && isPalindromic(i) && isPrime(i)) {
//                System.out.println(i);
                out.println(i);
            }
        }
    }

    static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int tmp = (int) Math.sqrt(num);
        for (int i=5; i<=tmp; i+=6) {
            if (num % i == 0 || num % (i+2) == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isPalindromic(int num) {
        String s = num + "";
        char[] chars = s.toCharArray();
        for (int i=0; i<chars.length/2; i++) {
            if (chars[i] != chars[chars.length-1-i]) {
                return false;
            }
        }
        return true;
    }
}
