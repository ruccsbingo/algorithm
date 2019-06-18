import java.io.*;

/*
ID: soldier7
LANG: JAVA
TASK: subset
*/
public class subset {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        int total = caculate(n);
        out.println(dp(n, total));
        out.close();
    }

    static long dp(int n, int total) {
        if (total % 2 != 0) {
            return 0;
        }
        int half = total/2;
        long[] A = new long[half+1];
        A[0] = 1;
        for (int i=1; i<=n; i++) {
            for (int j=half; j>=i; j--) {
                A[j] = A[j] + A[j-i];
            }
        }
        return A[half]/2;
    }


    static int caculate(int n) {
        int sum = 0;
        for (int i=1; i<=n; i++) {
            sum += i;
        }
        return sum;
    }
}
