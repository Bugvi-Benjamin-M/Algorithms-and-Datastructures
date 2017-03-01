class RunSort
{
  private static Comparable[] aux; // auxiliary array for merges
  // See page 271 for merge() code.
  public static void sort(Comparable[] a)
  { // Do lg N passes of pairwise merges.
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
}
