import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        Task_Manager taskManager = new Task_Manager();

        try (BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] operation = line.split(",");
                String task_type = operation[0];
                int burst_time = Integer.parseInt(operation[1]);
                String arrival_date = operation[2];
                String arrival_time = operation[3];
                taskManager.addFirst(task_type,burst_time,arrival_date,arrival_time);
            } }catch (IOException e) {
            e.printStackTrace();
        }
        taskManager.showOperation();
        System.out.println(taskManager.getSize());
        System.out.println("***********************************************");
        System.out.println("WAITING LINE:");
        Task_Manager WaitingList = new Task_Manager();
        while (!taskManager.isEmpty()){
            Operation temp = taskManager.remove();
            WaitingList.add(temp.taskType,temp.burstTime, temp.strArrivalDate, temp.arrivalTime.toString());
        }
        WaitingList.display();
        System.out.println(WaitingList.getSize());
        while (!WaitingList.isEmpty()){
            for (int i=0;i<5;i++){
                Operation op = WaitingList.remove();
            }
            System.out.println("Kalan operasyonlar");
            WaitingList.display();
        }
    }
}