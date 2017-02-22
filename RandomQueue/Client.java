import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// File: Tests.java
public class Client {
    public static void main(String[] args) {
        // Build a queue containing the Integers 1,2,...,6:
        RandomQueue<Integer> Q= new RandomQueue<Integer>();
        for (int i = 1; i < 1000000; ++i) Q.enqueue(i); // autoboxing! cool!
        for (int i = 0; i < 1000000; ++i) Q.sample();

        Iterator<Integer> I= Q.iterator();
        while (I.hasNext()) I.next();
        while(! Q.isEmpty() ) Q.dequeue();
        StdOut.println("Finished");
    }
}
