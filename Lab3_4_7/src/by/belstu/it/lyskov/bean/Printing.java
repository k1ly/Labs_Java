package by.belstu.it.lyskov.bean;

import java.io.Serializable;

public abstract class Printing implements Serializable, Comparable<Printing> {

    public enum PType {
        Book, Postcard, Comics
    }

    protected int id;
    protected int price;
    protected int year;

    protected PType pType;

    public Printing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public PType getPrintingType() {
        return pType;
    }

    @Override
    public int compareTo(Printing o) {
        int result = Integer.compare(this.id, o.id);
        if (result == 0) {
            result = Integer.compare(this.price, o.price);
        }
        return result;
    }
}