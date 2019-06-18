import javafx.util.Pair;

import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: milk
*/
public class milk {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("milk.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        SortedMap<Integer, Integer> data = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int units = Integer.parseInt(st.nextToken());
        int farmers = Integer.parseInt(st.nextToken());
        for (int i=0; i<farmers; i++) {
            st = new StringTokenizer(f.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (data.containsKey(k)) {
                data.put(k, data.get(k) + v);
            } else {
                data.put(k, v);
            }
        }
        int result = output(units, data);
        out.println(result);
        out.close();
    }

    static int output(int units, Map<Integer, Integer> map) {
        int tmp = units;
        int money = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (tmp <= 0) {
                break;
            }
            if (tmp >= entry.getValue()) {
                tmp = tmp - entry.getValue();
                money += entry.getValue() * entry.getKey();
            } else {
                money += tmp * entry.getKey();
                tmp = 0;
            }
        }
        return money;
    }

}
