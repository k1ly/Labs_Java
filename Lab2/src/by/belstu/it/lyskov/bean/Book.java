package by.belstu.it.lyskov.bean;

import java.io.Serializable;
import java.util.Objects;

public class Book extends Printing implements Serializable {
    protected String name;
    protected String author;

    public Book() {
        pType = PType.Book;
    }

    public Book(int id) {
        this();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", year=" + year +
                ", pType=" + pType +
                '}';
    }
}