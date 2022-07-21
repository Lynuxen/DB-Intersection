package src;

import java.util.concurrent.Semaphore;

public class Intersection {
    private static final int multiplier = 3;
    static Semaphore semaphore = new Semaphore(1);

    private final Direction northSouth;
    private final Direction westEast;

    public Intersection(int numberOfCars) {
        this.northSouth = new Direction(semaphore, Position.NORTH,
                numberOfCars);
        this.westEast = new Direction(semaphore, Position.WEST,
                numberOfCars * multiplier);
    }

    public void simulate() {
        this.northSouth.start();
        this.westEast.start();

        try {
            this.northSouth.join();
            this.westEast.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}