import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter available car count, N=");
        int numberOfCars = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter customer count, k=");
        int numberOfCustomers = Integer.parseInt(scanner.nextLine());

        //Create a car waiting list to rent a car from that has user-entered number of cars
        IDeque<Car> carWaitingList = new WaitingLine(numberOfCars);
        for (int i = 0; i < numberOfCars; i++) {
            carWaitingList.addToBack(new Car());
        }

        //Create a customer waiting list that has user-entered number of customers
        IQueue<Customer> customerWaitingList = new WaitingLine(numberOfCustomers);
        for (int i = 0; i < numberOfCustomers; i++) {
            customerWaitingList.enqueue(new Customer());
        }

        //declarations and initializations needed for the while loop which simulates the renting process
        Car car;
        Customer customer;
        CarList<Car> occupiedCars = new CarList<>();
        CarList<Customer> currentRenter = new CarList<>(); //to keep track of the current renters of the occupied cars
        int dayCounter = 0;
        boolean keepRenting = true;
        int leftCustomers = numberOfCustomers; //to store the number of customers iterated through each time
        int leftCars = numberOfCars; //to store the number of available cars iterated through

        //One loop represents one day
        // One loop represents one day
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
                        car.rent(); // Rent the car assigning it a random number of days of occupancy
                        occupiedCars.add(car); // The indices of the car and the customer will be the same in these lists
                        currentRenter.add(customer); // Also add the current renter of that car correspondingly to the currentRenter list
                        leftCustomers--;
                        leftCars--;
                        carRented = true; // Set the flag to indicate that the car has been rented
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
            System.out.println(!customerWaitingList.isEmpty());
            keepRenting = !customerWaitingList.isEmpty();

            if (!keepRenting) {
                System.out.println("All customers rented a car.");
            } else {
                // For loop to display results at the end of the day and to take care of availability of rented cars
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
                        i--; // Decrement the loop counter to account for the removed element
                    }
                }
                System.out.println("*******************" + "End of the Day" + "*******************");
            }
        }}}