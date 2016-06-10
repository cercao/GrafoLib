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
    	ArrayList<Integer> listaSolucaoTemp = new ArrayList<Integer>();
    	ArrayList<Integer> listaAtual = new ArrayList<Integer>(n);    	
    	
    	// Adiciona todos os nós em Y
    	for(int i = 0; i < n ; i++) 
    		listaAtual.add(i);
    		
    	// Chama recursiva passando matriz valia e a carregada
    	expand(listaSolucaoTemp, listaAtual);
    }
    

    void expand (ArrayList<Integer> listaSolucaoTemp , ArrayList<Integer> listaAtual) {
    	
    	nDecisoes++;
    	// itera os nós de Y, que no início é a carregada (itera de baixo pra cima)
    	for(int i = listaAtual.size() - 1; i >= 0; i--){
    		
    		// A somatória, deve ser sempre o tamanho total do grafo, quando for menor ou igual, é por que já removeu itens o suficiente
    		if(listaSolucaoTemp.size() + listaAtual.size() <= tamanhoMaximo ) 
    			return ;
    		
    		// pega o nó de Y e adiciona em X
    		int v = listaAtual.get(i) ;
    		listaSolucaoTemp.add(v);
    		
    		// Lista de nós conectados
    		ArrayList<Integer> novoNosY = new ArrayList<Integer>();
    		
    		// para cada item em Y que estiver conectado, grava o vizinho no novo Y
    		for(int w : listaAtual) 
    			if(A[v][w] == 1) 
    				novoNosY.add(w);
    		
    		// se não tem itens e a quantidade de nós em X for mais que o tamanho maximo, encontrou uma solucao
    		if(novoNosY.isEmpty() && listaSolucaoTemp.size() > tamanhoMaximo) {
    			gravaSolution(listaSolucaoTemp);
    			solucao = convertIntegers(listaSolucaoTemp);
    		}
    		
    		// Se o novo Y não está vazio, continua expandindo
    		if(!novoNosY.isEmpty()) 
    			expand(listaSolucaoTemp, novoNosY);
    		
    		// remove nó atual
    		listaSolucaoTemp.remove((Integer)v);
    		listaAtual.remove((Integer)v);
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
