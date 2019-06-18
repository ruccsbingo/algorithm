import java.io.*;

/*
ID: soldier7
LANG: JAVA
TASK: beads
*/
public class beads {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("beads.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int n = Integer.parseInt(f.readLine());
        String str = f.readLine();
        String str2 = str + str;
        int result = max(str2);
        out.println(result);
        out.close();
    }

    static int backtracking(char [] data, int i, char firstPart) {
        for (int j=i-1; j>=0; j--) {
            if (data[j] == firstPart) {
                return j;
            }
        }
        return 0;
    }

    static boolean isChanged(char [] data, int i, char firstPartChar, char secondPartChar, int changeCounter) {
        if (i==0) {
            return false;
        }
        if (changeCounter == 0) {
            if (data[i] != 'w' && data[i] != firstPartChar) {
                return true;
            }
        }
        if (changeCounter == 1) {
            if (data[i] != 'w' && data[i] != secondPartChar) {
                return true;
            }
        }
        return false;
    }

    static int max(String str) {
        char [] data = str.toCharArray();
        int max = 0;
        int index = 0;

        int changeCounter = 0;
        char firstPartChar = ' ';
        char secondPartChar = ' ';
        int length = 0;
        while (index < data.length && length < data.length / 2) {
            if (changeCounter == 0) {
                if (data[index] != 'w' && firstPartChar == ' ') {
                    firstPartChar = data[index];
                }
            }
            if (isChanged(data, index, firstPartChar, secondPartChar, changeCounter)) {
                changeCounter ++;
                if (changeCounter == 1) {
                    if (data[index] != 'w' && secondPartChar == ' ') {
                        secondPartChar = data[index];
                    }
                }
                if (changeCounter == 2) {
                    if (length > max) {
                        max = length - 1;
                        System.out.println("max=" + max + " start=" + (index - length + 1)  + " end=" + (index));
                    }
                    index = backtracking(data, index, firstPartChar);
                    length = 0;
                    changeCounter = 0;
                    firstPartChar = ' ';
                    secondPartChar = ' ';
                }
            }
            index++;
            length ++;
        }
        if (length - 1 > max) {
            max = length;
        }
        return max;
    }
}
