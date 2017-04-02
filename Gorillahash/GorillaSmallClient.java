import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

/**
 * @author Andreas Blanke
 * @version 02-04-2017
 * @project bads
 */
public class GorillaSmallClient {

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
                StdOut.println("" + Gorilla.vectorCosAngle(compare, list.get(i).getValues()));
        }
    }
}
