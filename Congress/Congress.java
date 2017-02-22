import edu.princeton.cs.algs4.*;

/* CLIENT */
class Congress{
    public static void main(String[] args) {
        int numberOfStates = StdIn.readInt();
        int numberOfSeats = StdIn.readInt();
        HHQueue pQueue = new HHQueue(numberOfStates);
        for (int i = 0; i < numberOfStates; i++) {
            String stateName = StdIn.readString();
            long statePop = StdIn.readLong();
            pQueue.insert(stateName,statePop);
        }
    }
}
