package by.belstu.it.lyskov.bean;

public class Operator {
    private boolean isBusy;
    private final String name;

    public Operator(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public String getName() {
        return name;
    }
}
