import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 * @author lucas
 */
public class CicloHamiltoniano {
	DecimalFormat df = new DecimalFormat("#.00"); 
	double totalCont = 0;	
	double valorAnterior = 0;
	private int caminhoCount;
	private int[] caminho;
	private int[][] matrizGrafo;

	public void buscarCicloHamiltoniano(int[][] grafo) {	
		// inicia timer para logar
		Timer timer = new Timer ();
		
		System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont ));
		TimerTask hourlyTask = new TimerTask () {
		    @Override
		    public void run () {
		    	System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont )
		    	+
		    	"; Velocidade: " + String.format( "%.2f", totalCont - valorAnterior) + "it/h" );
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

		// para cada vértice
		for (int v = 0; v < matrizGrafo.length; v++) {
						
			// Contabiliza
			totalCont++;
			
			//if (totalCont % Integer.MAX_VALUE == 0){
			//	System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont ));
			//}
			
			// Verifica se o vertice atual está conectado 
			if (matrizGrafo[vertice][v] == 1) {
				
				// se sim, adiciono no caminho e incremento o contador
				caminho[caminhoCount++] = v;
				
				// Remove a conexao
				matrizGrafo[vertice][v] = 0;
				matrizGrafo[v][vertice] = 0;

				// se o vértice não foi visitado, busca novamente
				if (!foiSelecionado(v))
					buscar(v);

				// restaura conexao se já foi visitado
				matrizGrafo[vertice][v] = 1;
				matrizGrafo[v][vertice] = 1;
				
				// remove do caminho e remove 1 do contador
				caminho[--caminhoCount] = -1;
			}
		}
	}

	public boolean foiSelecionado(int v) {
		// Verifica se já foi selecionado
		for (int i = 0; i < caminhoCount - 1; i++)
			if (caminho[i] == v)
				return true;
		return false;
	}

	public void exibirSolucao() {
		System.out.print("\n");
		for (int i = 0; i <= caminho.length; i++)
			System.out.print(caminho[i] + " ");	
		System.out.println();
	}
}