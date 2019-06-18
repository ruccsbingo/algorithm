/*
ID: soldier7
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        int np = Integer.parseInt(st.nextToken());    // first integer
        Map<String, Integer> accounts = new HashMap<>();
        List<String> names = new ArrayList<>();
        Map<String, Integer> initialMoneys = new HashMap<>();
        for (int i=0; i<np; i++) {
            String name = f.readLine();
            accounts.put(name, 0);
            names.add(name);
        }
        while (true) {
            String name = f.readLine();
            if (name == null) {
                break;
            }
            st = new StringTokenizer(f.readLine());
            int money = Integer.parseInt(st.nextToken());    // first integer
            initialMoneys.put(name, money);
            int number = Integer.parseInt(st.nextToken());    // first integer
            for (int i=0; i<number; i++) {
                String receiver = f.readLine();
                accounts.put(receiver, accounts.get(receiver) + money / number);
            }
            if (number > 0) {
                accounts.put(name, accounts.get(name) + money % number);
            } else {
                accounts.put(name, accounts.get(name) + money);
            }
        }

        for (int i=0; i<np; i++) {
            out.println(names.get(i) + " " + (accounts.get(names.get(i)) - initialMoneys.get(names.get(i))));
        }
        out.close();                                  // close the output file
    }
}