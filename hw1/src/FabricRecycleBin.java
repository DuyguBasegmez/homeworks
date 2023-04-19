public class FabricRecycleBin implements IBag<Garbage> {
    Garbage[] fabricRecycleBin;
    int size;
    int index = 0;

    public FabricRecycleBin(int size) {
        this.size = size;
        fabricRecycleBin = new Garbage[size];
    }

    @Override
    public boolean add(Garbage newItem) {
        if(isFull()) {
            return false;
        }
        else {
            fabricRecycleBin[index] = newItem;
            index++;
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        if(getItemCount() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if(getItemCount() == size) {
            return true;
        }
        else
            return false;
    }

    @Override
    public Garbage removeByIndex(int index) {
        if(fabricRecycleBin [index] == null) {
            return null;
        }
        else {
            Garbage removedItem = fabricRecycleBin[index];
            fabricRecycleBin[index] = null;
            return removedItem;
        }
    }

    @Override
    public Garbage remove() {
        for(int i = 0; i < this.size; i++) {
            if(fabricRecycleBin[this.size - (i+1)] != null) {
                Garbage removedItem = fabricRecycleBin[this.size - (i+1)];
                fabricRecycleBin[this.size - (i+1)] = null;
                return removedItem;
            }
        }
        return null;
    }


    @Override
    public Garbage remove(Garbage item) {
        if(contains(item)) {
            int itemIndex = getIndexOf(item);
            fabricRecycleBin[itemIndex] = null;
            return item;
        }
        else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for(int i = 0; i < this.size ; i++) {
            if(fabricRecycleBin[i] == null) {
                continue;
            }
            else {
                itemCount++;
            }
        }
        return itemCount;
    }

    @Override
    public int getIndexOf(Garbage item) {
        for(int i = 0; i < fabricRecycleBin.length; i++) {
            if(fabricRecycleBin[i] != null && fabricRecycleBin[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Garbage item) {
        for (int i = 0; i < this.size; i++) {
            if (fabricRecycleBin[i] == item) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayItems() {
        if (getItemCount() > 0) {
            int amount = 1;
            for (int i = 0; i < getItemCount() - 1; i++) {
                if(fabricRecycleBin[i].toString().equals(fabricRecycleBin[i + 1].toString())) {
                    amount++;
                } else {
                    System.out.println(fabricRecycleBin[i].name + ","+ fabricRecycleBin[i].type + "," + amount);
                    amount = 1;
                }
            }
            System.out.println(fabricRecycleBin[getItemCount() - 1].name+ ","+ fabricRecycleBin[getItemCount() - 1].type +","+ amount);
        }
    }
    
    @Override
    public void dump() {
        while (!isEmpty()) {
            remove();
        }
    }

    @Override
    public boolean transferTo(IBag<Garbage> targetBag, Garbage item) {
        if(contains(item)) {
            remove(item);
            targetBag.add(item);
            return true;
        }
        return false;
    }
}