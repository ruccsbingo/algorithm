import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: sort3
*/
public class sort3 {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
        int[] array = new int[n];
        for (int i=0; i<n; i++) {
            array[i] = Integer.parseInt(f.readLine());
        }

        sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);

        initBoundaryLine();
        initBoundaryNumbers(array);

        out.println(caculate());

//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }


    static int pos1, pos2;
    static int[] sorted;

    static int[][] boundaryNumbers = new int[3][3];

    static void initBoundaryNumbers(int[] array) {
        for (int i=0; i<array.length; i++) {
            if (i <= pos1) {
                if (array[i] == 2) {
                    boundaryNumbers[0][1] ++;
                }
                if (array[i] == 3) {
                    boundaryNumbers[0][2] ++;
                }
            } else if (i <= pos2) {
                if (array[i] == 1) {
                    boundaryNumbers[1][0] ++;
                }
                if (array[i] == 3) {
                    boundaryNumbers[1][2] ++;
                }
            } else {
                if (array[i] == 1) {
                    boundaryNumbers[2][0] ++;
                }
                if (array[i] == 2) {
                    boundaryNumbers[2][1] ++;
                }
            }
        }
    }

    static int caculate() {
        int result = 0;
        int small;
        int tmp = 0;
        for (int i=0; i<3; i++) {
            for (int j=i+1; j<3; j++) {
                small = Math.min(boundaryNumbers[i][j], boundaryNumbers[j][i]);
                result += small;
                boundaryNumbers[i][j] -= small;
                boundaryNumbers[j][i] -= small;
                tmp = Math.max(boundaryNumbers[i][j], boundaryNumbers[j][i]);
            }
        }
        result += tmp * 2;

        return result;
    }

    static void initBoundaryLine() {
        for (int i=0; i<sorted.length-1; i++) {
            if (sorted[i] != sorted[i+1]) {
                if (sorted[i] == 1) {
                    pos1 = i;
                }
                if (sorted[i] == 2) {
                    pos2 = i;
                }
            }
        }
    }

}
