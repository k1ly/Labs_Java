package by.belstu.it.lyskov.bean;

import java.io.Serializable;
import java.util.Objects;

public final class Postcard extends Printing implements Serializable {
    private String theme;

    public Postcard() {
        pType = PType.Postcard;
    }

    public Postcard(int id) {
        this();
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Postcard postcard = (Postcard) o;
        return Objects.equals(theme, postcard.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), theme);
    }

    @Override
    public String toString() {
        return "Postcard{" +
                "theme='" + theme + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", year=" + year +
                ", pType=" + pType +
                '}';
    }

}