public interface IBag<T> {
    
    /** Adds a new entry to this bag.
    * @param newItem The object to be added as a new entry.
    * @return True if the addition is successful, or False if not.
    */
    public boolean add(T newItem);

    /** Sees whether the bag is empty.
     * @return True if the bag is empty, or False if not.
     */
    public boolean isEmpty();

    /** Sees whether is the bag is full.
     * @return True if the bag is full, or False if not.
     */
    public boolean isFull();

    /** Removes the item by its index from the bag.
     * @param index -> index of the item.
     * @return The element of the bag.
     */
    public T removeByIndex(int index);

    /** Removes one unspecified entry -the last one- from the bag, if possible.
     * @return Either the removed entry, if the removal was successful, or null.
     */
    public T remove();

    /**
     * Removes the item by its name
     * @param item -> item's name,
     * @return Element of the bag.
     */
    public T remove(T item);
    
    /** Gets the total item number in the bag.
     * @return total number of items
     */
    public int getItemCount();
    
    /** Gets the index of the item.
     * @param item -> item's name
     * @return index of the item in the bag
     */
    public int getIndexOf(T item);

    /** Tests whether this bag contains a given entry,
     * @param item -> The entry to locate
     * @return True if the bag contains item, or false if not.
     */
    public boolean contains(T item);

    /** Displays all items in the bag.
     */
    public void displayItems();

    /** Dumps all entries from the bag.
     */
    public void dump();
    
    /** Transfers item from a bag to another.
     * @param targetBag -> name of the target bag
     * @param item -> name of the item
     * @return true if transfers, false otherwise
     */
    public boolean transferTo(IBag<T> targetBag, T item);
}