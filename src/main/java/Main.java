/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        int n = cin.nextInt();
        int k = cin.nextInt();
        int heights[] = new int[n];
        for (int i = 0; i < n; i ++) {
            heights[i] = cin.nextInt();
        }
        cin.close();

        Arrays.sort(heights);

        int arrangement[] = new int[n];
        boolean picked[] = new boolean[n];

        arrangement[0] = heights[0];
        picked[0] = true;
        int count = permutate(1, arrangement, picked, heights, k);

        System.out.println(count);
    }

    private static int permutate(int pos, int arrangement[], boolean picked[], int heights[], int k) {
        if (pos == arrangement.length) {
            return arrangement[0] + k >= arrangement[pos - 1] ? 1 : 0;
        }

        int count = 0;
        for (int i = 0; i < heights.length; i ++) {
            if (!picked[i] && Math.abs(arrangement[pos - 1] - heights[i]) <= k) {
                picked[i] = true;
                arrangement[pos] = heights[i];
                count += permutate(pos + 1, arrangement, picked, heights, k);
                picked[i] = false;
            } else if (heights[i] - arrangement[pos - 1] > k) {
                break;
            }
        }

        return count;
    }
}


