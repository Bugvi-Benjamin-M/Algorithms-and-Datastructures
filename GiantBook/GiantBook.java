import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;
import java.util.Random;

public class  GiantBook {
    private MyUnionFind myUnionFind;
    private static Random random = new Random();
    public int stepsUntilConnected;
    public int stepsUntilGiant;
    public int stepsUntilNotIsolated;
    
    public GiantBook(int n){
        myUnionFind = new MyUnionFind(n); 
    }
    
    public static void main(String[] args){
        GiantBook giantBook;
        if(args.length == 0){
            int n = StdIn.readInt();
            giantBook = new GiantBook(n);
            giantBook.findStepsWithInput();
            giantBook.printResults();
        }
        else{
            int n = Integer.parseInt(args[0]);
            int t = Integer.parseInt(args[1]);
            int[] isolatedResults = new int[t];
            int[] giantResults = new int[t];
            int[] connectedResults = new int[t];
            for(int i = 0 ; i < t ; i++){
                giantBook = new GiantBook(n);
                giantBook.findStepsWithRandom();
                isolatedResults[i] = giantBook.stepsUntilNotIsolated;
                giantResults[i] = giantBook.stepsUntilGiant;
                connectedResults[i] = giantBook.stepsUntilConnected;
            }
            double isolatedMean = StdStats.mean(isolatedResults);
            double isolatedStdDev = StdStats.stddev(isolatedResults);
            double giantMean = StdStats.mean(giantResults);
            double giantStdDev = StdStats.stddev(giantResults);
            double connectedMean = StdStats.mean(connectedResults);
            double connectedStdDev = StdStats.stddev(connectedResults);
  
            StdOut.println("N = " + n);
            StdOut.println("T = " + t);
            StdOut.print("Isolated Mean StdDev");
            StdOut.printf("%.2e ",isolatedMean);
            StdOut.printf("%.2e\n",isolatedStdDev);
            
            StdOut.print("Giant Mean StdDev");
            StdOut.printf("%.2e ",giantMean);
            StdOut.printf("%.2e\n",giantStdDev);
            
            StdOut.print("Connected Mean StdDev");
            StdOut.printf("%.2e ",connectedMean);
            StdOut.printf("%.2e\n",connectedStdDev);
        }
        
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
        return myUnionFind.getAmountOfIsolatedSites() != 0;
    }
    
    public boolean isGiant(){
        return (myUnionFind.getSizeOfBiggestComponent()*2 >= myUnionFind.getN());
    }
    
    public boolean isAllConnected(){
        return myUnionFind.isAllConnected();  
    }
}
