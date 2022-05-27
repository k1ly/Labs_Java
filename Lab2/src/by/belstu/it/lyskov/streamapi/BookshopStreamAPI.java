package by.belstu.it.lyskov.streamapi;

import by.belstu.it.lyskov.bookshop.Bookshop;

import java.util.concurrent.atomic.AtomicInteger;

public class BookshopStreamAPI {

    public int calculateFullCost(Bookshop bookshop) {
        AtomicInteger cost = new AtomicInteger();
        bookshop.getStorage().forEach(shelf -> cost.addAndGet(shelf.getProduct().getPrice() * shelf.getAmount()));
        return cost.get();
    }
}
