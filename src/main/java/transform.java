import java.io.*;
import java.util.Arrays;

/*
ID: soldier7
LANG: JAVA
TASK: transform
*/
public class transform {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int n = Integer.parseInt(f.readLine());
        char[][] src = new char[n][];
        char[][] dst = new char[n][];
        for (int i=0; i<n; i++) {
            String str = f.readLine();
            src[i] = str.toCharArray();
        }
        for (int i=0; i<n; i++) {
            String str = f.readLine();
            dst[i] = str.toCharArray();
        }

        int min = chooseMinimume(src, dst);
        out.println(min);
        out.close();
    }

    public enum TransformType {
        degree_90, degree_180, degree_270, reflection
    }

    static int chooseMinimume(char[][] src, char[][] dst) {
        TransformType[] types = TransformType.values();
        char[][] tmpDst;
        for (int i=0; i<types.length; i++) {
            tmpDst = transform(src, types[i]);
            if (isEqual(tmpDst, dst)) {
                return i+1;
            }
        }
        if (isCombination(src, dst)) {
            return 5;
        }
        if (isEqual(src, dst)) {
            return 6;
        }
        return 7;
    }

    static char[][] transform(char[][] src, TransformType type){
        char[][] dst;
        switch (type) {
            case degree_90:
                dst = rotate90(src);
                break;
            case degree_180:
                dst = rotate180(src);
                break;
            case degree_270:
                dst = rotate270(src);
                break;
            case reflection:
                dst = reflection(src);
                break;
            default:
                throw new EnumConstantNotPresentException(type.getClass(), type.toString());
        }
        return dst;
    }

    static char[][] rotate90(char[][] src) {
        char[][] dst = deepCopy(src);
        dst = rotateRightDiagonal(dst);
        dst = rotateHorizontally(dst);
        return dst;
    }

    static char[][] rotate180(char[][] src) {
        char[][] dst = deepCopy(src);
        dst = rotateRightDiagonal(dst);
        dst = rotateLeftDiagonal(dst);
        return dst;
    }

    static char[][] rotate270(char[][] src) {
        char[][] dst = deepCopy(src);
        dst = rotateLeftDiagonal(dst);
        dst = rotateHorizontally(dst);
        return dst;
    }

    static char[][] reflection(char[][] src) {
        char[][] dst = deepCopy(src);
        dst = rotateVertically(dst);
        return dst;
    }

    static boolean isCombination(char[][] src, char[][] dst) {
        char[][] tmpSrc = reflection(src);
        char[][] tmpDst;
        tmpDst = rotate90(deepCopy(tmpSrc));
        if (isEqual(tmpDst, dst)) {
            return true;
        }
        tmpDst = rotate180(deepCopy(tmpSrc));
        if (isEqual(tmpDst, dst)) {
            return true;
        }
        tmpDst = rotate270(deepCopy(tmpSrc));
        if (isEqual(tmpDst, dst)){
            return true;
        }

        return false;
    }

    static char[][] rotateLeftDiagonal(char[][] src) {
        char tmp;
        int m = src.length;
        int n = src[0].length;
        for (int i=0; i<m; i++) {
            for (int j=i; j<n; j++) {
                tmp = src[i][j];
                src[i][j] = src[j][i];
                src[j][i] = tmp;
            }
        }
        return src;
    }

    static char[][] rotateRightDiagonal(char[][] src) {
        char tmp;
        int m = src.length;
        int n = src[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n-i; j++) {
                tmp = src[i][j];
                src[i][j] = src[m-1-j][n-1-i];
                src[m-1-j][n-1-i] = tmp;
            }
        }
        return src;
    }

    static char[][] rotateHorizontally(char[][] src) {
        char tmp;
        int m = src.length;
        int n = src[0].length;
        for (int i=0; i<m/2; i++) {
            for (int j=0; j<n; j++) {
                tmp = src[i][j];
                src[i][j] = src[m-1-i][j];
                src[m-1-i][j] = tmp;
            }
        }
        return src;
    }

    static char[][] rotateVertically(char[][] src) {
        char tmp;
        int m = src.length;
        int n = src[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n/2; j++) {
                tmp = src[i][j];
                src[i][j] = src[i][n-1-j];
                src[i][n-1-j] = tmp;
            }
        }
        return src;
    }

    static boolean isEqual(char[][] src, char[][] dst) {
        return Arrays.deepEquals(src, dst);
    }

    static void print(char[][] arr){
        for (char i=0; i<arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    static char[][] deepCopy(char[][] src) {
        int m = src.length;
        int n = src[0].length;
        char[][] dst = new char[m][n];
        for (int i=0; i<m; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, n);
        }
        return dst;
    }
}
