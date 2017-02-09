import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Random;

public class  GiantBook {
    private MyUnionFind myUnionFind;
    private static Random random = new Random();
    private int stepsUntilConnected;
    private int stepsUntilGiant;
    private int stepsUntilNotIsolated;
    
    public GiantBook(int n){
        myUnionFind = new MyUnionFind(n);
        
    }
    public static void main(String[] args){
        GiantBook giantBook;
        if(args.length != 1){
            int n = StdIn.readInt();
            giantBook = new GiantBook(n);
            giantBook.findStepsWithInput();
        }
        else{
            int n = Integer.parseInt(args[0]);
            giantBook = new GiantBook(n);
            giantBook.findStepsWithRandom();
        }
        giantBook.printResults();
    }
    public void findStepsWithInput(){
        while (!StdIn.isEmpty()){
            analyze();
            addInputPair();
            
        }
    }
    
    public void findStepsWithRandom() {
        while(!isAllConnected() || !isGiant() || isIsolated()){
            addRandomPair();
            analyze();
        }
    }
    
    public void printResults(){
        StdOut.print(myUnionFind.getN() + " ");
        StdOut.print(stepsUntilNotIsolated + " ");
        StdOut.print(stepsUntilGiant + " ");
        StdOut.print(stepsUntilConnected + "\n");
    }
    
    public void analyze(){
        if(!isAllConnected()){stepsUntilConnected++;}
        if(!isGiant()){stepsUntilGiant++;}
        if(isIsolated()){stepsUntilNotIsolated++;}         
    }
    
    public void addRandomPair(){
        int p = random.nextInt(myUnionFind.getN());
        int q = random.nextInt(myUnionFind.getN());
        while(p == q){
            p = random.nextInt(myUnionFind.getN());
        }
        if (myUnionFind.connected(p, q)) return;
        myUnionFind.union(p, q);
    }
    
    public void addInputPair(){
        int p = StdIn.readInt();
        int q = StdIn.readInt();
        if (myUnionFind.connected(p, q)) return;
        myUnionFind.union(p, q);
    }
    
    public boolean isIsolated(){
        return myUnionFind.getAmountOfIsolatedSites() == 0;
    }
    
    public boolean isGiant(){
        return (myUnionFind.getSizeOfBiggestComponent()*2 >= myUnionFind.getN());
    }
    
    public boolean isAllConnected(){
        return myUnionFind.isAllConnected();
           
    }   
}
