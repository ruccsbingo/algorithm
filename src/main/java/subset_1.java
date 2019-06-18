import java.io.*;

/*
ID: soldier7
LANG: JAVA
TASK: subset_1
*/
public class subset_1 {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("subset_1.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset_1.out")));
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        generate(n);
        out.println(count/2);
        out.close();
    }

    static int count = 0;

    static void generate(int n) {
        int half = caculateHalf(n);
        int nums[] = new int[n];
        traverse(n, half);
    }

    static void traverse(int n, int half) {
        if (half < 0) {
            return;
        }
        if (half == 0) {
//            print(nums, pos);
            count++;
            return;
        }

        for (int i=n; i>0; i--) {
            traverse(i-1, half-i);
        }
    }

    static int sum(int nums[], int pos) {
        int sum = 0;
        for (int i=0; i<pos; i++) {
            sum += nums[i];
        }
        return sum;
    }

    static void print(int nums[], int pos) {
        for (int i=0; i < pos; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    static int caculateHalf(int n) {
        int sum = 0;
        for (int i=1; i<=n; i++) {
            sum += i;
        }
        return sum/2;
    }
}
