import java.util.Random;

public class TrashCan implements IBag<Garbage> {

    public Garbage[] trashList = new Garbage[450];// Create an array for store  the garbage objects
    public int index = 0;
    public int size = 450;
    
    // Create instances for recycling bins
    Random random = new Random();
    PlasticRecycleBin plasticRecycleBin = new PlasticRecycleBin((random.nextInt(1,4)*5));
    PaperRecycleBin paperRecycleBin = new PaperRecycleBin((random.nextInt(1,4)*5));
    GlassRecycleBin glassRecycleBin = new GlassRecycleBin((random.nextInt(1,4)*5));
    FabricRecycleBin fabricRecycleBin = new FabricRecycleBin((random.nextInt(1,4)*5));
    MetalRecycleBin metalRecycleBin = new MetalRecycleBin((random.nextInt(1,4)*5));
    OrganicRecycleBin organicRecycleBin = new OrganicRecycleBin((random.nextInt(1,4)*5));

    public boolean add(Garbage newItem) {
        if(isFull()) {
            return false;
        }
        else {
            trashList[index] = newItem;
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
        if(trashList [index] == null) {
            return null;
        }
        else {
            Garbage removedItem = trashList[index];
            trashList[index] = null;
            return removedItem;
        }
    }

    @Override
    public Garbage remove() {
        for(int i = 0; i < this.size; i++) {
            if(trashList[this.size - (i+1)] != null) {
                Garbage removedItem = trashList[this.size - (i+1)];
                trashList[this.size - (i+1)] = null;
                return removedItem;
            }
        }
        return null;
    }

    @Override
    public Garbage remove(Garbage item) {
        if(contains(item)) {
            int itemIndex = getIndexOf(item);
            trashList[itemIndex] = null;
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
            if(trashList[i] == null) {
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
        for(int i = 0; i < trashList.length; i++) {
            if(trashList[i] != null && trashList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Garbage item) {
        for (int i = 0; i < this.size; i++) {
            if (trashList[i] == item) {
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
                if(trashList[i] == null || trashList[i+1] == null){
                    continue;
                }
                if(trashList[i].toString().equals(trashList[i + 1].toString())) {
                    amount++;
                } else {
                    System.out.println(trashList[i].name + ", " + trashList[i].type + ", " + amount);
                    amount = 1;
                }
            }System.out.println(trashList[getItemCount()-1].name + ", " + trashList[getItemCount()-1].type + ", " + amount);
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

    public boolean separate(Garbage item){
        switch (item.type){
            case "plastic":
                if (!plasticRecycleBin.isFull()) {
                    transferTo(plasticRecycleBin, item);
                    return true;}
                break;
            case "paper":
                if (!paperRecycleBin.isFull()) {
                    transferTo(paperRecycleBin, item);
                    return true;}
                break;
            case "glass":
                if (!glassRecycleBin.isFull()) {
                    transferTo(glassRecycleBin, item);
                    return true;}
                break;
            case "fabric":
                if (!fabricRecycleBin.isFull()) {
                    transferTo(fabricRecycleBin, item);
                    return true;}
                break;
            case "metal":
                if (!metalRecycleBin.isFull()) {
                    transferTo(metalRecycleBin, item);
                    return true;}
                break;
            case "organic":
                if (!organicRecycleBin.isFull()) {
                    transferTo(organicRecycleBin, item);
                    return true;}
                break;
            default:
                return false;
        }

        return false;
    }

}