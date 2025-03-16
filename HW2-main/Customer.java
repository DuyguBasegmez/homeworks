import java.util.Random;
public class Customer {
    private double qualityThreshold;
    private String idNumber;
    private static int counter;
    private static Random random = new Random();

    public Customer() {
        qualityThreshold = random.nextDouble(1.0, 3.0);
        idNumber = Integer.toString(counter);
        counter++;
    }

    public String toString() {
        return "cust" + idNumber;
    }

    public double getQualityThreshold() {

        return Math.round(qualityThreshold * 100)/ 100;
    }

    public void decreaseThreshold() {
        qualityThreshold *= 0.9;
    }
}