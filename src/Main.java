import java.util.Scanner;

/**
 * Created by zhangbing on 2018/12/15.
 */
public class Main {
    public static void main(String args[]){
        Main p = new Main();
        Scanner cin = new Scanner(System.in);
        String n;
        String d;
        while (cin.hasNext()){
            n = cin.nextLine();
            int nn = Integer.parseInt(n);
//            System.out.println("n= " + nn);

            d = cin.nextLine();
            String datas[] = d.split(" ");
//            System.out.println(Arrays.toString(datas));

            long a[][] = p.initArray(nn, p.parse(datas));
            long max = p.nonRecursive(0, nn-1, a);
            System.out.println(max);

//            System.out.println(Arrays.toString(datas));
        }
    }

    int[] parse(String datas[]) {
        int result[] = new int[datas.length];
        for (int i = 0; i < datas.length; i++) {
            result[i] = Integer.parseInt(datas[i]);
        }
//        System.out.println("result= " + Arrays.toString(result));
        return result;
    }

    long[][] initArray(int n, int data[]) {
        long result[][] = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    result[i][j] = data[i];
                } else {
                    result[i][j] = Math.abs((data[j] - data[i]) * (j - i + 1));
                }
            }
        }

        return result;
    }


    long max(long dpArray[][], int start, int end) {
        long max = -1;
        for (int i = start; i <= end; i++) {
            for (int j = i; j <= end; j++) {
                if (max < dpArray[i][j]) {
                    max = dpArray[i][j];
                }
            }
        }
        return max;
    }

    long recursive(int start, int end, long a[][]) {
        if(start > end) {
            return 0;
        }

        int n = end - start + 1;
        long tmpDpArray[][] = new long[n][n];
        for (int i = start; i <= end; i++) {
            for (int j = i; j <= end; j++) {
                tmpDpArray[i-start][j-start] = recursive(start, i-1, a) + a[i][j] + recursive(j+1, end, a);
            }
        }

        return max(tmpDpArray, start - start, end - start);
    }

    long nonRecursive(int start, int end, long a[][]) {
        if(start > end) {
            return 0;
        }

        int len = end - start + 1;
        long dp[][] = new long[len][len];
        for (int i=0; i<len; i++){
            dp[i][i] = a[i][i];
        }

        for (int k = start; k <= end; k++) {
            for (int t = k; t <= end; t++) {
                long max = -1;
                for (int i = k; i <= t; i++) {
                    for (int j = i; j <= t; j++) {
                        long dp1 = 0;
                        if (k <= i-1) {
                            dp1 = dp[k][i-1];
                        }
                        long dp2 = 0;
                        if (j+1 <= t) {
                            dp2 = dp[j+1][t];
                        }
                        long tmp = dp1 + a[i][j] + dp2;
                        if (max < tmp) {
                            max = tmp;
                        }
                    }
                }
                dp[k][t] = max;
            }
        }

//        print(dp);

        return dp[start][end];
    }

    static void print(long data[][]) {
        for (int i=0; i<data.length; i++) {
            for (int j=0; j<data.length; j++) {
                System.out.print(data[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
