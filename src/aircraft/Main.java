package aircraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zhangbing on 2018/12/15.
 */
public class Main {
    public static void main(String args[]){
        Scanner cin = new Scanner(System.in);
        int m;
        int n;
        m = cin.nextInt();
        n = cin.nextInt();

        List<Integer> other = new ArrayList<>();
        for (int i=0; i<m; i++) {
            other.add(cin.nextInt());
        }

        List<Integer> owner = new ArrayList<>();
        for (int i=0; i<n; i++) {
            owner.add(cin.nextInt());
        }

        Collections.sort(owner);
        Collections.sort(other);

        int j;
        for (j=0; j<owner.size();) {
            if (other.size() > 0 && other.get(0) < owner.get(j)) {
                other.remove(0);
                owner.remove(j);
            } else {
                j++;
            }
        }

        if (other.size() != 0) {
            System.out.println(0);
        } else {
            int sum = 0;
            for (int k=0; k<owner.size(); k++) {
                sum += owner.get(k);
            }
            System.out.println(sum);
        }
    }
}
