package desafios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliqueMaxima {	
	int [][] A; // matriz adjacente 0-1
	int n; // numero de vertices
	long count; // numero de decisoes
    int tamanhoMaximo ; // tamanho da clique maxima
    int [] solucao; // solução encontrada
    int [] solucao01; // solução encontrada 0-1    

    CliqueMaxima (int tamanho, int [][] matriz){
    	this.n = tamanho;
    	this.A = matriz;    	
    	count = tamanhoMaximo = 0;    	   
    	solucao01 = new int[n];    	
    	
    }

    void buscar(){    	
    	count = 0;
    	ArrayList<Integer> listaSolucaoTemp = new ArrayList<Integer>();
    	ArrayList<Integer> listaAtual = new ArrayList<Integer>(n);    	
    	
    	// Adiciona todos os nós na lista atual
    	for(int i = 0; i < n ; i++) 
    		listaAtual.add(i);
    		
    	// Chama recursiva passando matriz vazia e a carregada
    	expand(listaSolucaoTemp, listaAtual);
    }
    

    void expand (ArrayList<Integer> listaSolucaoTemp , ArrayList<Integer> listaAtual) {    	
    	count++;
    	// itera os nós da lista atual
    	for(int i = listaAtual.size() - 1; i >= 0; i--){
    		
    		// quando a somatória for menor ou igual, é por que já removeu itens o suficiente, não é possível continuar
    		if(listaSolucaoTemp.size() + listaAtual.size() <= tamanhoMaximo ) 
    			return ;
    		
    		// pega o nó da lista atual e adiciona na SolucaoTemp
    		int v = listaAtual.get(i) ;
    		listaSolucaoTemp.add(v);
    		
    		// Lista de nós conectados
    		ArrayList<Integer> novaListaAtual = new ArrayList<Integer>();
    		
    		// para cada item na lista atual que estiver conectado, grava o vizinho na novaListaAtual
    		for(int w : listaAtual) 
    			if(A[v][w] == 1) 
    				novaListaAtual.add(w);
    		
    		// se não tem itens (não encontrou conexao)
    		// e a quantidade de nós na SolucaoTemp for maior que o tamanho maximo, encontrou uma nova solucao
    		if(novaListaAtual.isEmpty() && listaSolucaoTemp.size() > tamanhoMaximo) {
    			gravaSolution(listaSolucaoTemp);
    			solucao = convertIntegers(listaSolucaoTemp);
    		}
    		
    		// Se a nova lista atual não está vazia, continua expandindo
    		if(!novaListaAtual.isEmpty()) 
    			expand(listaSolucaoTemp, novaListaAtual);
    		
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
