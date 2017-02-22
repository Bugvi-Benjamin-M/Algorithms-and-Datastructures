import edu.princeton.cs.algs4.*;
/**
 * Priory Queue using the Huntington-Hill method
 */
public class HHQueue{

    private State[] collection;
    private int size;

    class State {
        double priority;
        int allocatedSeats = 1;
        int population;
        String name;

        void calculatePriority() {
            priority = (population /
                    Math.sqrt(
                            allocatedSeats * (allocatedSeats + 1)));
        }
    }

    public HHQueue(int capacity) {
        collection = new State[capacity+1];
        this.size = 0;
    }

    public void insert(String stateName, int population) {
        State newState = new State();
        newState.name = stateName;
        newState.population = population;
        newState.calculatePriority();
        collection[++size] = newState;
        swim(size);
    }

    private void swim(int position) {
        while(position > 1 && less(position/2,position)) {
            exchange(position/2,position);
            position = position/2;
        }
    }

    private void sink(int position) {
        while (2*position <= size) {
            int counter = 2*position;
            if (counter < size && less(counter,counter+1)) {
                counter++;
            }
            if(!less(position,counter)) {
                break;
            } else {
                exchange(position,counter);
                position = counter;
            }
        }
    }

    private boolean less(int positionOfParent, int positionOfChild) {
        return collection[positionOfParent].priority
                < collection[positionOfChild].priority;
    }

    private void exchange(int positionOfParent, int positionOfChild) {
        State parent = collection[positionOfParent];
        State child = collection[positionOfChild];
        collection[positionOfParent] = child;
        collection[positionOfChild] = parent;
    }
    
    public void addSeat(){
        collection[1].allocatedSeats++;
        collection[1].calculatePriority();
        sink(1);
    }
    
    public void printContents(){
        for(int i = 1 ; i < collection.length ; i++){
            StdOut.println(collection[i].name + " " + collection[i].allocatedSeats);
        }
    }
}
