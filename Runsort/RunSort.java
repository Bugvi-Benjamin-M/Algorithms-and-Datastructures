class RunSort
{
  private static Comparable[] aux; // auxiliary array for merges

  public static void sort(Comparable[] a)
  { 
      aux = new Comparable[a.length];
      int low = 0;
      int high = 0;
      int mid = 0; 
      
      while(true) {
          while(low < a.length-1) {
              mid = findPointer(a, low);
              if(mid == a.length-1) {
                  break;
              }
              high = findPointer(a, mid + 1);
              merge(a, low, mid, high);
              low = high + 1;
          } 
          if(isSorted(a)) {
              break;
          } else {
              low = 0;
          }
      }
  }

  public static void merge(Comparable[] a, int lo, int mid, int hi)
  { // Merge a[lo..mid] with a[mid+1..hi].
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

  private static boolean less(Comparable v, Comparable w)
  {
    return v.compareTo(w) < 0;
  }

  public static boolean isSorted(Comparable[] a)
  { // Test whether the array entries are in order.
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i - 1]))
        return false;
    return true;
  }
  
  private static int findPointer(Comparable[] a, int start) {
      while(start < a.length) {
          if(start == a.length-1) {
              break;
              
              } else if(less(a[start], a[start+1])) {
                  break;
              }
          start++;
      }
      return start;
  }
}

