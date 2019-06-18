import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: holstein
*/
public class holstein {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
//        long start = System.currentTimeMillis();
        int v = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        target = new int[v];
        for (int i=0; i<v; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        g = Integer.parseInt(f.readLine());
        scoops = new int[g][v];
        for (int i=0; i<g; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j=0; j<v; j++) {
                scoops[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> l = traverse();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<l.size(); i++) {
            sb.append(l.get(i)+1);
            sb.append(" ");
        }
        out.print(l.size());
        out.print(" ");
        out.println(sb.toString().trim());

//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }


    static int[] target;
    static int[][] scoops;
    static int g;

    static List<Integer> traverse() {
        List<Integer> tmp = new ArrayList<>();
        List<Integer> tt = null;
        Queue<List<Integer>> queue = new ArrayDeque<>();
        for (int i=0; i<g; i++) {
            tt = new ArrayList<>();
            tt.addAll(tmp);
            tt.add(i);
            queue.add(tt);
        }
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (isEnough(tmp)) break;
            for (int i=tmp.get(tmp.size()-1)+1; i<g; i++) {
                tt = new ArrayList<>();
                tt.addAll(tmp);
                tt.add(i);
                queue.add(tt);
            }
        }
        return tmp;
    }

    static boolean isEnough(List<Integer> l) {
        int[] sum = new int[target.length];
        for (int i=0; i<l.size(); i++) {
            for (int j=0; j<target.length; j++) {
                sum[j] += scoops[l.get(i)][j];
            }
        }
        for (int j=0; j<target.length; j++) {
            if (sum[j] < target[j]) {
                return false;
            }
        }
        return true;
    }
}
