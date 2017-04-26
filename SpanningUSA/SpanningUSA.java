import edu.princeton.cs.algs4.*;
import java.util.regex.*;
import java.util.*;

/**
 * Spanning USA assignment. 
 * @author Jakob Mollerup
 * @author Búgvi Magnussen
 * @author Andreas Blanke
 * @author Niclas Hedam
 * 
 */

public class SpanningUSA extends EdgeWeightedGraph {
    
    private static HashMap<String, Integer> cityToIdMap = new HashMap<>();
    private static HashMap<Integer, String> idToCityMap = new HashMap<>();
    private static int counter = 0;
    
    public SpanningUSA() {
        super(counter);
        
    }
    
    
    public static boolean isVertex(String line) {
        return !line.contains("--");
    }
    
    public static int parseVertex(String line) {
        String regex = "\"*(.+)\"*";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(line);
        return stringToInt(m.group(0));
    }
    
    public static Edge parseEdge(String line) {
        String regex = "\"?(.+)\"?--\"?(.+)\"?\\[(.+)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(line);
        Edge edge = new Edge(stringToInt(m.group(0)), stringToInt(m.group(1)), 
                             Integer.parseInt(m.group(2)));
        return edge;
    }
   
    
    public static int stringToInt(String string) {
        if(!cityToIdMap.containsKey(string)) {
            cityToIdMap.put(string, counter);
            idToCityMap.put(counter, string);
            return counter++;
        }      
        return cityToIdMap.get(string);       
    }
    
    public static String getCityName(int i) {
        return idToCityMap.get(i);
    }

    
    public static void main(String[] args) {
        
        Bag<Edge> edges = new Bag<>();
        
        
        while(!StdIn.isEmpty()) {
            String s = StdIn.readLine();
            if(isVertex(s)) {
                parseVertex(s);
            } else {
                Edge edge = parseEdge(s);
                edges.add(edge);
                
            }
        }
        SpanningUSA spanning = new SpanningUSA();
        for(Edge e : edges) {
            spanning.addEdge(e);
        }
    }
}