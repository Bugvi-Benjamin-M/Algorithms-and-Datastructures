import edu.princeton.cs.algs4.*;

/**
 * This class builds on the RunSort class by adding a visual representation
 * of the array. The visual representation is updated as the array gets sortet.
 * This class only acceps double[] instead of Comparable[].
 * @author Niclas Hedam
 * @author Jakob Mollerup
 * @Andreas Blanke
 * @Búgvi Magnussen
 * @version 1.0
 */
class VisualTraceRunSort {
    // auxiliary array for merges
        private static double[] aux; 
        //Number of Operations used to sort the array
        private static int numberOfOperations;
        //Number of runs used to sort the array
        private static int numberOfRuns;
        
        /**
         * This method is almost the same as in the RunSort class. 
         * However, it scans the given array, finds the max and minimum and converts
         * the values in the array to a new number range between 0.0 and 1.0 while
         * maintaining ratio. This is to ensure that the visual representation works
         * as intended. 
         * @param the array to be sorted. 
         */
        public static void sort(double[] a) {
            
            //Find max and minimum in the array.
                double max = 0;
                double min = 0;
                for(int i = 0 ; i < a.length ; i++) {
                    double temp =  a[i];
                    if (temp > max) {
                        max = temp;
                    }
                    if (temp < min) {
                        min = temp; 
                    }
                }
                //Based on max and minimum value, compute a scaler and use it to
                //convert all the values in the array to the new number range between 0.0 and 1.0
                double scaler = (1.0 - 0.0) / (max - min);
            for(int i = 0 ; i < a.length ; i++) {
                double newValue = (a[i] - min) * scaler + 0.0;
                a[i] = newValue;
            }
            //Initialise values and create auxiliary array
                aux = new double[a.length];
                int low = 0;
                int high = 0;
                int mid = 0;
                numberOfOperations = 0;
                numberOfRuns = 0;
                //Draw the array in its initial state
                StdDraw.setPenColor(StdDraw.BLACK);
                StdStats.plotBars(a);
                StdDraw.pause(1000);
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
                            //If done, draw the array one last time
                            StdDraw.setPenColor(StdDraw.BLACK);
                                StdDraw.clear();
                                StdStats.plotBars(a);
                                StdDraw.pause(1000);
                                break;
                        } else {
                            //For every iteration draw the state of the array
                            StdDraw.setPenColor(StdDraw.BLACK);
                                StdDraw.clear();
                                StdStats.plotBars(a);
                                StdDraw.pause(1000);
                                low = 0;
                                numberOfOperations++;
                        }
                }
        }
        
        /**
         * This method is identical to the merge method in the RunSort class.
         * @param all identical to the method in the RunSort class. 
         */
        public static void
        merge(double[] a, int lo, int mid,
              int hi) { 
                int i = lo, j = mid + 1;
                for (int k = lo; k <= hi; k++) {
                        aux[k] = a[k];
                }

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
         * Identical to the less method in the RunSort class.
         * @param identical to the RunSort class
         */
        private static boolean less(Double v, Double w) {
                return v.compareTo(w) < 0;
        }
        
        /**
         * Identical to the isSorded method in the RunSort class.
         * @param same as in the RunSort class
         */
        public static boolean isSorted(
            double[] a) { // Test whether the array entries are in order.
                for (int i = 1; i < a.length; i++)
                        if (less(a[i], a[i - 1]))
                                return false;
                return true;
        }
        /**
         * Identical to the findPointer method in the RunSort class.
         * @param same as RunSort class
         * @return same as RunSort class
         */
        private static int findPointer(double[] a, int start) {
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
         * This method converts the given string array into a double array
         * @param a string array to be converted
         * @return the converted array of doubles
         */
        public static double[] convertToDoubles(String[] st) {
            double[] a = new double[st.length];
            for(int i = 0 ; i < st.length ; i++) {
                a[i] = Double.parseDouble(st[i]);
            }
            return a;
        }
        
        /**
         * This method is identical to the show method in the RunSort class.
         * @param identical to the RunSort class.
         */
        private static void show(double[] a) {
                // Print the array, on a single line.
                for (int i = 0; i < a.length; i++)
                    StdOut.print(a[i] + " ");
                    StdOut.println();
                }
        
        /**
         * This method executes the program and acts as a client. 
         */
        public static void main(String[] args) {
                // Read strings from standard input, sort them, and print.          
                String[] st = StdIn.readAllStrings();   
                double[] a = convertToDoubles(st);
                sort(a);
                assert isSorted(a);
                show(a);
                StdOut.println(numberOfOperations);
                StdOut.println(numberOfRuns);
        }
}
