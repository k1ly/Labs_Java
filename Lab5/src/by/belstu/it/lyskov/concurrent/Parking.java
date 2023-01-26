package by.belstu.it.lyskov.concurrent;

import java.util.concurrent.Semaphore;

public class Parking {
    Semaphore first;
    Semaphore second;

    public Parking(int capacity) {
        first = new Semaphore(capacity);
        second = new Semaphore(capacity);
    }
}

