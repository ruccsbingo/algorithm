import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: frac1
*/
public class frac1 {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
        List<Node> l = traverse(n);
        Collections.sort(l);

        out.println("0/1");
        for (int i=0; i<l.size(); i++) {
            out.println(l.get(i).getEncode());
        }

//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static class Node implements Comparable<Node> {
        int f;
        int d;
        double value;

        public String getEncode() {
            return encode;
        }

        public void setEncode(String encode) {
            this.encode = encode;
        }

        String encode;

        Node(int f, int d, double v, String e) {
            this.f = f;
            this.d = d;
            this.value = v;
            this.encode = e;
        }

        public int compareTo(Node o){
            if (this.value > o.value) {
                return 1;
            } else if (this.value < o.value){
                return -1;
            } else {
                return 0;
            }
        }
    }

    static String encode(int f, int d) {
        return f + "/" + d;
    }

    static String getConcise(int f, int d) {
        int cd = getCommonDivisor(f, d);
        int tmpF = f / cd;
        int tmpD = d / cd;
        return encode(tmpF, tmpD);
    }

    static int getCommonDivisor(int f, int d) {
        int t;
        int biger, smaller;
        if (f >= d) {
            biger = f;
            smaller = d;
        } else {
            biger = d;
            smaller = f;
        }
        if (smaller == 0) {
            return biger;
        }
        while (true) {
            t = biger % smaller;
            biger = smaller;
            smaller = t;
            if (t == 0) {
                break;
            }
        }
        return biger;
    }

    static List<Node> traverse(int n) {
        List<Node> result = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (int f=1; f<=n; f++) {
            for (int d=f; d<=n; d++) {
                String str = getConcise(f, d);
                if (!set.contains(str)) {
                    set.add(str);
                    double v = f/(d+0.0);
                    Node node = new Node(f, d, v, str);
                    result.add(node);
                }
            }
        }

        return result;
    }

}
