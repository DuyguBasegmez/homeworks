public class PaperRecycleBin implements IBag<Garbage> {
    Garbage[] paperRecycleBin;
    int size;
    int index = 0;

    public PaperRecycleBin(int size) {
        this.size = size;
        paperRecycleBin = new Garbage[size];
    }

    @Override
    public boolean add(Garbage newItem) {
        if(isFull()) {
            return false;
        }
        else {
            paperRecycleBin[index] = newItem;
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
        if(paperRecycleBin [index] == null) {
            return null;
        }
        else {
            Garbage removedItem = paperRecycleBin[index];
            paperRecycleBin[index] = null;
            return removedItem;
        }
    }

    @Override
    public Garbage remove() {
        for(int i = 0; i < this.size; i++) {
            if(paperRecycleBin[this.size - (i+1)] != null) {
                Garbage removedItem = paperRecycleBin[this.size - (i+1)];
                paperRecycleBin[this.size - (i+1)] = null;
                return removedItem;
            }
        }
        return null;
    }

    @Override
    public Garbage remove(Garbage item) {
    	for (int i = 0; i < this.index; i++) {
            if (paperRecycleBin[i] == item){
                Garbage removedItem = paperRecycleBin[i];
                for (int j = i; j < this.index; j++) {
                    paperRecycleBin[j] = paperRecycleBin[j+1];
                }
                return removedItem;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for(int i = 0; i < this.size ; i++) {
            if(paperRecycleBin[i] == null) {
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
        for(int i = 0; i < paperRecycleBin.length; i++) {
            if(paperRecycleBin[i] != null && paperRecycleBin[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Garbage item) {
        for (int i = 0; i < this.size; i++) {
            if (paperRecycleBin[i] == item) {
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
	    		if(paperRecycleBin[i-1].name == paperRecycleBin[i].name) {
	    			count++;
	    			if(i == index-1) {
	    				System.out.println(paperRecycleBin[i-1].name + "," + paperRecycleBin[i-1].type + "," + count );
	    				}
	    			}else {
		    			System.out.println(paperRecycleBin[i-1].name + "," + paperRecycleBin[i-1].type + "," + count );
		    			count = 1;
		    			if(i == index-1) {
		    				System.out.println(paperRecycleBin[i-1].name + "," + paperRecycleBin[i-1].type + "," + count );
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


