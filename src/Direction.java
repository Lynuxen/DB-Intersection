package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class Direction extends Thread {
    Semaphore semaphore;
    private final String direction;
    ArrayList<Car> cars;

    public Direction(Semaphore semaphore, Position position, int numberOfCars) {
        super(position.toString());
        this.semaphore = semaphore;
        this.direction = position.toString();
        this.cars = new ArrayList<>(numberOfCars);
        for (int i = 0; i < numberOfCars; i++) {
            if (position.equals(Position.NORTH)) {
                cars.add(new Car("Mercedes " + i, position));
            } else {
                cars.add(new Car("Audi " + i, position));
            }

        }
    }

    @Override
    public void run() {
        if (this.direction.equals("NORTH")) {
            try {
                semaphore.acquire();
                System.out.println("GREEN FOR NORTH!");
                int counter = 0;
                for (Iterator<Car> i = cars.iterator(); i.hasNext(); ) {
                    Car myCar = i.next();
                    if (!myCar.getName().isEmpty()) {
                        System.out.print(myCar.getName() + " went from " + myCar.getPosition() + " to ");
                        myCar.setPosition(Position.SOUTH);
                        System.out.println(myCar.getPosition());
                        i.remove();
                    }

                    counter++;
                    if (counter == 10) {
                        System.out.println("RED FOR NORTH!");
                        semaphore.release();
//                       Do not sleep in a for loop,
//                       only for demonstration purposes :))
                        Thread.sleep(1);
                        semaphore.acquire();
                        System.out.println("GREEN FOR NORTH!");
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            semaphore.release();
            System.out.println("RED FOR NORTH!");
        }
        if (this.direction.equals("WEST")) {
            try {
                semaphore.acquire();
                System.out.println("GREEN FOR WEST!");
                int counter = 0;
                for (Iterator<Car> i = cars.iterator(); i.hasNext(); ) {
                    Car myCar = i.next();
                    if (!myCar.getName().isEmpty()) {
                        System.out.print(myCar.getName() + " went from " + myCar.getPosition() + " to ");
                        myCar.setPosition(Position.EAST);
                        System.out.println(myCar.getPosition());
                        i.remove();
                    }
                    counter++;
                    if (counter == 5) {
                        System.out.println("RED FOR WEST!");
                        semaphore.release();
                        Thread.sleep(1);
                        semaphore.acquire();
                        System.out.println("GREEN FOR WEST!");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            semaphore.release();
            System.out.println("RED FOR WEST!");
        }
    }
}
