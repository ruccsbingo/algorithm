import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: wormhole
*/
public class wormhole {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

//        long start = System.currentTimeMillis();

        int n = Integer.parseInt(f.readLine());
        Pos[] holes = new Pos[n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            holes[i] = new Pos(x, y);
        }

        matrixToPos = new HashMap<>();
        for (int i=0; i<n; i++) {
            String key = holes[i].x + "," + holes[i].y;
            matrixToPos.put(key, i);
        }

        matrix = initMatrix(holes);

        Pos[] pairs = new Pos[n];

        count(holes, 0, pairs);
        out.println(result);

//        long end = System.currentTimeMillis();

//        System.out.println(end - start);

        out.close();
    }

    static Map<String, Integer> matrixToPos;

    static int result = 0;

    static class Pos{
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return x + "," + y;
        }
    }


    static Map<String, String> tmpPairs;
    static void count(Pos[] holes, int pos, Pos[] pairs) {
        if (pos == holes.length) {
            for (int i=0; i<holes.length; i++) {
//                if (pairs[0].x == 0 && pairs[0].y == 1) {
//                    pos = pos;
//                }
                if (isStucked(holes, i, pairs)) {
//                    System.out.println(Arrays.toString(holes));
//                    System.out.println(Arrays.toString(pairs));
                    result ++;
                    return;
                }
            }
            return;
        }

        if (pairs[pos] == null) {
            for (int i=pos+1; i<holes.length; i++) {
                if (pairs[i] == null) {
                    pairs[pos] = holes[i];
                    pairs[i] = holes[pos];
                    count(holes, pos + 1, pairs);
                    pairs[pos] = null;
                    pairs[i] = null;
                }
            }
        } else {
            count(holes, pos + 1, pairs);
        }
    }

    static Map<Integer, List<Integer>> matrix;

    static Map<String, String> makeTwoWayPairs(Pos[] holes, Pos[] pairs) {
        Map<String, String> result = new HashMap<>();
        String one, two;
        for (int i=0; i<holes.length; i++) {
            one = holes[i].x + "," + holes[i].y;
            two = pairs[i].x + "," + pairs[i].y;
            result.put(one, two);
            result.put(two, one);
        }
        return result;
    }

    static boolean isStucked(Pos[] holes, int pos, Pos[] pairs) {
        Set<Integer> set = new HashSet<>();
        set.add(pos);
        Pos next = holes[pos];
        int nextPos = pos;
        while (true) {
            next = proceed(pairs, nextPos);
            if (next.x == -1) {
                return false;
            }
            nextPos = matrixToPos.get(next.x + "," + next.y);
            if (isLoop(set, nextPos)) {
                return true;
            }
            set.add(nextPos);
        }
    }

    static boolean isLoop(Set<Integer> set, Integer key) {
        if (set.contains(key)) {
            return true;
        }
        return false;
    }

    static Pos proceed(Pos[] pairs, int pos) {
        int nextX, nextY;

        int xx = pairs[pos].x;
        int yy = pairs[pos].y;

        nextX = getNextX(xx, yy);
        nextY = yy;
        return new Pos(nextX, nextY);
    }

    static int getNextX(int x, int y) {
        int pos = matrix.get(y).indexOf(x);
        if (pos >= matrix.get(y).size() - 1) {
            return -1;
        }
        return matrix.get(y).get(pos + 1);
    }

    static Map<Integer, List<Integer>> initMatrix(Pos[] holes) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i=0; i<holes.length; i++) {
            if (result.containsKey(holes[i].y)) {
                result.get(holes[i].y).add(holes[i].x);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(holes[i].x);
                result.put(holes[i].y, l);
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : result.entrySet()) {
            Collections.sort(entry.getValue());
        }
        return result;
    }
}
