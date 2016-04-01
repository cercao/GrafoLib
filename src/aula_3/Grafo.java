/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_3;
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author meira
 */
public class Grafo {
     
    private boolean[][] adj;
    private boolean ehConexo;
    private int n;
     
    public Grafo() {
         
    }
     
    private void iniciaAdj() {
        adj = new boolean[n][n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                adj[u][v] = false;
            }
        }
    }
     
    public void addAresta(int u, int v) {
        adj[u][v] = true;
        adj[v][u] = true;
    }
     
    public void removeArestas(int u, int v) {
        adj[u][v] = true;
        adj[v][u] = true;
    }
     
    public int grau(int v) {
        int grau = 0;
        for (int i = 0; i < n; i++) {
            if (adj[v][i]) {
                grau++;
            }
        }
        return grau;
    }
     
    public int[] vizinhos(int v) {
        int[] vet = new int[grau(v)];
        int top = 0;
        for (int i = 0; i < n; i++) {
            if (adj[v][i]) {
                vet[top++] = i;
            }
        }
        return vet;
    }
     
    public void carrega(File f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linha;
             
            linha = br.readLine();
            this.n = Integer.parseInt(linha);
            this.iniciaAdj();
             
            linha = br.readLine();
            while (linha != null && linha.length() > 2) {
                System.out.println(linha);
                String[] list = linha.split(" ");
                int u = Integer.parseInt(list[0]);
                int v = Integer.parseInt(list[1]);
                this.addAresta(u, v);
                linha = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void print() {
        String aux = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aux += " ";
                if (adj[i][j]) {
                    aux += 1;
                } else {
                    aux += 0;
                }
            }
            aux += "\n";
        }
        System.out.println(aux);
        aux = "";
        for (int i = 0; i < n; i++) {
            aux += "viz(" + i + ")={";
            int[] viz = this.vizinhos(i);
            for (int j = 0; j < viz.length; j++) {
                aux += viz[j];
                if (j < viz.length - 1) {
                    aux += ",";
                }
            }
            aux += "}\n";
        }
        System.out.println(aux);
    }
     
    int [] menorCaminho(int u,int v){
        //encontrar o menor caminho entre u e v
        return null;
    }
     
    
//    escolha uma raiz s de G
//    marque s
//    insira s em F
//    enquanto F não está vazia faça
//       seja v o primeiro vértice de F
//       para cada w ∈ listaDeAdjacência de v faça
//          se w não está marcado então
//             visite aresta entre v e w
//             marque w
//             insira w em F
//          senao se w ∈ F entao
//             visite aresta entre v e w
//          fim se
//       fim para
//       retira v de F
//    fim enquanto
    int [] buscaLargura(int u){
        //fazer a busca em largura a salvar em um vetor
    	
    	ArrayList<Integer> marcados = new ArrayList<Integer>();
    	
    	// Vi que este algoritmo usa a estrutura de fila
		Queue q = new LinkedList();

		// Marca e adiciona na fila o primeiro item
		q.add(u);
		marcados.add(u);
		System.out.println(u);    
		
		// Enquanto estiver conteudo
		while(!q.isEmpty())
		{
			// v é o primeiro
			Integer v = (Integer)q.remove();			
			boolean existeFilhosNaoVisitados = true;
			while(existeFilhosNaoVisitados){
				int[] vizinhos = vizinhos(v);
				for(int w : vizinhos){
					// se não foi marcado..
					if (!marcados.contains(w)){
						// Marca e adiciona na fila
						System.out.println(w);  
						q.add(w);
						marcados.add(w);
					}
				}
				
				// Verifica se ainda tem vizinhos não visitados
				for(int w : vizinhos){
					if (!marcados.contains(w)){
						existeFilhosNaoVisitados = true;
						break;
					}else{
						existeFilhosNaoVisitados = false;
					}
				}				
			}						
		}    
		
		// Converte para o formato solicitado		
        return convertIntegers(marcados);
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
    
    int [] buscaProfundidade(int u){
        //fazer a busca em profundidade e salvar num vetor
        
    	// aqui precisa usar pilha
    	Stack<Integer> stack = new Stack<Integer>();    	    	    	
        int[] visitados = new int[this.n + 1];
        int i, element;
        visitados[u] = 1;
        stack.push(u);
        while (!stack.isEmpty())
        {
            element = stack.pop();
            i = 0;// elemento;
            while (i < this.n)
            {
                if (adj[element][i] == true && visitados[i] == 0)
                {
                    stack.push(i);
                    visitados[i] = 1;
                }
                i++;
            }
        }
 
        System.out.println("O nó origem " + u + " está conectado com: ");
        int count = 0;
        for (int v = 1; v <= this.n; v++)
            if (visitados[v] == 1)
            {
                System.out.print(v + " ");
                count++;
            }
        
        if (count == this.n)
            this.ehConexo = true;
        else
            this.ehConexo = false;
        
        return visitados;                    
    }
     
    boolean conexo(){
        //verdadeiro se o grafo é conexo
    	
    	this.buscaProfundidade(0);
    	
    	// executa a busca por profundidade que irá marcar o rhConexo
        return ehConexo;
    }
     
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.carrega(new File("C:/Users/Lucas Rodrigues/workspace/GraphLib/src/aula_3/g1.txt"));
        //g.print();
        //g.buscaLargura(0);
        if(g.conexo())
        	System.out.println("Este grafo é conexo.");
        else
        	System.out.println("Este grafo não é conexo.");
    }
     
}