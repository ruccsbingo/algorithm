import java.io.*;

/*
ID: soldier7
LANG: JAVA
TASK: skidesign
*/
public class skidesign {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        int n = Integer.parseInt(f.readLine());
        int[] data = new int[n];
        for (int i=0; i<n; i++) {
             data[i] = Integer.parseInt(f.readLine());
        }
        out.println(minimume(data));
        out.close();
    }

    static int minimume(int[] data) {
        int upperLimit = 0;
        int lowerLimit = 0;

        for (int i=0; i<data.length; i++) {
            if (data[i] > upperLimit) {
                upperLimit = data[i];
            }
            if (data[i] < lowerLimit) {
                lowerLimit = data[i];
            }
        }

        int min = Integer.MAX_VALUE;
        int tmp;
        for (int i=lowerLimit; i<=upperLimit - 17; i++) {
            tmp = 0;
            for (int j=0; j<data.length; j++) {
                if (data[j] < i) {
                    tmp += (data[j]-i) * (data[j]-i);
                }
                if (data[j] > i+17) {
                    tmp += (data[j]-i-17) * (data[j]-i-17);
                }
            }
            if (min > tmp) {
                min = tmp;
            }
        }
        return min;
    }
}
