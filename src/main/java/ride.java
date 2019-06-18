/*
ID: soldier7
LANG: JAVA
TASK: ride
*/
import java.io.*;

class ride {
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        while (true) {
            String d1 = f.readLine();
            if (d1 == null) {
                break;
            }
            String d2 = f.readLine();
            char[] elems1 = d1.toCharArray();
            char[] elems2 = d2.toCharArray();
            int sum1=1, sum2=1;
            int result1, result2;
            for (int i=0; i<elems1.length; i++) {
                sum1 *= (elems1[i] - 'A' + 1);
            }
            result1 = sum1 % 47;
            for (int i=0; i<elems2.length; i++) {
                sum2 *= (elems2[i] - 'A' + 1);
            }
            result2 = sum2 % 47;
            if (result1 == result2) {
                out.println("GO");
            } else {
                out.println("STAY");
            }
        }
        out.close();                                  // close the output file
    }
}