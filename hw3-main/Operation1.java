//BENDÜZENLEDİMKESİNDEĞİL
import java.time.LocalDate;
import java.time.LocalTime;

public class Operation implements IComparable<Operation> {
    int burstTime;
    LocalDate arrivalDate;
    LocalTime arrivalTime;
    String taskType;
    int priority = 0;
    Operation next;
    String strArrivalDate;

    public Operation(String taskType,int burstTime,String arrivalDate,String arrivalTime) {
        LocalTime time = LocalTime.parse(arrivalTime);
        this.burstTime = burstTime;
        this.taskType = taskType;
        this.strArrivalDate = arrivalDate;
        this.next = null;
        this.arrivalTime = time;
        String[] operation = arrivalDate.split("/");
        this.arrivalDate = LocalDate.of(Integer.parseInt(operation[2]), Integer.parseInt(operation[1]), Integer.parseInt(operation[0]));
        switch (taskType) {
            case "security management":
                priority = 6;
                break;
            case "process management":
                priority = 5;
                break;
            case "memory management":
                priority = 4;
                break;
            case "user management":
                priority = 3;
                break;
            case "device management":
                priority = 2;
                break;
            case "file management":
                priority = 1;
                break;

        }
    }
    @Override
    public int compareToPriority(Operation other) {
        if (priority < other.priority) {
            return -1;
        } else if (priority > other.priority) {
            return 1;
        }
        else {
            return 0;
        }
    }
    public int compareToByBurstTime(Operation other){
        if (burstTime < other.burstTime){
            return -1;
        } else if (priority > other.burstTime) {
            return 1;
        } else {return 0;}}

    @Override
    public int compareToBurstTime(Operation other) {
        return 0;
    }
    @Override
    public int compareTo(Operation other) {
        return arrivalTime.compareTo(other.arrivalTime);
    }
    @Override
    public int compareToDate(Operation other) {
        return  arrivalDate.compareTo(other.arrivalDate);
    }

    public Operation getNextNode() {
        return next;
    }
    public int getPriority(){
        return priority;}
    public int getBurstTime(){
        return burstTime;}
    public String getData(){
        return taskType + " " + Integer.toString(burstTime) + " "+arrivalDate+" "+ arrivalTime;}

}
