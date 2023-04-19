public class PlasticRecycleBin implements IBag<Garbage> {
    Garbage[] plasticRecycleBin;
    int size;
    int index = 0;
    
    public PlasticRecycleBin(int size) {
        this.size = size;
        plasticRecycleBin = new Garbage[size];
    }

    @Override
    public boolean add(Garbage newItem) {
        if(isFull()) {
            return false;
        }
        else {
            plasticRecycleBin[index] = newItem;
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
        if(plasticRecycleBin [index] == null) {
            return null;
        }
        else {
            Garbage removedItem = plasticRecycleBin[index];
            plasticRecycleBin[index] = null;
            return removedItem;
        }
    }

    @Override
    public Garbage remove() {
        for(int i = 0; i < this.size; i++) {
            if(plasticRecycleBin[this.size - (i+1)] != null) {
                Garbage removedItem = plasticRecycleBin[this.size - (i+1)];
                plasticRecycleBin[this.size - (i+1)] = null;
                return removedItem;
            }
        }
        return null;
    }

    @Override
    public Garbage remove(Garbage item) {
    	for (int i = 0; i < this.index; i++) {
            if (plasticRecycleBin[i] == item){
                Garbage removedItem = plasticRecycleBin[i];
                for (int j = i; j < this.index; j++) {
                    plasticRecycleBin[j] = plasticRecycleBin[j+1];
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
            if(plasticRecycleBin[i] == null) {
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
        for(int i = 0; i < plasticRecycleBin.length; i++) {
            if(plasticRecycleBin[i] != null && plasticRecycleBin[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Garbage item) {
        for (int i = 0; i < this.size; i++) {
            if (plasticRecycleBin[i] == item) {
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
	    		if(plasticRecycleBin[i-1].name == plasticRecycleBin[i].name) {
	    			count++;
	    			if(i == index-1) {
	    				System.out.println(plasticRecycleBin[i-1].name + "," + plasticRecycleBin[i-1].type + "," + count );
	    				}
	    			}else {
		    			System.out.println(plasticRecycleBin[i-1].name + "," + plasticRecycleBin[i-1].type + "," + count );
		    			count = 1;
		    			if(i == index-1) {
		    				System.out.println(plasticRecycleBin[i-1].name + "," + plasticRecycleBin[i-1].type + "," + count );
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
