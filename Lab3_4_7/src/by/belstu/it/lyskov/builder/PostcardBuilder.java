package by.belstu.it.lyskov.builder;

import by.belstu.it.lyskov.bean.Postcard;

public class PostcardBuilder implements Builder<Postcard> {
    protected int id;
    protected int price;
    protected int year;
    protected String theme;

    public PostcardBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PostcardBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public PostcardBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public PostcardBuilder withTheme(String theme) {
        this.theme = theme;
        return this;
    }

    @Override
    public Postcard build() {
        Postcard postcard = new Postcard();
        postcard.setId(this.id);
        postcard.setPrice(this.price);
        postcard.setYear(this.year);
        postcard.setTheme(this.theme);
        return postcard;
    }
}
