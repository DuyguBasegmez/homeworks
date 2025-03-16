public interface IComparable <T>{
    public int compareToBurstTime(Operation other);
    public int compareToDate(Operation other);
    public int compareToPriority(T other);

    int compareTo(Operation other);
}
