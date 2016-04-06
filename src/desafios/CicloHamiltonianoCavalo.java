package desafios;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
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
	private Object[] caminho1;
	private int[][] matrizGrafo;
	int[] verticesVizinhos;
	Hashtable vertices = new Hashtable<>();

	
	public void buscarCicloHamiltoniano(int[][] grafo) {	
		
		System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont ));
		TimerTask hourlyTask = new TimerTask () {
		    @Override
		    public void run () {
		    	System.out.println((new Date()).toString() + ": " +  String.format( "%.2f", totalCont )
		    	+
		    	"; Velocidade: " + String.format( "%.2f", totalCont - valorAnterior) + "it/h; "
		    	+ "Caminho: " + convertCaminhoToString1(caminho1));
		    	
		    	if (verticesVizinhos != null)
		    		System.out.println(verticesVizinhos.length);
		    	
		    	valorAnterior = totalCont;
		    }
		};

		// agenda timer para executar a cada 10 minutos
		timer.schedule (hourlyTask, 0l, 1000*60*10);
		
		try {
			// Converte para estrutura de hash
			for (int i=0; i < grafo.length; i++)			
				addVertex(i);
			
			// conecta todos
			for (int i=0; i < grafo.length; i++)
				for (int j=0; j < grafo.length; j++)
					if(grafo[i][j] == 1 && !vertices.contains(i))
						connect(i, j);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		 // inicializa caminho  
		caminho1 = new Object[vertices.size()];

		// Preenche o caminho com -1
		Arrays.fill(caminho1, -1);
		
		try {
			// Inicializa o caminho com 0: é rótulo para o primeiro vértice do caminho
			caminho1[0] = 0;
			
			// Inicializa o contator
			caminhoCount = 1;
			
			// Inicia a funcao recursiva de busca passando como origem o vertice 0
			buscar(0);
			System.out.println("Não existe caminho hamiltoniano no grafo avaliado. Total Iteracoes: " + totalCont);
		} catch (Exception e) {
			
			// para o timer
			timer.cancel();
			
			// se passou uma exceção, mostra o caminho:			
			System.out.print("\nCiclo do Cavalo encontrado: ");
			for (int i = 0; i < caminho1.length; i++)
				System.out.print(caminho1[i] + " ");		
			
			System.out.println("");
			System.out.println(" Total Iteracoes: " + String.format( "%.2f", totalCont ));
		}
	}

	public void buscar(Object vertice) throws Exception {
		
		boolean temConexao = isConnected(vertice, 0);
		
		// verifica se o vértice atual está conectado com o primeiro e se é o último grafo
		if (caminhoCount == vertices.size())
			throw new Exception("Caminho: ");
		
		// se é o último e não encontrou, para de buscar e mostra a mensagem
		if (caminhoCount == vertices.size())
			return;			

		// No algoritmo do caminho do cavalo é necessário avaliar apenas os vizinho (que contém o vértice conectado) ao invés
		// de iterar todos os vértices		
		
		// Encontra vizinhos
		Object[] verticesVizinhos = this.getNeighboorsArray(vertice);
		
//		// Se é o último da lista e só tem uma conexão, é um vértice proibido
//		if(temConexao && verticesVizinhos.length == 1)
//			verticesProibidos.add(vertice);
		
		// Itera os vértices vizinhos 		
		for (int v = 0; v < verticesVizinhos.length; v++) {
			

			
			// Contabiliza
			totalCont++;				
			
			// não precisa verificar se já é conectado por que estamos iterando apenas vizinhos
				
			// adiciono no caminho e incremento o contador
			caminho1[caminhoCount++] = verticesVizinhos[v];
			
			// Remove a conexao
			disconnect(vertice, verticesVizinhos[v]);

			// se o vértice não foi visitado, busca novamente
			if (!foiSelecionado1(verticesVizinhos[v]))
				buscar(verticesVizinhos[v]);

			// restaura conexao se já foi visitado
			connect(vertice, verticesVizinhos[v]);
			
			// remove do caminho e remove 1 do contador
			caminho1[--caminhoCount] = -1;
			
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
		if (path == null)
			return "";
		for (int i = 0; i < path.length; i++) {			
			saida += " " + path[i];
		}
		
		return saida;
	}
	
	public String convertCaminhoToString1(Object[] path) {
		String saida =  "";
		if (path == null)
			return "";
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
	
	private boolean foiSelecionado1(Object v) {
		// Verifica se já foi selecionado
		for (int i = 0; i < caminhoCount - 1; i++)
			if (caminho1[i] == v)
				return true;
		return false;
	}

	private boolean foiSelecionado(int v) {
		// Verifica se já foi selecionado
		for (int i = 0; i < caminhoCount - 1; i++)
			if (caminho[i] == v)
				return true;
		return false;
	}
	
	public void addVertex(Object v) throws Exception {
        //if (vertices.containsKey(v))
        //    throw new Exception("Este vértice já existe");
        vertices.put(v,new Hashtable());
    }
	
	public void connect(Object v1, Object v2) throws Exception {
        //if (!vertices.containsKey(v1))
//            throw new Exception("Elemento não encontrado");
//        if (!vertices.containsKey(v2))
//            throw new Exception("Elemento não encontrado");
        if (v1==v2)
            return;
        ((Hashtable)vertices.get(v1)).put(v2,v2);
        ((Hashtable)vertices.get(v2)).put(v1,v1);
    }
	
	public Object[] getNeighboorsArray(Object v) throws Exception {
        //if (!vertices.containsKey(v))
        //	throw new Exception("Elemento não encontrado");
        Object[] neighboors = new Object[degree(v)];
        int i=0;
        for (Enumeration e=getNeighboors(v); e.hasMoreElements(); i++)
            neighboors[i]=e.nextElement();
        return neighboors;
    }
	
	 public void disconnect(Object v1, Object v2) throws Exception {
	        //if (!vertices.containsKey(v1))
	        //	throw new Exception("Elemento não encontrado");
	        //if (!vertices.containsKey(v2))
	        //	throw new Exception("Elemento não encontrado");
	        if (v1==v2)
	            return;
	        
	        ((Hashtable)vertices.get(v1)).remove(v2);
	        ((Hashtable)vertices.get(v2)).remove(v1);
	    }
	
	public int degree(Object v) throws Exception {
        //if (!vertices.containsKey(v))
        //	throw new Exception("Elemento não encontrado");
        return ((Hashtable)vertices.get(v)).size();
    }
	
	public int size() {
        return vertices.size();
    }
	public Enumeration getVertices() {
        return vertices.keys();
    }
	
	public Enumeration getNeighboors(Object v) throws Exception {
        //if (!vertices.containsKey(v))
        //	throw new Exception("Elemento não encontrado");
        return ((Hashtable)vertices.get(v)).elements();
    }

	public boolean isConnected(Object v1, Object v2) throws Exception {
//        if (!vertices.containsKey(v1))
//        	throw new Exception("Elemento não encontrado");
//        if (!vertices.containsKey(v2))
//        	throw new Exception("Elemento não encontrado");
        
        // vizinhos de v1
        Hashtable neighboors = (Hashtable) vertices.get(v1); 
        return (neighboors.containsKey(v2));
    }
	
}