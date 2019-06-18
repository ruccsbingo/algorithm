package permutation;

import java.util.*;

/**
 * Created by zhangbing on 2018/12/15.
 */
public class Main {
    static long num = 0;
    static Map<String, Boolean> exist = new HashMap<>();
    public static void main(String args[]){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();

        int[] data = new int[n];
//        List<Integer> data = new ArrayList<>();
//        data.add(1);
//        data.add(2);
//        data.add(3);
//        data.add(4);

        for (int i=0; i<n; i++) {
            data[i] = cin.nextInt();
        }

        long start = System.currentTimeMillis();

        List<Integer> l = new ArrayList<>();
//        permutation(k,1, data, l);
        permutation(k, 0, data.length-1, data);
        Map<Integer, Integer> duplicatedMap = duplicatedNumber(data);
        addDuplicated(duplicatedMap);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(num);
    }

    static void permutation(int k, int start, int end, int[] data) {
        if (start == end) {
            if (isValid(k, data)) {
                if (!isExist(data)) {
                    num++;
//                    print(l);
                }
            }
            return;
        }

        for (int i=start; i<= end; i++) {
            swap(data, start, i);
            permutation(k, start + 1, end, data);
            swap(data, start, i);
        }
    }

    static void swap(int[] data, int start, int end) {
        int tmp = data[start];
        data[start] = data[end];
        data[end] = tmp;
    }

    static void permutation(int k, int n, List<Integer> data, int[] l) {
        if (l.length == data.size()) {
            if (isValid(k, l)) {
                if (!isExist(l)) {
                    num++;
//                    print(l);
                }
            }
            return;
        }
        for (int i=0; i<n; i++) {
//            l.add(i, data.get(n-1));
            permutation(k,n+1, data, l);
//            l.remove(i);
        }
    }

    static void addDuplicated(Map<Integer, Integer> duplicated) {
        for (Map.Entry<Integer, Integer> entry : duplicated.entrySet()) {
            if (entry.getValue() > 1) {
                num *= factorial(entry.getValue());
            }
        }
    }

    static long factorial(int n) {
        long result = 1;
        for (int i=1; i<=n; i++) {
            result *= i;
        }
        return result;
    }

    static Map<Integer, Integer> duplicatedNumber(int[] l) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i=0; i<l.length; i++) {
            if (result.containsKey(l[i])) {
                result.put(l[i], result.get(l[i]) + 1);
            } else {
                result.put(l[i], 1);
            }
        }
        return result;
    }

    static boolean isExist(int[] l) {
        List<String> signatures = signature(l);
        for (int i=0; i<signatures.size(); i++) {
            if (exist.containsKey(signatures.get(i))) {
                return true;
            }
        }
        for (int i=0; i<signatures.size(); i++) {
            exist.put(signatures.get(i), true);
        }
        return false;
    }

    static List<String> signature(int[] l){
        int max = -1;
//        int position = -1;
        for (int i=0; i<l.length; i++){
            if (max < l[i]) {
                max = l[i];
//                position = i;
            }
        }

        List<Integer> posArray = new ArrayList<>();
        for (int i=0; i<l.length; i++) {
            if (l[i] == max) {
                posArray.add(i);
            }
        }

        List<String> signatures = new ArrayList<>();
        for (int j=0; j<posArray.size(); j++) {
            String result = "";
            int position = posArray.get(j);
            for (int i=0; i<l.length; i++) {
                int p = (position + i + l.length) % l.length;
                result += l[p] + ",";
            }
            signatures.add(result);
        }
//        System.out.println(result);
        return signatures;
    }

    static boolean isValid(int k, int[] l) {
        for (int i=0; i<l.length-1; i++){
            if (Math.abs(l[i]-l[i+1]) > k) {
                return false;
            }
        }
        if (Math.abs(l[0] - l[l.length-1]) > k) {
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
