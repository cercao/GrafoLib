package desafios;
import java.util.ArrayList;
import java.util.Arrays;

public class CliqueMaxima {	
	int [][] A; // matriz adjacente 0-1
	int n; // numero de vertices
	long nodes; // numero de decisoes
    long timeLimit ; // miliseconds
    long cpuTime ; // miliseconds
    int maxSize ; // tamanho da clique maxima
    int [] solution; // solução encontra

    CliqueMaxima (int n, int [][] A){
    	this.n = n;
    	this.A = A;    	
    	nodes = maxSize = 0;
    	cpuTime = timeLimit = -1;    	
    	solution = new int[n];
    }

    void buscar(){
    	cpuTime = System.currentTimeMillis();
    	nodes = 0;
    	ArrayList<Integer> C = new ArrayList<Integer>();
    	ArrayList<Integer> P = new ArrayList<Integer>(n);
    	for(int i =0;i<n ; i++) P.add(i);
    	expand(C, P);
    }

    void expand (ArrayList<Integer> C , ArrayList<Integer> P) {
    	if (timeLimit > 0 && System.currentTimeMillis() - cpuTime >= timeLimit ) return ;
    	nodes++;
    	for(int i=P.size() -1; i >=0; i--){
    		if(C.size() + P.size() <= maxSize ) return ;
    		int v = P.get(i) ;
    		C.add(v) ;
    		ArrayList<Integer> newP = new ArrayList<Integer>() ;
    		for(int w : P) if(A[v][w] == 1 ) newP.add(w);
    		if(newP.isEmpty() && C.size() > maxSize) saveSolution(C) ;
    		if(!newP.isEmpty()) expand(C, newP);
    		C.remove((Integer)v);
    		P.remove((Integer)v);
    	}
    }
    
	public String printSolution() {
		String saida =  "";
		for (int i = 0; i < solution.length; i++) {			
			saida += " " + solution[i];
		}
		
		return saida;
	}

    void saveSolution(ArrayList<Integer> C) {
    	Arrays.fill(solution, 0);
    	for(int i : C)
    		solution[i] = 1;
    	maxSize = C.size();
    }
}
