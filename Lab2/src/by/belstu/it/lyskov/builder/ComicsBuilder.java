package by.belstu.it.lyskov.builder;

import by.belstu.it.lyskov.bean.Comics;

public class ComicsBuilder implements Builder<Comics> {
    protected int id;
    protected int price;
    protected int year;
    protected String name;
    protected String author;
    protected String artist;

    public ComicsBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ComicsBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public ComicsBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public ComicsBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ComicsBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public ComicsBuilder withArtist(String artist) {
        this.artist = artist;
        return this;
    }

    @Override
    public Comics build() {
        Comics comics = new Comics();
        comics.setId(this.id);
        comics.setPrice(this.price);
        comics.setYear(this.year);
        comics.setName(this.name);
        comics.setAuthor(this.author);
        comics.setArtist(this.artist);
        return comics;
    }
}
