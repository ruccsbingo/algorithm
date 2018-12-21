package max4;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        while (true) {
            int n = cin.nextInt();
            if (n < 0) {
                System.out.println("X");
            }
            System.out.println(max(n));
        }
//        cin.close();
    }

    static boolean isPow = true;

    static boolean isOne(int n) {
        if (n > 1 && (n & 1) == 1) {
            return true;
        }
        return false;
    }

    static int max(int data) {
        int steps = 0;
        while(true) {
            if (isOne(data) && isPow) {
                isPow = false;
            }
            data = data >> 1;
            if (data == 0) {
                break;
            }
            steps ++;
            if (data == 1 && isPow) {
                steps--;
                break;
            }
        }
        return steps;
    }
}

