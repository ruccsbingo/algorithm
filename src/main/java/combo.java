import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: combo
*/
public class combo {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("combo.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        int n = Integer.parseInt(f.readLine());

        int[] seqence1 = new int[3];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=0; i<3; i++) {
            seqence1[i] = Integer.parseInt(st.nextToken());
        }

        int[] seqence2 = new int[3];
        StringTokenizer st2 = new StringTokenizer(f.readLine());
        for (int i=0; i<3; i++) {
            seqence2[i] = Integer.parseInt(st2.nextToken());
        }

        int[] tmp = new int[3];
        count(n, seqence1, 0, tmp);
        count(n, seqence2, 0, tmp);

        out.println(set.size());
        out.close();
    }

    static Set<String> set = new HashSet<>();

    static String tmpResult = "";
    static void count(int n, int[] seqence, int pos, int[] tmp) {
        if (pos == 3) {
            tmpResult = "";
            for (int i=0; i<tmp.length; i++) {
                tmpResult += tmp[i] + ",";
            }
            set.add(tmpResult);
//            System.out.println(tmpResult);
            return;
        }

        int tk;
        for (int k = seqence[pos] - 2; k <= seqence[pos] + 2; k++) {
            tk = k;
            if (k <= 0) {
                tk = n + k;
            }
            if (k > n) {
                tk = k - n;
            }
            if (tk <= 0) continue;
            if (tk > n) continue;
            tmp[pos] = tk;
            count(n, seqence, pos+1, tmp);
        }
    }
}
