import edu.princeton.cs.algs4.*;


/**
 * This class is an improvement on the runsort class. It uses insertion sort instead
 * of merge sort for small runs. 
 * @author Niclas Hedam
 * @author Jakob Mollerup
 * @author Andreas Blanke
 * @author Búgvi Magnussen
 * 
 * @version 1.0
 */
public class FancyRunSort {
        private static Comparable[] aux; // auxiliary array for merges
        private static int numberOfRounds; // Number of rounds used to sort input
        private static int numberOfMergeRuns; // Number of merges used to sort the input
        private static int numberOfInsertionRuns; // Number of insertion sort calls to sort the input
        
        /**
         * Sort method that takes an array of Comprable and sorts the input in increasing
         * order. 
         * @param a Comparable array to be sorted. 
         */
        public static void sort(Comparable[] a) {
            
            // initialise values
                aux = new Comparable[a.length];
                int low = 0;
                int high = 0;
                int mid = 0;
                numberOfRounds = 0;
                numberOfMergeRuns = 0;
                numberOfInsertionRuns = 0;
                
                //Initiate sort
                //Identify suquences, i.e. subarrays. 
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

                        }
                }
        }
        
        /**
         * Merge two sorted subarrays. Positions given as parameters to identify
         * where the subarrays are positioned in the array.
         * @param the array containing the subarrays to be sorted and merged
         * @param start of first subarray
         * @param end of first subarray
         * @param end of second subarray
         * 
         */
        public static void
        merge(Comparable[] a, int lo, int mid,
              int hi) { 
                int i = lo, j = mid + 1;
                //Copy to auxiliary array
                for (int k = lo; k <= hi; k++) 
                        aux[k] = a[k];
                //sort and merge back to original array in sorted order. 
                for (int k = lo; k <= hi; k++) 
                        if (i > mid)
                                a[k] = aux[j++];
                        else if (j > hi)
                                a[k] = aux[i++];
                        else if (less(aux[j], aux[i]))
                                a[k] = aux[j++];
                        else
                                a[k] = aux[i++];
        }
        
        /**
         * Standard insertion sort method. Sorts the given array in increasing order
         * between the given positions.
         * @param array to be sorted
         * @param start position
         * @param end position
         */
    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }
    
    /**
     * Standard exchange method. Exchanges two elements in a given array. 
     * @param the relevant array
     * @param position of element one
     * @param position of element two
     */
        private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
        
        /**
         * Standard less method. Compares two elements. 
         * @param first element to be compared
         * @param second element to be compared
         * @return true if the first element is less than the second, false otherwise. 
         */
        @SuppressWarnings("unchecked")
        private static boolean less(Comparable v, Comparable w) {
                return v.compareTo(w) < 0;
        }
        
        /**
         * Checks whether the given array is sorted 
         * @param the array to check
         * @return true if the array is sorted in increasing order, otherwise false. 
         */
        public static boolean isSorted(
            Comparable[] a) { // Test whether the array entries are in order.
                for (int i = 1; i < a.length; i++)
                        if (less(a[i], a[i - 1]))
                                return false;
                return true;
        }
        
        /**
         * private method used by the sort algorithm to identify natural sequences and 
         * determine subarrays.
         * @param the array to be analysed. 
         * @param the start position of the analysis
         * @return the end position of the analysis
         */
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
        
        /**
         * Prints the contents of a given array. 
         * Prints information about the steps taken by the program
         * @param the array to print. 
         */
        private static void show(Comparable[] a) {
                // Print the array, on a single line.
                for (int i = 0; i < a.length; i++)
                StdOut.print(a[i] + " ");
                StdOut.println();
                //Print runtime information
                StdOut.println("Rounds: " + numberOfRounds);
                StdOut.println("Merges: " + numberOfMergeRuns + "\n" + "Insertions: " + numberOfInsertionRuns);
        }
        
        /**
         * This method executes the program and acts as client. 
         */
        public static void main(String[] args) {
                // Read strings from standard input, sort them, and print.
                String[] a = StdIn.readAllStrings();
                sort(a);
                assert isSorted(a);
                show(a);
                
        }
}
