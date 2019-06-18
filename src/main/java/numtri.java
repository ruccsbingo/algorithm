import java.io.*;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
ID: soldier7
LANG: JAVA
TASK: numtri
*/
public class numtri {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
//        long start = System.currentTimeMillis();
        StringTokenizer st;
        int i, j=0;
        int n = Integer.parseInt(f.readLine());
        for (i=0; i<n; i++) {
            st = new StringTokenizer(f.readLine());
            for (j=0; j<=i; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        totalLevel = i;
        totalColumn = j;
//        int result = max(0, 0);
        int result = maxDp();
        out.println(result);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static int[][] array = new int[1000][1000];
    static int[][] M = new int[1000][1000];
    static int totalLevel;
    static int totalColumn;

    public static int max(int level, int column) {
        if (level >= totalLevel || column >= totalColumn) {
            return 0;
        }

        return Math.max(max(level+1, column), max(level+1, column+1)) + array[level][column];
    }

    public static int maxDp() {
        for (int level=totalLevel-1; level>=0; level--) {
            for (int column=totalColumn-(totalLevel - level); column>=0; column--) {
                if (level+1>=totalLevel) {
                    M[level][column] = array[level][column];
                } else {
                    M[level][column] = Math.max(M[level+1][column], M[level+1][column+1]) + array[level][column];
                }
            }
        }
        return M[0][0];
    }

}
