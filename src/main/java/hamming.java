import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: hamming
*/
public class hamming {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        // input file name goes above
        out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
//        long start = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        count = (int)Math.pow(2, b);
        initDistance();
//        List<Integer> l = traverse();
        List<Integer> tmp = new ArrayList<>();
        dfs(tmp);
//        StringBuilder sb = new StringBuilder();
//        for (int i=0; i<l.size(); i++) {
//            sb.append(l.get(i));
//            sb.append(" ");
//        }
//        out.println(sb.toString().trim());
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static int[][] distance;
    static int n;
    static int b;
    static int count;
    static int d;

    static PrintWriter out;

    static void print(List<Integer> l) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<l.size(); i++) {
            sb.append(l.get(i));
            if ((i+1) % 10 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        out.println(sb.toString().trim());
    }

    static void initDistance() {
        distance = new int[count][count];
        for (int i=0; i<count; i++) {
            for (int j=0; j<count; j++) {
                distance[i][j] = getDistance(i, j);
            }
        }
    }

    static int getDistance(int i, int j) {
        int result = 0;
        int t = i ^ j;
        int tmp;
        while (t!=0) {
            tmp = t & 1;
            if (tmp != 0) {
                result ++;
            }
            t = t>>>1;
        }
        return result;
    }

    static boolean isEnough(List<Integer> l) {
        return l.size() >= n;
    }

    static List<Integer> getNeighbors(List<Integer> l) {
        List<Integer> r = new ArrayList<>();
        if (l.isEmpty()) {
            for (int i=0; i<count; i++) {
                r.add(i);
            }
            return r;
        }

        List<BitSet> lb = new ArrayList<>();
        for (int i=0; i<l.size(); i++) {
            BitSet bs = new BitSet(count);
            for (int j=l.get(i)+1; j<count; j++) {
                if (distance[l.get(i)][j] >= d) {
                    bs.set(j, true);
                }
            }
            lb.add(bs);
        }
        BitSet sumBs = lb.get(0);
        for (int i=1; i<lb.size(); i++) {
            sumBs.and(lb.get(i));
        }
        for(int i=0; i<count; i++) {
            if (sumBs.get(i)) {
                r.add(i);
            }
        }
        return r;
    }

    static boolean flag = false;

    static void dfs(List<Integer> l) {
        if (flag) {
            return;
        }
        if (isEnough(l)) {
            flag = true;
            print(l);
            return;
        }

        List<Integer> neighbors = getNeighbors(l);

        for (int i=0; i<neighbors.size(); i++) {
            l.add(neighbors.get(i));
            dfs(l);
            l.remove(l.size()-1);
        }
    }

    static List<Integer> traverse() {
        List<Integer> result = new ArrayList<>();
        Queue<List<Integer>> queue = new ArrayDeque<>();
        List<Integer> tt;
        for (int i=0; i<count; i++) {
            tt = new ArrayList<>();
            tt.add(i);
            queue.add(tt);
        }
        List<Integer> tmp;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            print(tmp);
            if (isEnough(tmp)) {
                result = tmp;
                break;
            }
            List<Integer> neighbors = getNeighbors(tmp);
            for (int i=0; i<neighbors.size(); i++) {
                tt = new ArrayList<>();
                tt.addAll(tmp);
                tt.add(neighbors.get(i));
                queue.add(tt);
            }
        }
        return result;
    }

}
