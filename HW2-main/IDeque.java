public interface IDeque<T> {

    public void addToFront(T newEntry);

    public void addToBack(T newEntry);

    public T removeFront();

    public T removeBack();

    public T getFront();

    public T getBack();

    public boolean isEmpty();

    public void clear();

    public  int getLength();

}
