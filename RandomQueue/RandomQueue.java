import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item>{
    private int size;

    public RandomQueue(){
        // create an empty random queue
    }
    public boolean isEmpty(){
        return size == 0; // is it empty?
    }
    public int size(){
        return size; // return the number of elements
    }
    public void enqueue(Item item){
        // add an item
        size++;
    }
    public Item sample(){
        // return (but do not remove) a random item
        return null;
    }
    public Item dequeue(){
        // remove and return a random item
        size--;
        return null;
    }
    public Iterator<Item> iterator(){
        // return an iterator over the items in random order
        return null;
    }
}
