import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter available car count, N=");
        int numberOfCars = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter customer count, k=");
        int numberOfCustomers = Integer.parseInt(scanner.nextLine());

        startRentingProcess(numberOfCars, numberOfCustomers);
    }

    public static void startRentingProcess(int numberOfCars, int numberOfCustomers) {
        IDeque<Car> carWaitingList = new WaitingLine<Car>(numberOfCars);
        for (int i = 0; i < numberOfCars; i++) {
            carWaitingList.addToBack(new Car());
        }

        IQueue<Customer> customerWaitingList = new WaitingLine<Customer>(numberOfCustomers);
        for (int i = 0; i < numberOfCustomers; i++) {
            customerWaitingList.enqueue(new Customer());
        }

        Car car;
        Customer customer;
        CarList<Car> occupiedCars = new CarList<>();
        CarList<Customer> currentRenter = new CarList<>();
        int dayCounter = 0;
        boolean keepRenting = true;
        int leftCustomers = numberOfCustomers;
        int leftCars = numberOfCars;

        while (keepRenting) {
            dayCounter++;
            System.out.println("*******************" + "Day" + dayCounter + "*******************");
            numberOfCars = leftCars;
            for (int i = 1; i <= numberOfCars; i++) {
                car = carWaitingList.removeFront();
                System.out.println("Current " + car + " quality=" + car.getQualityScore() + " is offering to");
                numberOfCustomers = leftCustomers;
                boolean carRented = false; // Flag to check if the car has been rented
                for (int j = 1; j <= numberOfCustomers; j++) {
                    customer = customerWaitingList.dequeue();
                    if (car.getQualityScore() > customer.getQualityThreshold()) {
                        System.out.printf("\tCurrent " + customer + " threshold=" + "%.2f" + "\t\t\t" + "---accepted\n",
                                customer.getQualityThreshold());
                        car.rent(); 
                        occupiedCars.add(car); 
                        currentRenter.add(customer); 
                        leftCustomers--;
                        leftCars--;
                        carRented = true; 
                        break;
                    } else {
                        System.out.printf("\tCurrent " + customer + " threshold=" + "%.2f" + "\t\t\t" + "---not accepted\n",
                                customer.getQualityThreshold());
                        customer.decreaseThreshold();
                        customerWaitingList.enqueue(customer);
                    }
                }
                if (!carRented) {
                    carWaitingList.addToBack(car);
                    System.out.println("\t---not accepted by any customer---");
                }
            }

            System.out.println("All cars have been seen.");

            keepRenting = !customerWaitingList.isEmpty();

            if (!keepRenting) {
                System.out.println("All customers rented a car.");
            } else {
                System.out.println("But there are still customers waiting.");
                System.out.println("Rented cars: ");
                for (int i = 1; i <= occupiedCars.getLength(); i++) {
                    Car occupiedCar = occupiedCars.getEntry(i);
                    Customer renter = currentRenter.getEntry(i);
                    System.out.println(occupiedCar + " by " + renter + " occupancy=" + occupiedCar.getLeftDays());
                    occupiedCar.decreaseOccupancy();
                    if (occupiedCar.getLeftDays() == 0) {
                        occupiedCars.remove(i);
                        currentRenter.remove(i);
                        carWaitingList.addToFront(occupiedCar);
                        leftCars++;
                        i--; 

                    }
                }
                System.out.println("Available cars: ");
                int tempLeftCars = leftCars;
                boolean availableCarsExist = false;

                for (int i = 0; i < tempLeftCars; i++) {
                    Car availableCar = carWaitingList.removeFront();

                    if (availableCar.getLeftDays() != 1) {
                        System.out.println(availableCar);
                        availableCarsExist = true;
                    }

                    carWaitingList.addToBack(availableCar);
                }

                if (!availableCarsExist) {
                    System.out.println("No available cars.");
                }
                System.out.println("*******************" + "End of the Day" + "*******************:");
            }
        }
    }
}
