import edu.princeton.cs.algs4.*;

/* CLIENT */
class Congress{
    public static void main(String[] args) {
        int numberOfStates = Integer.parseInt(StdIn.readLine());
        int numberOfSeats = Integer.parseInt(StdIn.readLine());
        HHQueue pQueue = new HHQueue(numberOfStates);
        for (int i = 0; i < numberOfStates; i++) {
            String stateName = StdIn.readLine();
            int statePop = Integer.parseInt(StdIn.readLine());
            pQueue.insert(stateName,statePop);
        }
        for(int i = numberOfStates ; i < numberOfSeats ; i++){
            pQueue.addSeat();
        }
        pQueue.printContents();

    }
}
