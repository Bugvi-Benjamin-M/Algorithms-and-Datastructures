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

    public static double vectorAngle(int[] a, int[] b){
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


    public static void main(String[] args){
        ArrayList<Gorilla> list = new ArrayList<>();

        String input, name = "ignore";
        int nCodes = 0;
        Gorilla gorilla = new Gorilla(name);

        while(!StdIn.isEmpty()){
            input = StdIn.readString();
            int indexOfName = input.indexOf('>');
            int indexOfCode = input.indexOf('_');
            //StdOut.println(index);

            if (indexOfName == -1 && indexOfCode == -1) {
                // doesnt have name or code (read as code?)
                gorilla.appendProteinCode(input);
                nCodes++;
            } else if (indexOfName == -1 && indexOfCode != -1) {
                // doesnt have name, have code
                nCodes = 1;
                gorilla.appendProteinCode(
                        input.substring(indexOfCode+1,input.length()));
            } else if (indexOfName != -1 && indexOfCode == -1) {
                // have name but not code
                gorilla.appendProteinCode(
                        input.substring(0,indexOfName));
                nCodes++;
                gorilla.addSubstrings();
                name = input.substring(indexOfName + 1, input.length());
                gorilla = new Gorilla(name);
                list.add(gorilla);
            } // ignore if both is there (impossible)
            // if(nCodes % 1000 == 0) StdOut.println(""+name+":"+nCodes);
        }

        gorilla.addSubstrings();

        int[][] results = new int[list.size()][list.size()];
        StdOut.println("Comparing gorillas...");
        for (int j = 0; j < list.size(); j++) {
            int[] compare = list.get(j).getValues();
            for (int i = 0; i < list.size(); i++) {
                double result = vectorAngle(compare, list.get(i).getValues());
                results[j][i] = (int) (Math.floor(result * 100));
            }
        }

      for (int i = 0; i < list.size(); i++) {
        StdOut.print(list.get(i).name+" ");
      }
      StdOut.println();

      for (int i = 0; i < results.length; i++) {
        StdOut.print(list.get(i).name+":");
        for (int j = 0; j < results[i].length; j++) {
          StdOut.print(" "+results[i][j]+"%");
        }
        StdOut.println();
      }
    }
}
