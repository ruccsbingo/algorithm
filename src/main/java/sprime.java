import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/*
ID: soldier7
LANG: JAVA
TASK: sprime
*/
public class sprime {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
//        init();
//        output(out, n);
        generate(out, n, 0, "");
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static BitSet bs = new BitSet(100000000);

    static boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        if (num == 1) {
            return false;
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

    static void init() {
        for (int i=0; i<100000000; i++) {
            if (isPrime(i)) {
                bs.set(i);
            }
        }
    }

    static void generate(PrintWriter out, int n, int i, String data) {
        if (n == 1) {
            out.println("2\n3\n5\n7");
            return;
        }
        if (i == n) {
            out.println(data);
            return;
        }

        for (int k=1; k<=9; k++) {
            String s = data + k;
            int t = Integer.parseInt(s);
            if (isPrime(t)) {
                generate(out, n, i+1, s);
            }
        }
    }

    static List<Integer> candidates = new ArrayList<>();

    static void output(PrintWriter out, int n) {
        int start = (int) Math.pow(10, n-1);
        for (int i = start; i<Math.pow(10, n); i++) {
            int first = i / start;
            if (first == 1 || first == 4 || first == 6 || first == 8 || first == 9) {
                continue;
            }
            int tmp = i;
            while (true) {
                if (tmp <= 0) {
                    break;
                }

                if (!isPrime(tmp)) {
                    break;
                }

                tmp /= 10;
            }
            if (tmp == 0) {
                out.println(i);
            }
        }
    }

}
