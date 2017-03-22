import edu.princeton.cs.algs4.*;
import java.util.*;

public class Gorilla{

    int dimension = 10000;  //d
    static int substringLength = 20;  //k
    String[] keys;
    int[] values;
    String name;
    String proteinCode = "";
    
    public Gorilla(String name){
        keys = new String[dimension];
        values = new int[dimension];
        this.name = name;
    }
    
    public void appendProteinCode(String proteinCode){
        this.proteinCode = this.proteinCode + proteinCode;
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
        for(int i = 0 ; i <= proteinCode.length() - substringLength ; i++){
            put(proteinCode.substring(i, i + substringLength));
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
        
        String input;
        Gorilla gorilla = new Gorilla("Gorilla for life");
        
        while(!StdIn.isEmpty()){
            input = StdIn.readLine();
            if(input.charAt(0) == '>'){
                gorilla = new Gorilla(input);
                list.add(gorilla);
            }
            else{
                gorilla.appendProteinCode(input);
            }
        }
        
        for(Gorilla g : list){
            g.addSubstrings();
        }
        
        int[] compare = list.get(0).getValues();
        for(int i = 0 ; i < list.size() ; i++){
            StdOut.println(list.get(i).getName());
            StdOut.println("" + vectorAngle(compare, list.get(i).getValues()));
        
        }
        
    }
}