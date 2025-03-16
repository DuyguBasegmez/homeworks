public interface IPriorityQueue <T extends Comparable<T>>{
    public void add(String task_type, int burst_time, String arrival_date, String arrival_time);
    public  void addFirst(String task_type, int burst_time, String arrival_date, String arrival_time);
    public T remove();
    public T peek();
    public boolean isEmpty();
    public int getSize();
    public void clear();
    public void display();

}


