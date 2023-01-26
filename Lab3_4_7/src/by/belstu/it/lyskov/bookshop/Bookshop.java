package by.belstu.it.lyskov.bookshop;

import java.io.*;
import java.util.*;

import by.belstu.it.lyskov.bean.*;
import by.belstu.it.lyskov.exception.ServiceException;

public class Bookshop implements Shop<Printing> {

    public static class Shelf implements Serializable, Comparable<Shelf> {
        Printing product;
        int amount;

        Shelf(Printing product, int amount) {
            this.product = product;
            this.amount = amount;
        }

        public Printing getProduct() {
            return product;
        }

        public void setProduct(Printing product) {
            this.product = product;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public int compareTo(Shelf o) {
            int result = this.product.compareTo(o.product);
            if (result == 0) {
                result = Integer.compare(this.amount, o.amount);
            }
            return result;
        }

        @Override
        public String toString() {
            return "Shelf{" +
                    "product=" + product +
                    ", amount=" + amount +
                    '}';
        }
    }

    final List<Shelf> storage;

    public Bookshop() {
        storage = new ArrayList<>();
    }

    public Bookshop(int capacity) {
        storage = new ArrayList<>(capacity);
    }

    public List<Shelf> getStorage() {
        return storage;
    }

    public void sort() {
        storage.sort(Comparator.naturalOrder());
    }

    public void show() {
        if (storage.size() > 0) {
            for (Shelf shelf : storage) {
                System.out.println(shelf);
            }
        } else
            System.out.println("Storage is empty.");
    }

    public int size() {
        int size = 0;
        for (Shelf shelf : storage) {
            size += shelf.amount;
        }
        return size;
    }

    @Override
    public boolean add(Printing product) throws ServiceException {
        if (product.getId() < 0)
            throw new ServiceException("Product id can't be negative");
        boolean isContain = false;
        for (var shelf : storage) {
            if (shelf.getProduct().equals(product)) {
                shelf.setAmount(shelf.getAmount() + 1);
                isContain = true;
                break;
            }
        }
        if (!isContain) {
            storage.add(new Shelf(product, 1));
        }
        return true;
    }

    @Override
    public boolean update(int id, Printing product) throws ServiceException {
        boolean isContain = false;
        for (var shelf : storage) {
            if (shelf.getProduct().getId() == id) {
                sell(id);
                add(product);
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    @Override
    public Printing find(int id) {
        Printing product = null;
        for (var shelf : storage) {
            if (shelf.getProduct().getId() == id) {
                product = shelf.getProduct();
                break;
            }
        }
        return product;
    }

    @Override
    public Printing sell(int id) throws ServiceException {
        Printing product = null;
        for (var shelf : storage) {
            if (shelf.getProduct().getId() == id) {
                product = shelf.getProduct();
                if (shelf.getAmount() > 1)
                    shelf.setAmount(shelf.getAmount() - 1);
                else storage.remove(shelf);
                break;
            }
        }
        if (product == null)
            throw new ServiceException("Wrong product id");
        return product;
    }
}