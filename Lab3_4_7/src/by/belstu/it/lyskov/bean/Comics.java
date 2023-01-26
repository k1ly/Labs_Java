package by.belstu.it.lyskov.bean;

import java.io.Serializable;
import java.util.Objects;

public final class Comics extends Book implements Serializable {
    private String artist;

    public Comics() {
        pType = PType.Comics;
    }

    public Comics(int id){
        this();
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return Objects.equals(artist, comics.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist);
    }

    @Override
    public String toString() {
        return "Comics{" +
                "Name='" + name + '\'' +
                ", Author='" + author + '\'' +
                ", artist='" + artist + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", year=" + year +
                ", pType=" + pType +
                '}';
    }
}