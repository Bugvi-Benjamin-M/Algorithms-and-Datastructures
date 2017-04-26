import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

/**
 * @author Andreas Blanke
 * @version 02-04-2017
 * @project bads
 */
public class GorillaSmallClient {

    public static void main(String[] args){
        ArrayList<Gorilla> list = new ArrayList();

        String input; int dimension = 10000;
        Gorilla gorilla = new Gorilla("Gorilla for life",10000);

        while(!StdIn.isEmpty()){
            input = StdIn.readLine();
            if(input.charAt(0) == '>'){
                gorilla = new Gorilla(input,10000);
                list.add(gorilla);
            } else{
                gorilla.appendProteinCode(input);
            }
        }

        for(Gorilla g : list){
            g.addSubstrings();
        }

        for (int i = 0; i < list.size(); i++) {
            int[] compare = list.get(i).getValues();

            for(int j = 0 ; j < list.size() ; j++){
                StdOut.println(list.get(j).getName());
                StdOut.println("" + Gorilla.vectorCosAngle(compare, list.get(j).getValues()));
            }
        }
    }
}
