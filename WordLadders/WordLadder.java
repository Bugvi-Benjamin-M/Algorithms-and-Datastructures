import edu.princeton.cs.algs4.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Class details:
 * Like a Digraph
 *
 * @author Andreas Blanke, blan@itu.dk
 * @version 19-04-2017.
 * @project bads
 */
public class WordLadder extends Digraph {

    private final static boolean DEBUG_MODE_ACTIVE = false; // change me to display more info
    private String[] words;

    public WordLadder(ArrayList<String> words) {
        super(words.size());
        this.words = words.toArray(new String[words.size()]);
        buildEdges();
    }

    private void buildEdges() {
        for (int i = 0; i < words.length; i++) {
            char[] substring = words[i].substring(1,5).toCharArray();
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    char[] word = words[j].toCharArray();
                    if(isConnectable(substring,word)) {
                        addEdge(i,j);
                    }
                }
            }
        }
    }

    public String getWord(int index) {return words[index];}

    private boolean isConnectable(char[] substring, char[] other) {
        for (int i = 0; i < 4; i++) {
            boolean found = false;
            for (int j = 0; j < 5; j++) {
                if (substring[i] == other[j]) {
                    other[j] = '\u0000';
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    public int indexOfWord(String word) {
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        if(args.length != 3) {
            try {
                WordLadder wordLadder = loadBuildFile(args[0]);
                loadInFile(args[1], wordLadder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StdOut.println("Runtime: "+(System.currentTimeMillis()-startTime)+" ms");
    }

    private static void loadInFile(String file, WordLadder wordLadder)
            throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String read = null;
            while((read = reader.readLine()) != null) {
                String[] path = read.split(" ");
                BreadthFirstDirectedPaths BFS = new BreadthFirstDirectedPaths(
                        wordLadder,wordLadder.indexOfWord(path[0]));
                int compared = wordLadder.indexOfWord(path[1]);
                boolean hasPath = BFS.hasPathTo(compared);
                if(DEBUG_MODE_ACTIVE) StdOut.println("Has path: "+hasPath);
                if (hasPath) {
                    if(DEBUG_MODE_ACTIVE) StdOut.print("Dist: ");
                    StdOut.println(BFS.distTo(compared));
                    if(DEBUG_MODE_ACTIVE) {
                        StdOut.print("Path: ");
                        for (int index : BFS.pathTo(compared)) {
                            StdOut.print(" -> " + wordLadder.getWord(index));
                        }
                        StdOut.println();
                    }
                } else {
                    if(DEBUG_MODE_ACTIVE) StdOut.print("Dist: ");
                    StdOut.println("-1");
                }
                if(DEBUG_MODE_ACTIVE) StdOut.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }

    private static WordLadder loadBuildFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        ArrayList<String> words = new ArrayList<>();
        try {
            String read = null;
            while((read = reader.readLine()) != null) {
                words.add(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
        return new WordLadder(words);
    }
}