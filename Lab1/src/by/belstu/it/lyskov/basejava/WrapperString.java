package by.belstu.it.lyskov.basejava;

import java.util.Objects;

public class WrapperString {

    private String str;

    public WrapperString(String str) {
        this.str = str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void replace(char oldchar, char newchar) {
        str = str.replace(oldchar, newchar);
    }

    @Override
    public String toString() {
        return "\"" + str + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperString that = (WrapperString) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}

