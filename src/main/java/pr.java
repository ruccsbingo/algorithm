import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zhangbing on 2019/5/10.
 */
public class pr {

    public static void main(String []args) throws Exception {
        recall("third-party-1w.csv");
    }

    static void recall(String fileName) throws IOException {
        int senseTimeTruePorn = 0;
        int shumeiTruePorn = 0;
        int ksTruePorn = 0;
        int totalTruePorn = 0;

        int moderationPorn = 0;


        //id,senseTime,shumei,ks,moderator
        BufferedReader f = new BufferedReader(new FileReader(fileName));

        for (int i=0; i<10000; i++) {
            String tmp = f.readLine();
            if (tmp == null || tmp.length() == 0) {
                continue;
            }

            int[] result = parse(tmp);
            if (result[4] == 1) {
                moderationPorn ++;
            }
            if (result[1] == 1 && result[1] == result[4]) {
                senseTimeTruePorn ++;
            }
            if (result[2] == 1 && result[2] == result[4]) {
                shumeiTruePorn ++;
            }
            if (result[3] == 1 && result[3] == result[4]) {
                ksTruePorn ++;
            }
            if ((result[1] | result[2] | result[3]) == 1 && result[4] == 1) {
                totalTruePorn ++;
            }
        }

        System.out.println("SenceTime Recall = " + (senseTimeTruePorn / (moderationPorn + 0.0)));
        System.out.println("Shumei Recall = " + (shumeiTruePorn / (moderationPorn + 0.0)));
        System.out.println("ks Recall = " + (ksTruePorn / (moderationPorn + 0.0)));
        System.out.println("total Recall = " + (totalTruePorn / (moderationPorn + 0.0)));

    }

    static void precision(String fileName) throws IOException {
        int senseTimePorn = 0;
        int shumeiPorn = 0;
        int ksPorn = 0;

        int senseTimeTruePorn = 0;
        int shumeiTruePorn = 0;
        int ksTruePorn = 0;

        //id,senseTime,shumei,ks,moderator
        BufferedReader f = new BufferedReader(new FileReader(fileName));

        for (int i=0; i<10000; i++) {
            String tmp = f.readLine();
            int[] result = parse(tmp);
            if (result[1] == 1) {
                senseTimePorn ++;
                if (result[1] == result[4]) {
                    senseTimeTruePorn ++;
                }
            }
            if (result[2] == 1) {
                shumeiPorn ++;
                if (result[2] == result[4]) {
                    shumeiTruePorn ++;
                }
            }
            if (result[3] == 1) {
                ksPorn ++;
                if (result[3] == result[4]) {
                    ksTruePorn ++;
                }
            }
        }

        System.out.println("SenceTime Precision = " + (senseTimeTruePorn / (senseTimePorn + 0.0)));
        System.out.println("Shumei Precision = " + (shumeiTruePorn / (shumeiPorn + 0.0)));
        System.out.println("ks Precision = " + (ksTruePorn / (ksPorn + 0.0)));
    }

    static int[] parse(String str) {
        String[] strs = str.split(",");
        int[] result = new int[5];
        for (int i=1; i<strs.length; i++) {
            result[i] = Integer.parseInt(strs[i]);
        }
        return result;
    }
}
