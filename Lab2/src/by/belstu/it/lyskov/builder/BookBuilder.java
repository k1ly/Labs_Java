package by.belstu.it.lyskov.builder;

import by.belstu.it.lyskov.bean.Book;

public class BookBuilder implements Builder<Book> {
    protected int id;
    protected int price;
    protected int year;
    protected String name;
    protected String author;

    public BookBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public BookBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public BookBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public BookBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public Book build() {
        Book book = new Book();
        book.setId(this.id);
        book.setPrice(this.price);
        book.setYear(this.year);
        book.setName(this.name);
        book.setAuthor(this.author);
        return book;
    }
}
