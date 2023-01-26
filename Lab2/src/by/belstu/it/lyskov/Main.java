package by.belstu.it.lyskov;

import by.belstu.it.lyskov.basejava.JavaTest;
import by.belstu.it.lyskov.basejava.WrapperString;

import java.util.*;

public class Main {

    private static WrapperString wsanonym;
    private WrapperString wsanonym1;

    public static void main(String[] args) {

        JavaTest jtest = new JavaTest();
        jtest.math();
        System.out.println();
        jtest.type();
        System.out.println();
        jtest.Type();
        System.out.println();
        jtest.string();
        System.out.println();
        jtest.arr();
        System.out.println();

        WrapperString ws = new WrapperString("Hello world!");
        ws.replace('!', '?');
        System.out.println(ws);

        WrapperString wsanonym = new WrapperString("Anonymous class!") {
            @Override
            public void replace(char oldchar, char newchar) {
                super.replace(oldchar, newchar);
                System.out.println("* Замена произведена *");
            }

            public WrapperString delete(char oldchar) {
                super.replace(oldchar, '\0');
                return this;
            }
        }.delete('o');

        wsanonym.replace('!', '.');
        System.out.println(wsanonym);
    }

}

/*
 * @author LK

 * */