import edu.princeton.cs.algs4.*;

import java.util.*;

/**
 * @author Andreas Blanke
 * @version 02-04-2017
 * @project bads
 */
public class GorillaBigClient {

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
                double result = Gorilla.vectorCosAngle(compare, list.get(i).getValues());
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
