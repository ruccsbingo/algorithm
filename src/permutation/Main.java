package permutation;

import java.util.*;

/**
 * Created by zhangbing on 2018/12/15.
 */
public class Main {
    static int num = 0;
    static Map<String, Boolean> exist = new HashMap<>();
    public static void main(String args[]){
        Scanner cin = new Scanner(System.in);
        List<Integer> data = new ArrayList<>();
//        data.add(1);
//        data.add(2);
//        data.add(3);
//        data.add(4);

        int n = cin.nextInt();
        int k = cin.nextInt();

        for (int i=0; i<n; i++) {
            data.add(cin.nextInt());
        }

        List<Integer> l = new ArrayList<>();
        permutation(k,1, data, l);
        System.out.println(num);
    }

    static void permutation(int k, int n, List<Integer> data, List<Integer> l) {
        if (l.size() == data.size()) {
            if (isValid(k, l)) {
                if (!isExist(l)) {
                    num++;
                }
//                print(l);
            }
            return;
        }
        for (int i=0; i<n; i++) {
            l.add(i, data.get(n-1));
            permutation(k,n+1, data, l);
            l.remove(i);
        }
    }

    static boolean isExist(List<Integer> l) {
        String signature = signature(l);
        if (exist.containsKey(signature)) {
            return true;
        }
        exist.put(signature, true);
        return false;
    }

    static String signature(List<Integer> l){
        int max = -1;
        int position = -1;
        for (int i=0; i<l.size(); i++){
            if (max < l.get(i)) {
                max = l.get(i);
                position = i;
            }
        }

        String result = "";
        for (int i=0; i<l.size(); i++) {
            int p = (position + i + l.size()) % l.size();
            result += l.get(p) + ",";
        }
//        System.out.println(result);
        return result;
    }

    static boolean isValid(int k, List<Integer> l) {
        for (int i=0; i<l.size()-1; i++){
            if (Math.abs(l.get(i)-l.get(i+1)) > k) {
                return false;
            }
        }
        if (Math.abs(l.get(0) - l.get(l.size()-1)) > k) {
            return false;
        }
        return true;
    }

    static void print(List<Integer> l) {
        for (int i=0; i<l.size(); i++) {
            System.out.print(l.get(i)+"\t");
        }
        System.out.println();
    }
}
