package max5;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Pos {
        int row;
        int column;

        public Pos(int r, int c) {
            row = r;
            column = c;
        }
    }

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        int n = cin.nextInt();

        for (int i=0; i<n; i++) {
            int k = cin.nextInt();
            char[][] matrix = new char[k][10];
            boolean[][] picked = new boolean[k][10];
            int startR = 0;
            int startC = 0;

            String line = cin.nextLine();
            for (int j=0; j<k; j++) {
                line = cin.nextLine();
//                System.out.println(line);
                char[] chars = line.toCharArray();
                for (int t=0; t<chars.length; t++) {
                    matrix[j][t] = chars[t];
                    if (chars[t] == 'S') {
                        startR = j;
                        startC = t;
                    }
                }
            }
            flag = true;
            traverse(matrix, picked, startR, startC);
            if (flag) {
                System.out.println("N");
            }
        }

    }

    static boolean flag = true;

    static void traverse(char[][] matrix, boolean[][] picked, int row, int column) {
        if (isComplete(matrix, picked)) {
            if (flag) {
                System.out.println("Y");
                flag = false;
            }
            return;
        }
        List<Pos> l = getNeighbers(matrix, picked, row, column);
        if (isDead(l)) {
            return;
        }

        for (int i=0; i<l.size(); i++) {
            Pos p = l.get(i);
            picked[p.row][p.column] = true;
            traverse(matrix, picked, p.row, p.column);
            picked[p.row][p.column] = false;
        }
    }

    static boolean isDead(List<Pos> l) {
        if (l.size() == 0) {
            return true;
        }
        return false;
    }

    static List<Pos> getNeighbers(char[][] matrix, boolean[][] picked, int row, int column) {
        List<Pos> l = new ArrayList<>();
        if (row -1 >= 0 && row -1 < matrix.length) {
            if (matrix[row-1][column] == 'A' && !picked[row-1][column]) {
                Pos p = new Pos(row-1, column);
                l.add(p);
            }
        }
        if (row +1 >= 0 && row +1 < matrix.length) {
            if (matrix[row+1][column] == 'A' && !picked[row+1][column]) {
                Pos p = new Pos(row+1, column);
                l.add(p);
            }
        }
        if (column +1 >= 0 && column +1 <= 8) {
            if (matrix[row][column+1] == 'A' && !picked[row][column+1]) {
                Pos p = new Pos(row, column+1);
                l.add(p);
            }
        }
        if (column -1 >= 0 && column -1 <= 8) {
            if (matrix[row][column-1] == 'A' && !picked[row][column-1]) {
                Pos p = new Pos(row, column-1);
                l.add(p);
            }
        }
        return l;
    }

    static int columnMaxLen = 9;

    static boolean isComplete(char[][] matrix, boolean[][] picked) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<columnMaxLen; j++) {
                if (matrix[i][j] == 'A' && !picked[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

