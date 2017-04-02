import edu.princeton.cs.algs4.*;
import java.util.*;

public class Gorilla{

    int dimension = 100000000;  //d
    static int substringLength = 20;  //k
    String[] keys;
    int[] values;
    String name;
    StringBuilder proteinCode;

    public Gorilla(String name){
        proteinCode = new StringBuilder();
        keys = new String[dimension];
        values = new int[dimension];
        this.name = name;
    }

    public void appendProteinCode(String proteinCode){
        this.proteinCode.append(proteinCode);
    }

    public String getName(){
        return name;
    }

    public int[] getValues(){
        return values;
    }

    public void put(String key){
        int keyHashCode = hashCode(key);

        while(keys[keyHashCode] != null){
            if(key.equals(keys[keyHashCode])){
                values[keyHashCode] += 1;
                break;
            }
            keyHashCode++;
            if(keyHashCode == keys.length) keyHashCode = 0;
        }

        if(keys[keyHashCode] == null){
          keys[keyHashCode] = key;
          values[keyHashCode] = 1;
        }

    }

    public int hashCode(String s){
        int hashcode = s.hashCode();
        return (hashcode & 0x7fffffff) % dimension;
    }

    public void addSubstrings(){
        int counter = 0; int n = proteinCode.length();
        String proteinCode = this.proteinCode.toString();
        this.proteinCode = null;
        // StdOut.println("Size: "+n+" divided by "+substringLength);
        for(int i = 0 ; i <= n - substringLength; i++){
            put(proteinCode.substring(i, i + substringLength));
            // proteinCode.delete(i, i + substringLength);
            counter++;
            // if (counter % 1000 == 0)
              // StdOut.println("count: "+counter+" i:"+i);
        }
        proteinCode = null; n = 0; counter = 0;
        // StdOut.println("Adding terminated...");
    }

    public static double vectorLength(int[] coordinates){
        long sum = 0;
        for(int i : coordinates){
            sum += i * i;
        }
        return Math.sqrt(sum);
    }

    public static double vectorCosAngle(int[] a, int[] b){
        double angle;
        angle = (double) (dotProduct(a, b)) / (vectorLength(a) * vectorLength(b));
        return angle;
    }

    public static long dotProduct(int[] a, int[] b){
        long sum = 0;

        for(int i = 0 ; i < a.length ; i++){
            sum += a[i]*b[i];
        }

        return sum;
    }

}
