package by.belstu.it.lyskov.concurrent;

import by.belstu.it.lyskov.bean.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Organization {
    private final List<Operator> operators;

    public Organization(int n) {
        operators = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            operators.add(new Operator("Operator#" + (i + 1)));
        }
    }

    public void call(Client client) {
        int i = 0;
        while (operators.get(i).isBusy()) {
            i = (i == operators.size() - 1) ? 0 : i + 1;
        }
        synchronized (operators.get(i)) {
            Operator operator = operators.get(i);
            operator.setBusy(true);
            System.out.println(operator.getName() + " обслуживает " + client.getName() + " ..");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            operator.setBusy(false);
            System.out.println(operator.getName() + " заканчивает обслуживать " + client.getName());
        }
    }
}