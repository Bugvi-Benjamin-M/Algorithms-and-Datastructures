import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item>{
    public RandomQueue(){
        // create an empty random queue
    }
    public boolean isEmpty(){
        // is it empty?
        return false;
    }
    public int size(){
        // return the number of elements
        return 0;
    }
    public void enqueue(Item item){
        // add an item
    }
    public Item sample(){
        // return (but do not remove) a random item
        return null;
    }
    public Item dequeue(){
        // remove and return a random item
        return null;
    }
    public Iterator<Item> iterator(){
        // return an iterator over the items in random order
        return null;
    }
}
