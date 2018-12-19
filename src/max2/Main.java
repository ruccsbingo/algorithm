package max2;
/**
 * Created by zhangbing on 2018/12/17.
 */

import java.util.Scanner;

public class Main {

    static class Element{
        int h;
        int a;
        int c;

        public Element(int h, int a, int c) {
            this.h = h;
            this.a = a;
            this.c = c;
        }

        public String toString(){
            return this.h + "\t" ;
        }
    }

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);

        int n = cin.nextInt();
        Element[] elements = new Element[n];
        int h;
        int a;
        int c;
        int counter = 0;
        for (int i = 0; i < n; i ++) {
            h = cin.nextInt();
            a = cin.nextInt();
            c = cin.nextInt();
            counter += c;
            elements[i] = new Element(h, a, c);
        }

        int k=0;
        Element[] data = new Element[counter];
        for (int i=0; i<elements.length; i++) {
            for (int j=0; j<elements[i].c; j++) {
                data[k] = elements[i];
                k++;
            }
        }
        cin.close();

        recursive(0, data);
        System.out.println(max);
    }

    public static boolean isValid(int pos, Element[] arragement){
        long tmp = height(pos-1, arragement);
        if (arragement[pos].a >= tmp) {
            return true;
        }
        return false;
    }

    static long max = -2100000000;

    public static long height(int pos, Element[] arragement) {
        long h = 0;
        for (int i=0; i<=pos; i++) {
            h += arragement[i].h;
        }
        return h;
    }

    static void print(long max, int pos, Element[] arragement) {
//        if (max < 48) {
//            return;
//        }
        System.out.print("Max: " + max + "\t");
        for (int i=0; i<=pos; i++) {
            System.out.print(arragement[i]);
        }
        System.out.println();
    }

    public static void recursive(int pos, Element[] arragement) {
        if (!isValid(pos, arragement)) {
            long tmp = height(pos-1, arragement);
            if (max < tmp) {
                max = tmp;
            }
//            print(tmp, pos - 1, arragement);
            return;
        }
        if (pos == arragement.length-1) {
            long tmp = height(pos, arragement);
            if (max < tmp) {
                max = tmp;
            }
//            print(tmp, pos, arragement);
            return;
        }

        for (int i=pos; i<arragement.length; i++) {
            if (i != pos && arragement[pos].h == arragement[i].h) {
                continue;
            }
            swap(arragement, pos, i);
            if (!isValid(pos, arragement)) {
                swap(arragement, pos, i);
                continue;
            }
            recursive(pos+1, arragement);
            swap(arragement, pos, i);
        }
    }

    static void swap(Element[] arragement, int start, int end) {
        if (start == end) return;
        Element tmp = arragement[start];
        arragement[start] = arragement[end];
        arragement[end] = tmp;
    }
}

