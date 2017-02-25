import edu.princeton.cs.algs4.*;

/** 
* Client class for using the HHQueue data structure.
* Note that this class only works with a specific format of text files.
* 
* The required format of input files is as follows:
*   number of states (integer, ex. 50)
*   number of seats (integer, ex. 435)
*   state name (string, ex. Wisconsin)
*   population of state (integer, ex. 1000000)
*   ad libitum (of states followed by their population)...
* 
* @author Andreas Blanke
* @author Búgvi Magnussen
* @author Jakob Mollerup
* @author Niclas Hedam
* @version 1.0
*/
class Congress {
  
  public static void main(String[] args) {
    int numberOfStates = Integer.parseInt(StdIn.readLine());
    int numberOfSeats = Integer.parseInt(StdIn.readLine());
    HHQueue pQueue = new HHQueue(numberOfStates);
    for (int i = 0; i < numberOfStates; i++) {
      String stateName = StdIn.readLine();
      int statePop = Integer.parseInt(StdIn.readLine());
      pQueue.insert(stateName, statePop);
    }
    for (int i = numberOfStates; i < numberOfSeats; i++) {
      pQueue.addSeat();
    }
    pQueue.printContents();
  }
}
