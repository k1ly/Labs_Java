package by.belstu.it.lyskov.concurrent;

public class Client extends Thread {
    private static Organization organization;

    public Client(Organization org, String name) {
        organization = org;
        setName(name);
    }

    @Override
    public void run() {
        organization.call(this);
    }
}
