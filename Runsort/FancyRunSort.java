import edu.princeton.cs.algs4.*;

public class FancyRunSort {
        private static Comparable[] aux; // auxiliary array for merges
        private static int numberOfRounds;
        private static int numberOfMergeRuns;
        private static int numberOfInsertionRuns;
        public static void sort(Comparable[] a) {
                aux = new Comparable[a.length];
                int low = 0;
                int high = 0;
                int mid = 0;
                numberOfRounds = 0;
                numberOfMergeRuns = 0;
                numberOfInsertionRuns = 0;

                while (true) {
                        while (low < a.length - 1) {

                                mid = findPointer(a, low);
                                if (mid == a.length - 1) {
                                        break;
                                }
                                high = findPointer(a, mid + 1);
                                
                                if((high - low) > 8) {
                                    merge(a, low, mid, high);
                                    numberOfMergeRuns++;
                                } else {
                                    insertionSort(a, low, high);
                                    numberOfInsertionRuns++;
                                }
                                low = high + 1;
                        }
                        if (isSorted(a)) {
                                break;
                        } else {
                                low = 0;
                                numberOfRounds++;
                                StdStats.plotBars(a);
                        }
                }
        }

        public static void
        merge(Comparable[] a, int lo, int mid,
              int hi) { // Merge a[lo..mid] with a[mid+1..hi].
                int i = lo, j = mid + 1;
                for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
                        aux[k] = a[k];

                for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
                        if (i > mid)
                                a[k] = aux[j++];
                        else if (j > hi)
                                a[k] = aux[i++];
                        else if (less(aux[j], aux[i]))
                                a[k] = aux[j++];
                        else
                                a[k] = aux[i++];
        }
        
    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }
        private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

        private static boolean less(Comparable v, Comparable w) {
                return v.compareTo(w) < 0;
        }

        public static boolean isSorted(
            Comparable[] a) { // Test whether the array entries are in order.
                for (int i = 1; i < a.length; i++)
                        if (less(a[i], a[i - 1]))
                                return false;
                return true;
        }

        private static int findPointer(Comparable[] a, int start) {
                while (start < a.length) {
                        if (start == a.length - 1) {
                                break;

                        } else if (less(a[start + 1], a[start])) {
                                break;
                        }
                        start++;
                }
                return start;
        }

        private static void show(Comparable[] a) {
                // Print the array, on a single line.
                for (int i = 0; i < a.length; i++)
                StdOut.print(a[i] + " ");
                StdOut.println();
                StdOut.println("Rounds: " + numberOfRounds);
                StdOut.println("Merges: " + numberOfMergeRuns + "\n" + "Insertions: " + numberOfInsertionRuns);
        }

        public static void main(String[] args) {
                // Read strings from standard input, sort them, and print.
                String[] a = StdIn.readAllStrings();
                sort(a);
                assert isSorted(a);
                show(a);
                
        }
}
