/**
 * Priory Queue using the Huntington-Hill method
 */
public class HHQueue{

    private State[] collection;
    private long size;
    private int capacity;

    class State {
        double priority;
        int allocatedSeats = 1;
        long population;
        String name;

        void calculatePriority() {
            priority = (population /
                    Math.sqrt(
                            allocatedSeats * (allocatedSeats + 1)));
        }
    }

    public HHQueue(int capacity) {
        collection = new State[capacity+1];
        this.capacity = capacity;
        this.size = 0;
    }

    public void insert(String stateName, long population) {
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
}
