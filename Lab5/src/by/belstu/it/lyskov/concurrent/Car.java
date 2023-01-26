package by.belstu.it.lyskov.concurrent;

import java.util.concurrent.TimeUnit;

public class Car extends Thread {
    private final Parking parking;
    private boolean isParked;

    public Car(Parking parking, int id) {
        this.parking = parking;
        setName("Car#" + id);
    }

    @Override
    public void run() {
        try {
            while (!isParked) {
                if (parking.first.availablePermits() > 0) {
                    parking.first.acquire();
                    System.out.println("Машина " + getName() + " паркуется на 1-ой стоянке");
                    isParked = true;
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Машина " + getName() + " покидает 1-ую стоянку");
                    parking.first.release();
                }
                TimeUnit.MILLISECONDS.sleep(100);

                if (!isParked && parking.second.availablePermits() > 0) {
                    parking.second.acquire();
                    System.out.println("Машина " + getName() + " паркуется на 2-ой стоянке");
                    isParked = true;
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Машина " + getName() + " покидает 2-ую стоянку");
                    parking.second.release();
                }
                TimeUnit.MILLISECONDS.sleep(500);
                if (!isParked)
                    System.out.println("Машина " + getName() + " не нашла свободных мест, ожидает освобождения...");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
