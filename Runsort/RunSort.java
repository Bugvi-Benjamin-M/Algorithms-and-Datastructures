import edu.princeton.cs.algs4.*;

/**
 * This class implements a runsort algorithm, using several standard bottom up merge sort
 * methods. However, the sort method is behaves differently and utlises natural
 * orders that might already exist in the given array. This is know as natural merge sort. 
 * @author Andreas Blanke
 * @author Jakob Mollerup
 * @author Búgvi Magnussen
 * @author Niclas Hedam
 * @version 1.0
 */

class RunSort {
        // auxiliary array for merges
        private static Comparable[] aux; 
        
        // Number of operations used to sort input
        private static int numberOfOperations;
        
        // Number of rounds used to sort input
        private static int numberOfRuns;
        
        /**
         * The natural merge sort sorting method. Identifies subarrays that are 
         * already sorted in the given array. 
         * @param A Comparable array to be sorted. 
         */
        public static void sort(Comparable[] a) {
            
            //Initialise values
                aux = new Comparable[a.length];
                int low = 0;
                int high = 0;
                int mid = 0;
                numberOfOperations = 0;
                numberOfRuns = 0;
                
                //Initiate sort
                //Identify suquences, i.e. subarrays. 
                while (true) {
                        while (low < a.length - 1) {

                                mid = findPointer(a, low);
                                if (mid == a.length - 1) {
                                        break;
                                }
                                high = findPointer(a, mid + 1);

                                merge(a, low, mid, high);

                                low = high + 1;
                                numberOfRuns++;
                        }
                        if (isSorted(a)) {
                                break;
                        } else {
                                low = 0;
                                numberOfOperations++;
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
                StdOut.println(numberOfOperations);
                StdOut.println(numberOfRuns);
        }
}
