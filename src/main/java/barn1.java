import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: soldier7
LANG: JAVA
TASK: barn1
*/
public class barn1 {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int boards = Integer.parseInt(st.nextToken());
        int barns = Integer.parseInt(st.nextToken());
        int occupied = Integer.parseInt(st.nextToken());
        List<Integer> positions = new ArrayList<>();
        for (int i=0; i<occupied; i++) {
            positions.add(Integer.parseInt(f.readLine()));
        }
        Collections.sort(positions);
        int result = mininume(boards, positions);
        out.println(result);
        out.close();
    }

    static int mininume(int boards, List<Integer> positions) {
        int result = 0;
        int usedBoards = 0;
        List<Integer> gaps = new ArrayList<>();
        result += 1;
        usedBoards += 1;
        for (int i=1; i<positions.size(); i++) {
            int gap = positions.get(i) - positions.get(i-1);
            if (gap == 1) {
                result += 1;
            } else {
                result += 1;
                usedBoards += 1;
                gaps.add(gap);
            }

        }
        Collections.sort(gaps);
        int j = 0;
        while (usedBoards > boards) {
            result += gaps.get(j) - 1;
            j++;
            usedBoards -= 1;
        }
        return result;
    }
}
