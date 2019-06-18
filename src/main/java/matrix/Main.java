package matrix;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        while (true) {
            int n = cin.nextInt();
            if (n == 0) {
                return;
            }
            int[][] matrix = new int[n][n];
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    matrix[i][j] = cin.nextInt();
                }
            }
            System.out.println(validMatrix(matrix));
        }
//        cin.close();
    }

    static String validMatrix(int[][] matrix) {
        int[] rows = invalidRow(matrix);
        int[] columns = invalidColunm(matrix);
        if (rows.length > 1 || columns.length > 1) {
            return "ERROR";
        }
        if (rows[0] == -1 && columns[0] == -1) {
            return "OK";
        }
        if (rows[0] * columns[0] < 0) {
            return "ERROR";
        }
        if (rows[0] * columns[0] >=0) {
            if (matrix[rows[0]][columns[0]] == 0) {
                matrix[rows[0]][columns[0]] = 1;
            } else {
                matrix[rows[0]][columns[0]] = 0;
            }
            if (isValid(matrix, rows[0], columns[0])) {
                int tmpR = rows[0] +1;
                int tmpC = columns[0] + 1;
                return "CHANGE ("+ tmpR + "," + tmpC + ")";
            }
        }
        return "Unreach";
    }

    static boolean isValid(int[][] matrix, int row, int column) {
        int sum = 0;
        for (int i=0; i<matrix.length; i++) {
            sum += matrix[row][i];
        }
        if (sum % 2 !=0) {
            return false;
        }
        sum = 0;
        for (int i=0; i<matrix.length; i++) {
            sum += matrix[i][column];
        }
        if (sum % 2 != 0) {
            return false;
        }
        return true;
    }

    static int[] invalidRow(int[][] matrix) {
        List<Integer> rows = new ArrayList<>();
        for (int i=0; i<matrix.length; i++) {
            int sum = 0;
            for (int j=0; j<matrix.length; j++) {
                sum += matrix[i][j];
            }
            if (sum % 2 != 0) {
                rows.add(i);
            }
        }
        if (rows.size() > 0) {
            int[] result = new int[rows.size()];
            for (int i=0; i<rows.size(); i++) {
                result[i] = rows.get(i);
            }
            return result;
        }
        return new int[] {-1};
    }

    static int[] invalidColunm(int[][] matrix) {
        List<Integer> columns = new ArrayList<>();
        for (int i=0; i<matrix.length; i++) {
            int sum = 0;
            for (int j=0; j<matrix.length; j++) {
                sum += matrix[j][i];
            }
            if (sum % 2 != 0) {
                columns.add(i);
            }
        }
        if (columns.size() > 0) {
            int[] result = new int[columns.size()];
            for (int i=0; i<columns.size(); i++) {
                result[i] = columns.get(i);
            }
            return result;
        }
        return new int[] {-1};
    }
}

