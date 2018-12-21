package max3;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        while (true) {
            String data = cin.nextLine();
            if (data.trim().equals("0")) {
                return;
            }
            System.out.println(max(data));
        }
//        cin.close();
    }

    public static int max(String data) {
        int max = 0;
        for (int i=0; i<=data.length(); i++) {
            for (int j=i+1; j<=data.length(); j++) {
                String subStr = data.substring(i, j);
                if (isValid(data, subStr)) {
                    int steps = data.length() / subStr.length();
                    if (max < steps) {
                        max = steps;
                    }
                }
            }
        }
        return max;
    }

    public static boolean isValid(String data, String subStr) {
        char[] datas = data.toCharArray();
        char[] subStrs = subStr.toCharArray();
        if (datas.length % subStrs.length != 0) {
            return false;
        }
        int steps = datas.length / subStrs.length;
        int count = 0;
        for (int i=0; i<steps; i++) {
            for (int j=0; j<subStrs.length; j++) {
                if (datas[count] != subStrs[j]) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

}

