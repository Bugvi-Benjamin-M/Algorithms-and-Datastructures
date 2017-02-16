import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/**
 * The RandomQueue class represents a collection of items stored in a seemingly random order.
 * The class is built as an array implementation of a Queue and supports operations for adding (enqueue)
 * items to the collection, viewing (sample) a random item in the collection, deleting (dequeue) a random item
 * in the collection and going through the collection in a random order using an Iterator.
 *
 * Note that this collection uses dynamic resizing and hence you can experience performance drops
 * when using the RandomQueue class.
 *
 * @author Andreas Blanke
 * @author Búgvi Magnussen
 * @author Jacob Mollerup
 * @author Niclas Hedam
 */
public class RandomQueue<Item> implements Iterable<Item>{

    // constants
    private static final int DEFAULT_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 2;

    // fields
    private int size = 0;
    private Item[] collection;

    /**
     * Constructor that creates a RandomQueue object using the default capacity of 10.
     */
    public RandomQueue(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor that creates a RandomQueue object using an array of elements
     */
    public RandomQueue(Item[] array){
        this.collection = array;
        this.size = array.length;
    }

    /**
     * Constructor that creates a RandomQueue object using a given capacity
     * @param capacity The initial capacity of elements for the collection
     */
    public RandomQueue(int capacity){
        collection = (Item[])new Object[capacity];
    }

    /**
     * Checks whether the RandomQueue is empty
     */
    public boolean isEmpty(){
        return size == 0; // is it empty?
    }

    /**
     * Returns the size of the RandomQueue (the amount of elements contained in the collection)
     */
    public int size(){
        return size; // return the number of elements
    }

    /**
     * Adds an item to the RandomQueue object.
     *
     * If the object is full of elements (the size is equal to the capacity) then the
     * capacity of the RandomQueue is doubled.
     *
     * Expect performance drops when increasing the capacity.
     */
    public void enqueue(Item item){
        if(collection.length > size)
        {
            collection[size] = item;
            size++;
        }
        else
        {
            increaseSize();
            enqueue(item);
        }  
    }

    /**
     * Finds a random item in the RandomQueue and returns it.
     * This method does not remove any items from the collection.
     */
    public Item sample(){
        if (size == 0){
        throw new RuntimeException();
        }
        return collection[randomInt(size)];
    }

    /**
     * Finds, removes and returns a random item from the RandomQueue.
     *
     * If the RandomQueue contains a number of items around 1/4th of
     * the capacity of the RandomQueue, then it will decrease the object's capacity.
     *
     * Expect performance drops when decreasing the capacity.
     */
    public Item dequeue(){
        if (size == 0){
            throw new RuntimeException("Cannot dequeue from empty queue");
        }
        int random = randomInt(size);
        Item temp = collection[random];
        Item lastItem = collection[size - 1];
        collection[size - 1] = null;
        collection [random] = lastItem;       
        size--;
        
        if(collection.length > DEFAULT_CAPACITY && size*4 <= collection.length)
        {
            decreaseSize();
        }
        
        return temp;
    }

    /**
     * Increase the capacity of the collection.
     */
    private void increaseSize(){
        Item[] temp = collection;
        collection = (Item[]) new Object[size * RESIZE_FACTOR];
        for(int i = 0 ; i < size ; i++)
        {
            collection[i] = temp[i];
        }
    }

    /**
     * Decreases the capacity of the collection.
     */
    private void decreaseSize(){
        Item[] temp = collection;
        collection = (Item[]) new Object[collection.length / RESIZE_FACTOR];
        for(int i = 0 ; i < size ; i++)
        {
            collection[i] = temp[i];
        }
    }

    /**
     * Returns a random number between 0 and the given range (excluding).
     */
    private int randomInt(int range){
        return StdRandom.uniform(range);
    }

    /**
     * Returns a copy of the collection.
     */
    public Item[] toArray() {
        Item[] array = (Item[])new Object[size];
        for(int i = 0 ; i < size ; i++){
            array[i] = collection[i];
        }
        return array;
    }

    /**
     * Returns a Iterator over the items in the collection
     */
    public Iterator<Item> iterator(){
        return new RandomQueueIterator<>(this);
    }

    /**
     * An Iterator implementation for going through the contents of a RandomQueue collection.
     */
    private class RandomQueueIterator implements Iterator<Item> {
        // A copy of the random queue collection
        RandomQueue<Item> randomQueue;

        /**
         * Constructor for creating a RandomQueueIterator using an RandomQueue object
         */
        public RandomQueueIterator(RandomQueue<Item> randomQueue)
        {
            this.randomQueue = new RandomQueue<Item>(randomQueue.toArray());
        }

        /**
         * Checks whether the Iterator contains more elements.
         */
        public boolean hasNext()
        {          
            return !randomQueue.isEmpty();
        }

        /**
         * Finds and returns a random item from the collection.
         */
        public Item next()
        {
            return randomQueue.dequeue();
        }

        /**
         * Not implemented method. Will return a runtime exception.
         * @throws RuntimeException
         */
        @Deprecated
        public void remove(){throw new RuntimeException("Remove is not implemented");}
    }
}
