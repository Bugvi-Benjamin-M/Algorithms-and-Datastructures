import edu.princeton.cs.algs4.*;

/**
 * A representation of a Priority Queue datastructure that utilises the Huntington-Hill method
 * for determining the priority of a state (eg. governed state) and based on that priority is
 * able to determine the number of seats that should be allocated to that individual state.
 * 
 * This current version of this class does not work with generics as the data model representing 
 * a state is implemented directly inside this class. 
 * 
 * Arguably, refactoring and implementing the class to be able to handle generics would 
 * all around have beeen a better solution. However, due to the nature and scope of this 
 * assignment, we deemed the current implementation as sufficient.
 * 
 * @author Andreas Blanke
 * @author Búgvi Magnussen
 * @author Jakob Mollerup
 * @author Niclas Hedam
 * @version 1.0
 */
public class HHQueue {

  private State[] collection;
  private int size;
  
  /**
   * Simple data model representing a governed State.
   */
  class State {
    double priority;
    int allocatedSeats = 1;
    int population;
    String name;
    
    /**
     * Implementation of the Huntington-Hill mathematical formula for calculating the priority of a State.
     * @see https://en.wikipedia.org/wiki/Huntington%E2%80%93Hill_method
     */
    void calculatePriority() {
      priority = (population / Math.sqrt(allocatedSeats * (allocatedSeats + 1)));
    }
  }

  /**
   * Constructor for HH-Queue objects
   * @param capacity The capacity of the data structure, eg. the number of states to be stored in the collection.
   */
  public HHQueue(int capacity) {
    collection = new State[capacity + 1];
    this.size = 0;
  }
  
  /**
   * Creates a State and inserts it into the data structure with a given priority
   */
  public void insert(String stateName, int population) {
    State newState = new State();
    newState.name = stateName;
    newState.population = population;
    newState.calculatePriority();
    collection[++size] = newState;
    swim(size);
  }

  /**
  * A standard swim method of a Priority Queue data structure
  */
  private void swim(int position) {
    while (position > 1 && less(position / 2, position)) {
      exchange(position / 2, position);
      position = position / 2;
    }
  }

  /**
  * A standard swim method of a Priority Queue data structure
  */
  private void sink(int position) {
    while (2 * position <= size) {
      int counter = 2 * position;
      if (counter < size && less(counter, counter + 1)) {
        counter++;
      }
      if (!less(position, counter)) {
        break;
      } else {
        exchange(position, counter);
        position = counter;
      }
    }
  }

  /**
   * Checks whether the priority of one State is less than the priority of another State.
   * @param positionOfParent  The position in the collection of the State to be compared
   * @param positionOfChild   The position in the collection of the State to be compared with
   */
  private boolean less(int positionOfParent, int positionOfChild) {
    return collection[positionOfParent].priority < collection[positionOfChild].priority;
  }

  /**
   * Exchanges the position of two States in the collection.
   * @param positionOfParent  The position in the collection of the State to be exchanged
   * @param positionOfChild   The position in the collection of the State to be exchanged with
   */
  private void exchange(int positionOfParent, int positionOfChild) {
    State parent = collection[positionOfParent];
    State child = collection[positionOfChild];
    collection[positionOfParent] = child;
    collection[positionOfChild] = parent;
  }
  
  /**
   * Adds a seat to the State with the currently highest priority and recalculates the priority of that State.
   */
  public void addSeat() {
    collection[1].allocatedSeats++;
    collection[1].calculatePriority();
    sink(1);
  }
  
  /**
   * Prints out all states represented in the data structure and their current amount of allocated seats.
   * The information for each state will be printed in a order based on their current priority.
   */
  public void printContents() {
    for (int i = 1; i < collection.length; i++) {
      StdOut.println(collection[i].name + " " + collection[i].allocatedSeats);
    }
  }
}
