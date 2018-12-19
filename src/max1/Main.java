package max1;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        int n = cin.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i ++) {
            data[i] = cin.nextInt();
        }
        cin.close();

        System.out.println(recursive(1, 0, data.length-1, data));
    }

    public static int recursive(int n, int start, int end, int[] data) {
        if (start == end) {
            return data[start] * n;
        }

        return Math.max(recursive(n+1, start+1, end, data) + data[start]*n, recursive(n+1, start, end-1, data)+data[end]*n);
    }
}


