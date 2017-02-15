// Alt denne kode skal indsættes ind i RandomQueue klassen
public class Client {

      // The main method below tests your implementation. Do not change it.
      public static void main(String args[])
      {
            // Build a queue containing the Integers 1,2,...,6:
            RandomQueue<Integer> Q= new RandomQueue<Integer>();
            for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

            // Print 30 die rolls to standard output
            StdOut.print("Some die rolls: ");
            for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() +" ");
            StdOut.println();

            // Let's be more serious: do they really behave like die rolls?
            int[] rolls= new int [10000];
            for (int i = 0; i < 10000; ++i)
              rolls[i] = Q.sample(); // autounboxing! Also cool!
            StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
            StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
              StdStats.stddev(rolls));

            // Now remove 3 random values
            StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
            // Add 7,8,9
            for (int i = 7; i < 10; ++i) Q.enqueue(i);
            // Empty the queue in random order
            while (!Q.isEmpty()) StdOut.print(Q.dequeue() +" ");
            StdOut.println();

            // Let's look at the iterator. First, we make a queue of colours:
            RandomQueue<String> C= new RandomQueue<String>();
            C.enqueue("red"); C.enqueue("blue"); C.enqueue("green"); C.enqueue("yellow");

            Iterator I= C.iterator();
            Iterator J= C.iterator();

            StdOut.print("Two colours from first shuffle: "+I.next()+" "+I.next()+" ");

            StdOut.print("\nEntire second shuffle: ");
            while (J.hasNext()) StdOut.print(J.next()+" ");

            StdOut.print("\nRemaining two colours from first shuffle: "+I.next()+" "+I.next());
      }

}