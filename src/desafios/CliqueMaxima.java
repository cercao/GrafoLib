package desafios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliqueMaxima {	
	int [][] A; // matriz adjacente 0-1
	int n; // numero de vertices
	long nDecisoes; // numero de decisoes
    int tamanhoMaximo ; // tamanho da clique maxima
    int [] solucao; // solução encontrada
    int [] solucao01; // solução encontrada 0-1    

    CliqueMaxima (int tamanho, int [][] matriz){
    	this.n = tamanho;
    	this.A = matriz;    	
    	nDecisoes = tamanhoMaximo = 0;    	   
    	solucao01 = new int[n];    	
    	
    }

    void buscar(){    	
    	nDecisoes = 0;
    	ArrayList<Integer> matrizListaX = new ArrayList<Integer>();
    	ArrayList<Integer> matrizListaY = new ArrayList<Integer>(n);    	
    	
    	// Adiciona todos os nós
    	for(int i = 0; i < n ; i++) 
    		matrizListaY.add(i);
    		
    	// Chama recursiva
    	expand(matrizListaX, matrizListaY);
    }
    

    void expand (ArrayList<Integer> nosX , ArrayList<Integer> nosY) {
    	
    	nDecisoes++;
    	// itera os nós
    	for(int i = nosY.size() -1; i >= 0; i--){
    		
    		// se a quantidade de nós em x mais os de y foram menor que o maximo..
    		if(nosX.size() + nosY.size() <= tamanhoMaximo ) 
    			return ;
    		
    		// pega o nó de Y e adiciona em X
    		int v = nosY.get(i) ;
    		nosX.add(v);
    		
    		// crio uma nova origem
    		ArrayList<Integer> novoNosY = new ArrayList<Integer>();
    		
    		// para cada item em Y que estiver conectado, grava o vizinho no novo Y
    		for(int w : nosY) 
    			if(A[v][w] == 1) 
    				novoNosY.add(w);
    		
    		// se não tem itens e a quantidade de nós em X for mais que o tamanho maximo, encontrou uma solucao
    		if(novoNosY.isEmpty() && nosX.size() > tamanhoMaximo) {
    			gravaSolution(nosX);
    			solucao = convertIntegers(nosX);
    		}
    		
    		// Se o novo Y não está vazio, continua expandindo
    		if(!novoNosY.isEmpty()) 
    			expand(nosX, novoNosY);
    		
    		// remove nó atual
    		nosX.remove((Integer)v);
    		nosY.remove((Integer)v);
    	}
    }

    void expandir (ArrayList<Integer> nosX, ArrayList<Integer> nosY) {    	// C P
    	
    	nDecisoes++;
    	// itera os nós
    	for(int i = nosY.size() - 1; i >=0; i--){
    		
    		// se a quantidade de nós em x mais os de y foram menor que o maximo..
    		if(nosY.size() + nosY.size() <= tamanhoMaximo ) 
    			return ;
    		
    		// pega o nó de Y e adiciona em X
    		int v = nosY.get(i) ;
    		nosX.add(v);
    		
    		// crio uma nova origem
    		ArrayList<Integer> novoNosY = new ArrayList<Integer>();
    		
    		// para cada item em Y que estiver conectado, grava o vizinho no novo Y
    		for(int w : nosY) 
    			if(A[v][w] == 1 ) 
    				novoNosY.add(w);
    		
    		// se não tem itens e a quantidade de nós em X for mais que o tamanho maximo, encontrou uma solucao
    		if(novoNosY.isEmpty() && nosX.size() > tamanhoMaximo) {
    			gravaSolution(nosX);
    			solucao = convertIntegers(nosX);
    		}
    		
    		// Se o novo Y não está vazio, continua expandindo
    		if(!novoNosY.isEmpty()) 
    			expandir(nosX, novoNosY);
    		
    		// remove nó atual
    		nosX.remove((Integer)v);
    		nosY.remove((Integer)v);
    	}
    }
    
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
	public String getSolucao() {
		String saida =  "";
		for (int i = 0; i < solucao.length; i++) {			
			saida += " " + solucao[i];
		}
		
		return saida;
	}

    void gravaSolution(ArrayList<Integer> C) {
    	Arrays.fill(solucao01, 0);
    	for(int i : C)
    		solucao01[i] = 1;
    	tamanhoMaximo = C.size();
    }
}
