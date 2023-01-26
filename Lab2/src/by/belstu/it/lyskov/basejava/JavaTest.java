package by.belstu.it.lyskov.basejava;

import static java.lang.Math.*;

public class JavaTest {
    int age;
    final int CONST_1 = 1;
    public final int CONST_2 = 2;
    public static final int CONST_3 = 3;

    public boolean gets() {
        return age < 7;
    }

    static int sint;

    public void type() {
        char t1 = 'c';
        short t2 = 1023;
        byte t3 = 42;
        int t4 = 23187;
        long t5 = 854353L;
        boolean t6 = false;

        var s1 = "int " + 65;
        var s2 = "char " + t1;
        var s3 = "double " + 123.99;
        System.out.println(s1 + " " + s2 + " " + s3);

        byte b1 = (byte) (t3 + 99);
        double b2 = 23.3 + t5;
        long b3 = (long) t4 + 2147483647;
        System.out.println(b1 + " " + b2 + " " + b3);

        System.out.println(sint);

        var n1 = 9223372036854775807L;
        var n2 = 0x7fff_fff_ffffL;
        System.out.println(n1 + " " + n2);

        boolean l1 = true && t6;
        boolean l2 = false ^ true;
//        boolean l3 = true + true;
        System.out.println(l1 + " " + l2);

        char c1 = 'u';
        char c2 = '\u0061';
        char c3 = 97;
        var c4 = c1 + c2 + c3;
        System.out.println(c1 + " " + c2 + " " + c3 + " " + c4);

        var o1 = 3.45 % 2.4;
        var o2 = 1.0 / 0.0;
        var o3 = 0.0 / 0.0;
        var o4 = log(-345);
        var o5 = Float.intBitsToFloat(0x7f800000);
        var o6 = Float.intBitsToFloat(0xff800000);
        System.out.println(o1 + " " + o2 + " " + o3 + " " + o4 + " " + o5 + " " + o6);
    }

    public void math() {
        double pi = PI;
        double e = E;
        long round = Math.round(pi);
        double min = Math.min(pi, e);
        double rand = Math.random();
        System.out.println(pi + " " + e + " " + round + " " + min + " " + rand);
    }

    public void Type() {
        Boolean t1 = true;
        Character t2 = 'v';
        Integer t3 = -1234;
        Byte t4 = 2;
        Short t5 = 432;
        Long t6 = 646L;
        Double t7 = 332.66;

        Integer x1 = t3 >>> 1;
        Integer x2 = t3 >> 10;
        Integer x3 = ~t3;
        Integer x4 = t3 & 742;
        Integer x5 = t3 * 894;
        Integer x6 = t3 + 5040;
        Integer x7 = t3 - 126;
        System.out.println(x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7);

        System.out.println(Long.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);

        Integer w1 = 28;
        int uw1 = w1 / 7;
        Byte w2 = (byte) 255;
        byte uw2 = (byte) (w2 / 2);
        System.out.println(w1 + " " + uw1 + " " + w2 + " " + uw2);

        System.out.println(Integer.parseInt("734"));
        System.out.println(Integer.toHexString(47));
        System.out.println(Integer.compare(21, 540));
        System.out.println(Integer.toString(5230));
        System.out.println(Integer.bitCount(63));
    }

    public void string() {
        String s34 = "2345";
        Integer i0 = 2345;
        int i1 = i0;
        int i2 = Integer.parseInt(s34);
        int i3 = Integer.valueOf(s34);
        System.out.println(i1 + " " + i2 + " " + i3);

        char[] ca = s34.toCharArray();
        String s2 = String.copyValueOf(ca);
        for (int i = 0; i < ca.length; i++) {
            System.out.print(ca[i]);
        }
        System.out.println(s2);

        boolean b1 = Boolean.getBoolean(s34);
        boolean b2 = Boolean.parseBoolean(s34);
        System.out.println(b1 + " " + b2);

        System.out.println(s2 == s34);
        System.out.println(s2.equals(s34));
        System.out.println(s2.compareTo(s34));
        s34 = null;
        System.out.println(s2 == s34);
        System.out.println(s2.equals(s34));
//        System.out.println(s2.compareTo(s34));

        String s1 = "String contains words";
        String[] cb = s1.split("");
        for (int i = 0; i < cb.length; i++) {
            System.out.print(cb[i] + "|");
        }
        System.out.println();
        System.out.println(s1.contains("c"));
        System.out.println(s1.hashCode());
        System.out.println(s1.indexOf("word"));
        System.out.println(s1.length());
        System.out.println(s1.replace("String", "Text"));
    }

    public void arr() {
        char[][] c1;
        char[] c2[];
        char c3[][];
        c1 = new char[3][];
        for (int i = 0; i < 3; i++) {
            c1[i] = new char[i + 1];
            System.out.print(c1[i].length + " ");
        }
        System.out.println();
        c2 = new char[5][];
        c3 = new char[10][];
        boolean comRez = c2 == c3;
        c2 = c3;
        c1[0][0] = '#';
        System.out.println(comRez);
        for (char i : c1[0]) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println(c1[c1.length - 1][0]);
    }
}