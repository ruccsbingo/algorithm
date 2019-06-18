import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: milk3
*/
public class milk3 {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
//        long start = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(f.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        State s = new State(0, 0, C);
//        test();
        traverse(s);
        Integer[] result = new Integer[]{};
        result = set.toArray(result);
        Arrays.sort(result);

        StringBuilder tmp = new StringBuilder();
        for (int i=0; i<result.length; i++) {
            tmp.append(result[i]).append(" ");
        }
        String t = tmp.toString().trim();
        out.println(t);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

//    static void test() {
//        State s = new State(1, 2, 3);
//        set.add(s.toString());
//        State s1 = new State(1, 2, 3);
//        set.add(s1.toString());
//        System.out.println(set);
//    }

    static public class State {
        Integer a;
        Integer b;
        Integer c;

        public State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String toString() {
            return this.a + "," + this.b + "," + this.c;
        }

        public boolean equals(Object o) {
            if (o instanceof State) {
                State s = (State) o;
                return Objects.equals(this.a, s.a)
                        && Objects.equals(this.b, s.b)
                        && Objects.equals(this.c, s.c);
            }
            return false;
        }
    }

    static Set<Integer> set = new HashSet<>();
    static Map<String, Boolean> map = new HashMap<>();
    static int A, B, C;


    public static void traverse(State state) {
        Queue<State> queue = new ArrayDeque<>();
        queue.add(state);

        State s;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (!map.containsKey(s.toString())) {
                if (s.a == 0) {
                    set.add(s.c);
                }
//                System.out.println(s.c);
                List<State> neighbors = getNeighbors(s);
                for (State t : neighbors) {
                    queue.add(t);
                }
            }
            map.put(s.toString(), true);
        }
    }

    static List<State> getNeighbors(State s) {
        List<State> l = new ArrayList<>();
        State tmp;
        if (s.a > 0) {
            tmp = new State(s.a, s.b, s.c);
            if (tmp.a + tmp.b > B) {
                tmp.a = tmp.a + tmp.b - B;
                tmp.b = B;
            } else {
                tmp.b = tmp.a + tmp.b;
                tmp.a = 0;
            }
            if (!map.containsKey(tmp.toString())) {
                l.add(tmp);
            }

            tmp = new State(s.a, s.b, s.c);
            if (tmp.a + tmp.c > C) {
                tmp.a = tmp.a + tmp.c - C;
                tmp.c = C;
            } else {
                tmp.c = tmp.a + tmp.c;
                tmp.a = 0;
            }
            if (!map.containsKey(tmp.toString())) {
                l.add(tmp);
            }
        }
        if (s.b > 0) {
            tmp = new State(s.a, s.b, s.c);
            if (tmp.b + tmp.a > A) {
                tmp.b = tmp.a + tmp.b - A;
                tmp.a = A;
            } else {
                tmp.a = tmp.a + tmp.b;
                tmp.b = 0;
            }
            if (!map.containsKey(tmp.toString())) {
                l.add(tmp);
            }

            tmp = new State(s.a, s.b, s.c);
            if (tmp.b + tmp.c > C) {
                tmp.b = tmp.b + tmp.c - C;
                tmp.c = C;
            } else {
                tmp.c = tmp.b + tmp.c;
                tmp.b = 0;
            }
            if (!map.containsKey(tmp.toString())) {
                l.add(tmp);
            }
        }

        if (s.c > 0) {
            tmp = new State(s.a, s.b, s.c);
            if (tmp.a + tmp.c > A) {
                tmp.c = tmp.a + tmp.c - A;
                tmp.a = A;
            } else {
                tmp.a = tmp.a + tmp.c;
                tmp.c = 0;
            }
            if (!map.containsKey(tmp.toString())) {
                l.add(tmp);
            }

            tmp = new State(s.a, s.b, s.c);
            if (tmp.b + tmp.c > B) {
                tmp.c = tmp.b + tmp.c - B;
                tmp.b = B;
            } else {
                tmp.b = tmp.b + tmp.c;
                tmp.c = 0;
            }
            if (!map.containsKey(tmp.toString())) {
                l.add(tmp);
            }
        }
        return l;
    }
}
