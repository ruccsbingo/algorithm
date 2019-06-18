package max6;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.*;

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
            String[][] matrix = new String[k][];
            boolean[][] picked = new boolean[k][];
            int startR = 0;
            int startC = 0;

            String line = cin.nextLine();
            for (int j=0; j<k; j++) {
                line = cin.nextLine();
//                System.out.println(line);
                String[] chars = line.split(" ");
                matrix[j] = new String[chars.length];
                picked[j] = new boolean[chars.length];
                for (int t=0; t<chars.length; t++) {
                    matrix[j][t] = chars[t];
                    if (chars[t].equals("GO")) {
                        startR = j;
                        startC = t;
                    }
                }
            }
            flag = true;
            backward = getAllNeighbers(matrix);
            traverse(matrix, picked, startR, startC);
            if (flag) {
                System.out.println("0 0");
            }
        }

    }

    static boolean flag = true;

    static void traverse(String[][] matrix, boolean[][] picked, int row, int column) {
        if (isComplete(matrix, picked, row, column)) {
            if (flag) {
                System.out.println((column+1) + " " + (row+1));
                flag = false;
            }
            return;
        }
        List<Pos> l = getNeighbers(picked, row, column);
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

    static String key(int r, int c) {
        return r + "," + c;
    }

    static int[] parseKey(String key) {
        String[] tmps = key.split(",");
        int[] rc = new int[2];
        rc[0] = Integer.parseInt(tmps[0]);
        rc[1] = Integer.parseInt(tmps[1]);
        return rc;
    }

    static Map<String, List<String>> getAllNeighbers(String[][] matrix) {
        backward = new HashMap<>();
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                Pos p = getNextPos(matrix, i, j) ;
                if (p == null) {
                    continue;
                }
                if (backward.containsKey(key(p.row, p.column))) {
                    backward.get(key(p.row, p.column)).add(key(i, j));
                } else {
                    List<String> l = new ArrayList<>();
                    l.add(key(i, j));
                    backward.put(key(p.row, p.column), l);
                }
            }
        }
        return backward;
    }

    static Pos getNextPos(String[][] matrix, int row, int column) {
        String str = matrix[row][column];
        char[] chars = str.toCharArray();
        if (chars.length < 2 || str.equals("GO")) {
            return null;
        }
        int step = Integer.parseInt(chars[1]+"");
        switch (chars[0]) {
            case 'G':
                break;
            case 'R':
                if (column + step < matrix[row].length) {
                    return new Pos(row, column + step);
                }
                break;
            case 'L':
                if (column - step >= 0) {
                    return new Pos(row, column - step);
                }
                break;
            case 'D':
                if (row + step < matrix.length) {
                    return  new Pos(row + step, column);
                }
                break;
            case 'U':
                if (row - step >= 0) {
                    return new Pos(row-step, column);
                }
                break;
        }
        return null;
    }

    static Map<String, List<String>> backward = new HashMap<>();

    static List<Pos> getNeighbers(boolean[][] picked, int row, int column) {
        List<Pos> l = new ArrayList<>();

        String key = key(row, column);
        List<String> tmp = backward.get(key);
        if (tmp == null) {
            return l;
        }
        for (int i=0; i<tmp.size(); i++) {
            int[] rc = parseKey(tmp.get(i));
            if (picked[rc[0]][rc[1]]) {
                continue;
            } else {
                l.add(new Pos(rc[0], rc[1]));
            }
        }

        return l;
    }


    static boolean isComplete(String[][] matrix, boolean[][] picked, int row, int column) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                if (!matrix[i][j].equals("GO") && !picked[i][j]) {
                    return false;
                }
            }
        }
            return true;
    }
}

