import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: sort3_2
*/
public class sort3_2 {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("sort3_2.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3_2.out")));
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
        int[] array = new int[n];
        for (int i=0; i<n; i++) {
            array[i] = Integer.parseInt(f.readLine());
        }

        sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);

        initBoundaryLine();

        out.println(traverse(new Node(array)));
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }


    static int pos1, pos2;
    static int[] sorted;
    static Set<String> set = new HashSet<>();

    static class Node{
        int[] array;
        boolean flag;

        public Node(boolean flag) {
            this.flag = flag;
        }

        public Node(int[] arr) {
            this.array = Arrays.copyOf(arr, arr.length);
        }

        public String toString() {
            return Arrays.toString(array);
        }
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

    static boolean isSorted(Node n) {
        if (n.flag) return false;
        for (int i=0; i<n.array.length-1; i++) {
            if (n.array[i]>n.array[i+1]) {
                return false;
            }
        }
        return true;
    }

    static List<Node> getNeighbors(Node n) {
        List<Node> l = new ArrayList<>();
        List<Integer> positions = getUnorderPositions(n);
        for (int i=0; i<positions.size(); i++) {
            for (int j=i+1; j<positions.size(); j++) {
//                if (positions.get(i) == 1) {
//                    if (positions.get(j)>pos1) continue;
//                }
//                if (positions.get(i) == 3) {
//                    if (positions.get(j)<=pos2) continue;
//                }
                Node t = new Node(n.array);
                int tmp = t.array[positions.get(i)];
                t.array[positions.get(i)] = t.array[positions.get(j)];
                t.array[positions.get(j)] = tmp;
                l.add(t);
            }
        }
        return l;
    }


    static List<Integer> getUnorderPositions(Node n) {
        List<Integer> l = new ArrayList<>();
        for (int i=0; i<sorted.length; i++) {
            if (sorted[i] != n.array[i]) {
                l.add(i);
            }
        }
        return l;
    }

    static int traverse(Node n) {
        Node t;
        List<Node> list;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(n);
        queue.add(new Node(true));
        int result = 0;
        while (!queue.isEmpty()) {
            t = queue.poll();
            if (isSorted(t)) {
                break;
            }
            if (!t.flag) {
                if (set.contains(t.toString())) {
                    continue;
                }
                set.add(t.toString());
            }
            if (t.flag) {
                result ++;
                queue.add(new Node(true));
                continue;
            }
            list = getNeighbors(t);
//            for (int i=0; i<list.size(); i++) {
//                System.out.println(list.get(i));
//            }
            queue.addAll(list);
        }

        return result;
    }
}
