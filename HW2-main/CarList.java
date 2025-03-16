import java.util.Arrays;
public class CarList <T> implements IList<T> {
    private T[] carList;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;  // as in the slide sets
    private static final int MAX_CAPACITY = 10000;

    public CarList() {
        this(DEFAULT_CAPACITY);
    }

    public CarList(int initialCapacity) {
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;

        @SuppressWarnings("unchecked")
        T[] templist = (T[]) new Object[initialCapacity + 1];
        carList = templist;
        numberOfEntries = 0;
    }
    private void makeRoom(int newPosition){
        assert (newPosition>=1) && (newPosition<=numberOfEntries+1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        for (int index = lastIndex; index >= newIndex; index--)
            carList[index+1] = carList[index];
    }
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Exceeded maximum capacity.");
        }
    }

    private void ensureCapacity() {
        int capacity = carList.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity);
            Car[] newCarList = new Car[newCapacity + 1];

            for (int i = 0; i < carList.length; i++) {
                newCarList[i] =  (Car) carList[i];
            }

            carList = (T[]) newCarList;
        }
    }

    @Override
    public void add(T newEntry) {
        carList[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if((newPosition >= 1) && (newPosition <= numberOfEntries + 1)){
            if(newPosition<=numberOfEntries)
                makeRoom(newPosition);
            carList[newPosition] = newEntry;
            numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public T remove(int givenPosition) {
        if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
            assert !isEmpty();
            T result = carList[givenPosition];
            if (givenPosition<numberOfEntries)
                removeGap(givenPosition);
            numberOfEntries--;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    public void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = removedIndex; index < lastIndex; index++)
            carList[index] = carList[index + 1]; {
        }
    }

    @Override
    public void clear() { while (!isEmpty()){
        remove(0);
        }
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T originalEntry = carList[givenPosition];
            carList[givenPosition] = newEntry;
            return originalEntry;
        }
        else throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    @Override
    public T getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
            assert !isEmpty();
            return carList[givenPosition];
        }
        else throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }


    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index = 0;
             index < numberOfEntries;
             index++)
        {
            result[index] = carList[index+1];
        }
        return result;
    }


    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < this.numberOfEntries ; i++) {
            if (carList[i] == anEntry) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
}

