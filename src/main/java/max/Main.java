package max;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Element{
        int a;
        int b;

        public Element(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public String toString(){
            return this.a + "," + this.b;
        }
    }

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        int n = cin.nextInt();
        Element[] data = new Element[n];
        int a;
        int b;
        for (int i = 0; i < n; i ++) {
            a = cin.nextInt();
            b = cin.nextInt();
            data[i] = new Element(a, b);
        }
        cin.close();

        List<List<Element>> l = new ArrayList<>();

        recursive(0, data, l);

        System.out.println(findMax(l));

//        print(l);
    }

    public static long findMax(List<List<Element>> elements) {
        long max = -2147483648;
        for (int i=0; i<elements.size(); i++) {
            long tmp = 0;
            long tmpA = 0;
            long tmpB = 0;
            for (int j=0; j<elements.get(i).size(); j++) {
                tmp += elements.get(i).get(j).a + elements.get(i).get(j).b;
                tmpA += elements.get(i).get(j).a;
                tmpB += elements.get(i).get(j).b;
            }
            if (tmpA >= 0 && tmpB >= 0 && max < tmp) {
                max = tmp;
            }
        }
        return max;
    }

    public static void recursive(int n, Element[] data, List<List<Element>> elements) {
        if (n == data.length) {
            return;
        }

        addElements(data[n], elements);

        recursive(n+1, data, elements);
    }

    static void addElements(Element element, List<List<Element>> elements) {
        List<List<Element>> tmp = new ArrayList<>();
        for (int i=0; i<elements.size(); i++) {
            tmp.add(copy(elements.get(i)));
        }
        for (int i=0; i<tmp.size(); i++) {
            tmp.get(i).add(element);
        }

        elements.addAll(tmp);

        List<Element> l = new ArrayList<>();
        l.add(element);
        elements.add(l);
    }

    static List<Element> copy(List<Element> l) {
        List<Element> r = new ArrayList<>();
        for (int i=0; i<l.size(); i++) {
            r.add(l.get(i));
        }
        return r;
    }

    static void print(List<List<Element>> elements) {
        for (int i=0; i<elements.size(); i++) {
            System.out.println(elements.get(i).toString());
        }
    }
}


