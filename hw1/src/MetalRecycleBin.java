public class MetalRecycleBin implements IBag<Garbage> {
    Garbage[] metalRecycleBin;
    int size;
    int index = 0;


    public MetalRecycleBin(int size) {
        this.size = size;
        metalRecycleBin = new Garbage[size];
    }

    @Override
    public boolean add(Garbage newItem) {
        if(isFull()) {
            return false;
        }
        else {
            metalRecycleBin[index] = newItem;
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
        if(metalRecycleBin[index] == null) {
            return null;
        }
        else {
            Garbage removedItem = metalRecycleBin[index];
            metalRecycleBin[index] = null;
            return removedItem;
        }
    }

    @Override
    public Garbage remove() {
        for(int i = 0; i < this.size; i++) {
            if(metalRecycleBin[this.size - (i+1)] != null) {
                Garbage removedItem = metalRecycleBin[this.size - (i+1)];
                metalRecycleBin[this.size - (i+1)] = null;
                return removedItem;
            }
        }
        return null;
    }


    @Override
    public Garbage remove(Garbage item) {
        if(contains(item)) {
            int itemIndex = getIndexOf(item);
            metalRecycleBin[itemIndex] = null;
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
            if(metalRecycleBin[i] == null) {
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
        for(int i = 0; i < metalRecycleBin.length; i++) {
            if(metalRecycleBin[i] != null && metalRecycleBin[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean contains(Garbage item) {
        for (int i = 0; i < this.size; i++) {
            if (metalRecycleBin[i] == item) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayItems() {
    	int count = 1;
    	for(int i = 0; i < index; i++) {
    		if(i != 0) {
	    		if(metalRecycleBin[i-1].name == metalRecycleBin[i].name) {
	    			count++;
	    			if(i == index-1) {
	    				System.out.println(metalRecycleBin[i-1].name + "," + metalRecycleBin[i-1].type + "," + count );
	    				}
	    			}else {
		    			System.out.println(metalRecycleBin[i-1].name + "," + metalRecycleBin[i-1].type + "," + count );
		    			count = 1;
		    			if(i == index-1) {
		    				System.out.println(metalRecycleBin[i-1].name + "," + metalRecycleBin[i-1].type + "," + count );
		    			}
	    			}
	    		}
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
