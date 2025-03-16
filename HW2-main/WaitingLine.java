public class WaitingLine<T> implements IQueue<T>,IDeque<T> {
    private T[] queue;
    private int backIndex;
    private int frontIndex;
    private int numberOfEntries = 0;
    private static final int MAX_CAPACITY = 10000;

    public WaitingLine(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
    }

    private void ensureCapacity() {
        if(queue.length == MAX_CAPACITY)
        {
            throw new IllegalStateException("Reached max size");
        }
        else if((backIndex + 2) % queue.length == frontIndex % queue.length)
        {
            @SuppressWarnings("unchecked")
            T[] newQueue = (T[]) new Object[queue.length + 10];
            for(int i = 0; i < queue.length; i++)
            {
                newQueue[i] = queue[frontIndex % queue.length];
                frontIndex++;
            }

            queue = newQueue;
            frontIndex = 0;
            backIndex = queue.length - 12;
        }
    }

    @Override
    public void addToFront(T newEntry) {
        if (isEmpty()) queue[frontIndex] = newEntry;
        ensureCapacity();
        if (frontIndex - 1 < 0) {
            queue[queue.length - 1] = newEntry;
            frontIndex = queue.length - 1;
        }
        else {
            frontIndex = (frontIndex - 1) % queue.length;
            queue[frontIndex] = newEntry;
        }
        numberOfEntries++;
    }

    @Override
    public void addToBack(T newEntry) {
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
        numberOfEntries++;
    }

    @Override
    public T removeFront() {
        if (isEmpty()) return null;
        else {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            numberOfEntries--;
            return front;
        }
    }

    @Override
    public T removeBack() {

        if (isEmpty()) return null;
        else {
            T back = queue[backIndex];
            queue[backIndex] = null;
            backIndex = (backIndex - 1) % queue.length;
            numberOfEntries--;
            return back;
        }
    }

    @Override
    public T getBack() {
        return queue[backIndex];
    }

    @Override
    public void enqueue(T newEntry) {
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
        numberOfEntries++;
    }

    @Override
    public T dequeue() {
        if(!isEmpty())
        {
            T removedItem = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return removedItem;
        }
        else
        {
            return null;
        }
    }

    @Override
    public T getFront() {
        return queue[frontIndex];
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex+1) % queue.length);
    }

    @Override
    public void clear() {
        while (frontIndex != ((backIndex + 1) % queue.length)) {
            dequeue();
        }
    }


    @Override
    public int getLength() {
        return numberOfEntries;
    }
}
