import edu.princeton.cs.algs4.*;
import java.util.*;

public class Gorilla{

    int dimension = 10000;  //d
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
        this.put(proteinCode);
        // this.proteinCode.append(proteinCode);
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
                values[keyHashCode]++;
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
        return (s.hashCode() & 0x7fffffff) % dimension;
    }
    
    public void addSubstrings(){
        int counter = 0;
        String proteinCode = this.proteinCode.toString();
        StdOut.println("Size: "+proteinCode.length()+" divided by "+substringLength);
        for(int i = 0 ; i <= proteinCode.length() - substringLength ; i++){
            put(proteinCode.substring(i, i + substringLength));
            counter++;
            if (counter % 100 == 0) StdOut.println("... substrings: "+counter);
        }
    }
    
    public static double vectorLength(int[] coordinates){
        int sum = 0;
        for(int i : coordinates){
            sum += i * i;
        }
        return Math.sqrt(sum);
    }
    
    public static double vectorAngle(int[] a, int[] b){
        double angle;
        angle = dotProduct(a, b) / (vectorLength(a) * vectorLength(b));
        return angle;
    }
    
    public static int dotProduct(int[] a, int[] b){
        int sum = 0;
        
        for(int i = 0 ; i < a.length ; i++){
            sum += a[i]*b[i];
        }
        
        return sum;
    }
    
    
    public static void main(String[] args){
        ArrayList<Gorilla> list = new ArrayList<Gorilla>();
        
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
                name = input.substring(indexOfName + 1, input.length());
                gorilla = new Gorilla(name);
                list.add(gorilla);
            } // ignore if both is there (impossible)
            if(nCodes % 1000 == 0) StdOut.println(""+name+":"+nCodes);
        }

        /*
        StdOut.println("Finding and adding substrings...");
        for(Gorilla g : list){
            g.addSubstrings();
            StdOut.println("Added substrings to: "+g.name);
        }*/

        StdOut.println("Comparing gorillas...");
        for (int j = 0; j < list.size(); j++) {
            int[] compare = list.get(j).getValues();
            for (int i = 0; i < list.size(); i++) {
                StdOut.println(list.get(i).getName());
                StdOut.println("" + vectorAngle(compare, list.get(i).getValues()));
            }
        }
    }
}