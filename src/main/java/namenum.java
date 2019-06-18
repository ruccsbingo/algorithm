import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: namenum
*/
public class namenum {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        writer = out;

        String data = f.readLine();
        char[] chars = data.toCharArray();
        int[] digits = new int[data.length()];
        for (int i=0; i<chars.length; i++) {
            digits[i] = Integer.parseInt(chars[i]+"");
        }
        names(digits);
//        out.println(result);
        if (flag) {
            out.println("NONE");
        }
        out.close();
    }

    static void names(int[] digits) throws IOException {
        initDict();
        initDigitMap();
        char[] chars = new char[digits.length];
        output(0, digits, chars);
    }
    static boolean flag = true;
    static PrintWriter writer;
    static Set<String> dicts = new HashSet<>();
    static Map<Integer, List<Character>> digitMap = new HashMap<>();

    static void initDict() throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dict.txt"));
        while (true) {
            String data = f.readLine();
            if (data == null) break;
            dicts.add(data);
        }
    }

    static void initDigitMap() {
//        2: A,B,C     5: J,K,L    8: T,U,V
//        3: D,E,F     6: M,N,O    9: W,X,Y
//        4: G,H,I     7: P,R,S
        List<Character> l;
        l = new ArrayList<>();
        l.add('A');
        l.add('B');
        l.add('C');
        digitMap.put(2, l);

        l = new ArrayList<>();
        l.add('D');
        l.add('E');
        l.add('F');
        digitMap.put(3, l);

        l = new ArrayList<>();
        l.add('G');
        l.add('H');
        l.add('I');
        digitMap.put(4, l);

        l = new ArrayList<>();
        l.add('J');
        l.add('K');
        l.add('L');
        digitMap.put(5, l);

        l = new ArrayList<>();
        l.add('M');
        l.add('N');
        l.add('O');
        digitMap.put(6, l);

        l = new ArrayList<>();
        l.add('P');
        l.add('R');
        l.add('S');
        digitMap.put(7, l);

        l = new ArrayList<>();
        l.add('T');
        l.add('U');
        l.add('V');
        digitMap.put(8, l);

        l = new ArrayList<>();
        l.add('W');
        l.add('X');
        l.add('Y');
        digitMap.put(9, l);
    }

    static String toString(char[] chars) {
        StringBuilder builder = new StringBuilder();
        for(char ch : chars) {
            builder.append(ch);
        }
        return builder.toString();
    }

    static void output(int i, int[] digits, char[] chars) {
        if (i >= digits.length) {
            String result = toString(chars);
//            System.out.println(result);
            if (dicts.contains(result)) {
                flag = false;
                writer.println(result);
//                System.out.println(result);
            }
            return ;
        }
        List<Character> candidates = digitMap.get(digits[i]);
        for (char ch : candidates) {
            chars[i] = ch;
            output(i+1, digits, chars);
        }
    }
}

