import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: milk2
*/
public class milk2 {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        SortedMap<Integer, List<Integer>> data = new TreeMap<>();
        int n = Integer.parseInt(f.readLine());
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int left = Integer.parseInt(st.nextToken());    // first integer
            int right = Integer.parseInt(st.nextToken());    // second integer
            if (data.containsKey(left)) {
                data.get(left).add(1);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                data.put(left, list);
            }

            if (data.containsKey(right)) {
                data.get(right).add(2);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(2);
                data.put(right, list);
            }
        }
        int[] results = max(data);
        out.println(results[0] + " " + results[1]);
        out.close();
    }

    static int[] max(SortedMap<Integer, List<Integer>> data) {
        int[] result = new int[2];
        int maxLenght = 0, maxIdel = 0;
        int rightCounter = 0;
        int start = 0, end = 0;
        for (Map.Entry<Integer, List<Integer>> entry : data.entrySet()) {
            if (rightCounter == 0) {
                int tmp = end - start;
                if (tmp > maxLenght) {
                    maxLenght = tmp;
                }
                start = entry.getKey();
                if (end != 0) {
                    int tmp2 = start - end;
                    if (tmp2 > maxIdel) {
                        maxIdel = tmp2;
                    }
                }
            }
            for (Integer elem : entry.getValue()) {
                if (elem == 1) {
                    rightCounter ++;
                } else if (elem == 2) {
                    rightCounter --;
                    end = entry.getKey();
                }
            }
        }
        if (rightCounter == 0) {
            int tmp = end - start;
            if (tmp > maxLenght) {
                maxLenght = tmp;
            }
            int tmp2 = start - end;
            if (tmp2 > maxIdel) {
                maxIdel = tmp2;
            }
        }
        result[0] = maxLenght;
        result[1] = maxIdel;
        return result;
    }
}
