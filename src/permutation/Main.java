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

        long start = System.currentTimeMillis();

        List<Integer> l = new ArrayList<>();
//        permutation(k,1, data, l);
        permutation(k, 0, data.size()-1, data);
        Map<Integer, Integer> duplicatedMap = duplicatedNumber(data);
        addDuplicated(duplicatedMap);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(num);
    }

    static void permutation(int k, int start, int end, List<Integer> data) {
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

    static void swap(List<Integer> data, int start, int end) {
        int tmp = data.get(start);
        data.set(start, data.get(end));
        data.set(end, tmp);
    }

    static void permutation(int k, int n, List<Integer> data, List<Integer> l) {
        if (l.size() == data.size()) {
            if (isValid(k, l)) {
                if (!isExist(l)) {
                    num++;
//                    print(l);
                }
            }
            return;
        }
        for (int i=0; i<n; i++) {
            l.add(i, data.get(n-1));
            permutation(k,n+1, data, l);
            l.remove(i);
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

    static Map<Integer, Integer> duplicatedNumber(List<Integer> l) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i=0; i<l.size(); i++) {
            if (result.containsKey(l.get(i))) {
                result.put(l.get(i), result.get(l.get(i)) + 1);
            } else {
                result.put(l.get(i), 1);
            }
        }
        return result;
    }

    static boolean isExist(List<Integer> l) {
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

    static List<String> signature(List<Integer> l){
        int max = -1;
//        int position = -1;
        for (int i=0; i<l.size(); i++){
            if (max < l.get(i)) {
                max = l.get(i);
//                position = i;
            }
        }

        List<Integer> posArray = new ArrayList<>();
        for (int i=0; i<l.size(); i++) {
            if (l.get(i) == max) {
                posArray.add(i);
            }
        }

        List<String> signatures = new ArrayList<>();
        for (int j=0; j<posArray.size(); j++) {
            String result = "";
            int position = posArray.get(j);
            for (int i=0; i<l.size(); i++) {
                int p = (position + i + l.size()) % l.size();
                result += l.get(p) + ",";
            }
            signatures.add(result);
        }
//        System.out.println(result);
        return signatures;
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
