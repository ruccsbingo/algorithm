import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/*
ID: soldier7
LANG: JAVA
TASK: crypt1
*/
public class crypt1 {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        int n = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }

        out.println(count(arr));
        out.close();
    }

    static int count(int[] arr) {
        int c = 0;
        for (int i = 100; i<1000; i++) {
            for (int j=10; j<100; j++) {
                if (!isValidNumber(i) || !isValidNumber(j)) continue;
                int singleDigit = j % 10;
                if (singleDigit == 0 ) continue;
                int firstPart = singleDigit * i;
                if (!isFirstPart(firstPart)) continue;
                int tenDigit = j / 10;
                int secendPart = tenDigit * i;
                if (!isSecondPart(secendPart)) continue;
                if (isFinalPart(secendPart * 10 + firstPart)) {
                    c ++;
                }
            }
        }
        return c;
    }

    static boolean isFirstPart(int num) {
        return num >= 100 && num < 1000 && isValidNumber(num);
    }

    static boolean isSecondPart(int num) {
        return isFirstPart(num) && isValidNumber(num);
    }

    static boolean isFinalPart(int num) {
        return num >= 1000 && num < 10000 && isValidNumber(num);
    }

    static Set<Integer> set = new HashSet<>();
    static boolean isValidNumber(int num) {
        int digit;
        while (true) {
            digit = num % 10;
            if (!set.contains(digit)) {
                return false;
            }
            num /= 10;
            if (num == 0) {
                break;
            }
        }
        return true;
    }
}
