public class Task_Manager implements IPriorityQueue<Operation>{
    private Operation firstNode;
    private Operation lastNode;
    private int numberOfEntries = 0;

    public Task_Manager(){
        firstNode = null;

    }
    public  void addFirst(String task_type, int burst_time, String arrival_date, String arrival_time){
        numberOfEntries++;
        Operation operation = new Operation(task_type,burst_time,arrival_date,arrival_time);
        int priority = operation.getPriority();
        if (isEmpty()){

            operation.next = firstNode;
            firstNode = operation;
        } else if ((operation.compareToDate(firstNode)) ==0&&((operation.compareTo(firstNode)<0))) {
            operation.next = firstNode;
            firstNode = operation;
        } else {
            Operation temp = firstNode;

            // Find the appropriate position to insert the new node
            while (temp.next != null && (operation.compareToDate(temp.next)) >=0) {
                if (operation.compareToDate(temp.next) ==0){
                    if (operation.compareTo(temp.next)<0){
                        break;}
                }
                temp = temp.next;
            }
            // Insert the new node at the appropriate position
            operation.next = temp.next;
            temp.next = operation;
        }
    }
    @Override
    public void add(String task_type,int burst_time,String arrival_date,String arrival_time){
        numberOfEntries++;
        Operation operation = new Operation(task_type,burst_time,arrival_date,arrival_time);
        int priority = operation.getPriority();
        if (isEmpty() || priority > firstNode.priority) {
            operation.next = firstNode;
            firstNode = operation;
        }
        else {
            Operation temp = firstNode;

            // Find the appropriate position to insert the new node
            while (temp.next != null && priority <= temp.next.priority) {
                if (operation.compareToPriority(temp.next)==0){
                    if (operation.compareToDate(temp.next)<0){
                        break;
                    }
                    while (operation.compareToDate(temp)==0){
                        if (operation.compareTo(temp.next)<0){
                            break;
                        }
                        temp = temp.next;
                    }
                }
                temp = temp.next;

            }

            // Insert the new node at the appropriate position
            operation.next = temp.next;
            temp.next = operation;
        }
    }
    public Operation peek(){
        if (isEmpty())
            throw new RuntimeException("Priority queue is empty");
        else return firstNode;
    }

    public Operation remove(){
        if (isEmpty()) {
            throw new RuntimeException("Priority queue is empty");
        }

        Operation removedItem = firstNode;
        firstNode = firstNode.next;
        return removedItem;

    }
    public boolean isEmpty(){
        return (firstNode == null) && (lastNode == null);
    }
    public void clear(){
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }
    public int getSize(){
        return numberOfEntries;
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("Priority queue is empty");
            return;
        }

        Operation temp = firstNode;
        while (temp != null) {
            System.out.println("Data: " + temp.getData() + ", Priority: " + temp.priority);
            temp = temp.next;
        }
    }
    public Operation[] toArray(){
        Operation[] result =  new Operation[numberOfEntries];
        int index = 0;
        Operation temp = firstNode;
        while ((index < numberOfEntries) && (temp != null))
        {
            result[index] = temp;
            temp = temp.getNextNode();
            index++;
        }
        return result;
    }
    public void showOperation(){
        if (isEmpty()) {
            System.out.println("Priority queue is empty");
            return;
        }

        Operation temp = firstNode;
        while (temp != null) {
            System.out.println("Task type: " + temp.taskType+" Burst time: "+temp.burstTime +" Arrival time: "+temp.arrivalTime);
            temp = temp.next;
        }
    }
}