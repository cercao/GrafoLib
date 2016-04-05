package desafios;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 * @author lucas
 * Os métodos públicos são importantes para o entendimento do negócio. Os demais são apenas auxiliares.
 */
public class CicloHamiltonianoCavalo {
	// inicia timer para logar
	Timer timer = new Timer ();
	
	DecimalFormat df = new DecimalFormat("#.00"); 
	double totalCont = 0;	
	double valorAnterior = 0;
	private int caminhoCount;
	private int[] caminho;
	private int[][] matrizGrafo;
	int[] verticesVizinhos;

	public void buscarCicloHamiltoniano(int[][] grafo) {	
		
		System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont ));
		TimerTask hourlyTask = new TimerTask () {
		    @Override
		    public void run () {
		    	System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont )
		    	+
		    	"; Velocidade: " + String.format( "%.2f", totalCont - valorAnterior) + "it/h; "
		    	+ "Caminho: " + convertCaminhoToString(caminho));
		    	
		    	if (verticesVizinhos != null)
		    		System.out.println(verticesVizinhos.length);
		    	
		    	valorAnterior = totalCont;
		    }
		};

		// agenda timer para executar a cada uma hora
		timer.schedule (hourlyTask, 0l, 1000*60*60);
		
		matrizGrafo = grafo;
		
		 // inicializa tamanho  
		caminho = new int[matrizGrafo.length];

		// Preenche o caminho com -1
		Arrays.fill(caminho, -1);
		
		try {
			// Inicializa o caminho com 0: é rótulo para o primeiro vértice do caminho
			caminho[0] = 0;
			
			// Inicializa o contator
			caminhoCount = 1;
			
			// Inicia a funcao recursiva de busca passando como origem o vertice 0
			buscar(0);
			System.out.println("Não existe caminho hamiltoniano no grafo avaliado. Total Iteracoes: " + totalCont);
		} catch (Exception e) {
			
			// para o timer
			timer.cancel();
			
			// se passou uma exceção, mostra o caminho:			
			System.out.print("\nCiclo Hamiltoniano encontrado: ");
			for (int i = 0; i <= caminho.length; i++)
				System.out.print(caminho[i] + " ");		
			
			System.out.println(" Total Iteracoes: " + String.format( "%.2f", totalCont ));
		}
	}

	public void buscar(int vertice) throws Exception {
		
		// verifica se o vértice atual está conectado com o primeiro e se é o último grafo
		if (matrizGrafo[vertice][0] == 1 && caminhoCount == matrizGrafo.length)
			throw new Exception("Caminho: ");
		
		// se é o último e não encontrou, para de buscar e mostra a mensagem
		if (caminhoCount == matrizGrafo.length)
			return;

		// No algoritmo do caminho do cavalo é necessário avaliar apenas os vizinho (que contém o vértice conectado) ao invés
		// de iterar todos os vértices		
		
		// Encontra vizinhos
		int[] verticesVizinhos = this.getVizinhos(vertice);
		
		// Itera os vértices vizinhos 		
		for (int v = 0; v < verticesVizinhos.length; v++) {
						
			// Contabiliza
			totalCont++;
			if(getTamanhoCaminho(caminho) > matrizGrafo.length - 3){
		    	System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont )
		    	+
		    	"; QUASE: Velocidade: " + String.format( "%.2f", totalCont - valorAnterior) + "it/h; "
		    	+ "Caminho: " + convertCaminhoToString(caminho));
		    	
		    	if (verticesVizinhos != null)
		    		System.out.println(verticesVizinhos.length);
			}				
			
			// não precisa verificar se já é conectado por que estamos iterando apenas vizinhos
				
			// adiciono no caminho e incremento o contador
			caminho[caminhoCount++] = verticesVizinhos[v];
			
			// Remove a conexao
			matrizGrafo[vertice][verticesVizinhos[v]] = 0;
			matrizGrafo[verticesVizinhos[v]][vertice] = 0;

			// se o vértice não foi visitado, busca novamente
			if (!foiSelecionado(verticesVizinhos[v]))
				buscar(verticesVizinhos[v]);

			// restaura conexao se já foi visitado
			matrizGrafo[vertice][verticesVizinhos[v]] = 1;
			matrizGrafo[verticesVizinhos[v]][vertice] = 1;
			
			// remove do caminho e remove 1 do contador
			caminho[--caminhoCount] = -1;
			
		}
	}
    
	public int getGrau(int v) {
        int grau = 0;
        for (int i = 0; i < this.matrizGrafo.length; i++) {
            if (this.matrizGrafo[v][i] == 1) {
                grau++;
            }
        }
        return grau;
    }
	
	public int[] getVizinhos(int vertice) {
		int[] vet = new int[this.getGrau(vertice)];
		int cont = 0;
		
		for (int v = 0; v < matrizGrafo.length; v++) {
			
			// Verifica se o vertice atual está conectado 
			if (matrizGrafo[vertice][v] == 1) {				
				vet[cont] = v;	
				cont++;
			}
		}
		
		return vet;
	}
	
	public String convertCaminhoToString(int[] path) {
		String saida =  "";
		for (int i = 0; i < path.length; i++) {			
			saida += " " + path[i];
		}
		
		return saida;
	}
	
	public int getTamanhoCaminho(int[] path) {
		int cont = 0;
		for (int i = 0; i < path.length; i++) {		
			if (path[i] != -1)
				cont++;
		}
		
		return cont;
	}

	private boolean foiSelecionado(int v) {
		// Verifica se já foi selecionado
		for (int i = 0; i < caminhoCount - 1; i++)
			if (caminho[i] == v)
				return true;
		return false;
	}
}