import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: preface
*/
public class preface {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("preface.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
        int n = Integer.parseInt(f.readLine());
        init();
        Map<Character, Integer> result = caculate(n);

        char[] symbals = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        for (int i=0; i<symbals.length; i++) {
            if (result.containsKey(symbals[i])) {
                out.print(symbals[i]);
                out.print(" ");
                out.print(result.get(symbals[i]));
                out.println();
            }
        }

        out.close();
    }

    static Map<Integer, String> digits = new HashMap<>();

    static void init() {
        digits.put(1, "I");
        digits.put(2, "II");
        digits.put(3, "III");
        digits.put(4, "IV");
        digits.put(5, "V");
        digits.put(6, "VI");
        digits.put(7, "VII");
        digits.put(8, "VIII");
        digits.put(9, "IX");
        digits.put(10, "X");
        digits.put(20, "XX");
        digits.put(30, "XXX");
        digits.put(40, "XL");
        digits.put(50, "L");
        digits.put(60, "LX");
        digits.put(70, "LXX");
        digits.put(80, "LXXX");
        digits.put(90, "XC");
        digits.put(100, "C");
        digits.put(200, "CC");
        digits.put(300, "CCC");
        digits.put(400, "CD");
        digits.put(500, "D");
        digits.put(600, "DC");
        digits.put(700, "DCC");
        digits.put(800, "DCCC");
        digits.put(900, "CM");
        digits.put(1000, "M");
        digits.put(2000, "MM");
        digits.put(3000, "MMM");
    }

    static String translate(int number) {
        int tmp = number;
        int digit;
        int count = 1;
        String result = "";

        while (tmp > 0) {
            digit = tmp % 10;
            digit = digit * count;
            if (digit != 0) {
                result = digits.get(digit) + result;
            }

            count *= 10;
            tmp /= 10;
        }
        return result;
    }

    static Map<Character, Integer> caculate(int number) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i=1; i<=number; i++) {
            String tmp = translate(i);
            char[] symbols = tmp.toCharArray();
            for (int j=0; j<symbols.length; j++) {
                if (result.containsKey(symbols[j])) {
                    int count = result.get(symbols[j]);
                    result.put(symbols[j], ++count);
                } else {
                    result.put(symbols[j], 1);
                }
            }
        }
        return result;
    }

}
