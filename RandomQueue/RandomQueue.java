import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomQueue<Item> implements Iterable<Item>{
    private static final int DEFAULT_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 2;
    private int size = 0;
    private Item[] collection;
    
    // create an empty random queue
    public RandomQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public RandomQueue(Item[] array){
        this.collection = array;
        this.size = array.length;
    }
    
    public RandomQueue(int capacity){
        collection = (Item[])new Object[capacity];
    }
        
    public boolean isEmpty(){
        return size == 0; // is it empty?
    }
    public int size(){
        return size; // return the number of elements
    }
    
    // add an item
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
    
    // return (but do not remove) a random item
    public Item sample(){
        if (size == 0){
        throw new RuntimeException();
        }
        return collection[randomInt(size)];
    }
    
    // remove and return a random item
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
    
    private void increaseSize(){
        Item[] temp = collection;
        collection = (Item[]) new Object[size * RESIZE_FACTOR];
        for(int i = 0 ; i < size ; i++)
        {
            collection[i] = temp[i];
        }
    }
    
    private void decreaseSize(){
        Item[] temp = collection;
        collection = (Item[]) new Object[collection.length / RESIZE_FACTOR];
        for(int i = 0 ; i < size ; i++)
        {
            collection[i] = temp[i];
        }
    }
    
    private int randomInt(int range){
        return StdRandom.uniform(range);
    }
    
    public Item[] toArray()
    {
        Item[] array = (Item[])new Object[size];
        for(int i = 0 ; i < size ; i++){
        array[i] = collection[i];
        }
        return array;
    }
    
    // return an iterator over the items in random order
    public Iterator<Item> iterator(){
        RandomQueue<Item> copy = new RandomQueue<Item>(this.toArray());
        return new RandomQueueIterator(copy);
    }
    
    private class RandomQueueIterator implements Iterator<Item>
    {
        RandomQueue<Item> randomQueue;
        
        public RandomQueueIterator(RandomQueue<Item> randomQueue)
        {
            this.randomQueue = randomQueue;
        }
        
        public boolean hasNext()
        {          
            return !randomQueue.isEmpty();
        }
        
        public Item next()
        {
            return randomQueue.dequeue();
        }
        
        public void remove(){throw new RuntimeException("Remove is not implemented");}
    }
}
