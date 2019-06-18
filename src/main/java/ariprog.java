import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: ariprog
*/
public class ariprog {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        writer = out;
//        long start = System.currentTimeMillis();
        int n = Integer.parseInt(f.readLine());
        int m = Integer.parseInt(f.readLine());
        initSet(m);
        output(n, m);
        if (flag) {
            out.println("NONE");
        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static PrintWriter writer;
    static boolean flag = true;

    static BitSet set = new BitSet(200010);

    static void initSet(int m) {
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=m; j++) {
                set.set(i*i + j*j);
            }
        }
    }

    static void output(int n, int m) {
        for (int b=1; b<=(2*m*m)/(n-1); b++) {
            for (int a=0; a<=2*m*m-(n-1)*b; a++) {
                int i=0;
//                if (a==5 && b==20) {
//                    System.out.println(a + " " + b);
//                }
                for (; i<n; i++) {
                    if (!set.get(a+i*b)) {
                        break;
                    }
                }
                if (i == n) {
                    writer.println(a + " " + b);
                    flag = false;
//                    System.out.println(a + " " + b);
                }
            }
        }
    }

    static boolean isBisquare(int num, int m) {
        for (int i=0; i<=num/2+1; i++) {
            if (i<=m*m && num-i<=m*m && isSquare(i) && isSquare(num-i)) {
                return true;
            }
        }
        return false;
    }

    static boolean isSquare(int num) {
        int i;
        for(i=1; num>0; i+=2)
        {
            num-=i;
        }
        if(num==0)
            return true;
        else
            return false;
    }

}
