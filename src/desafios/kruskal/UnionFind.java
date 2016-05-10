package desafios.kruskal;

import java.util.ArrayList;
import java.util.List;

public class UnionFind {
	private int nodeCount = 0;
	private int setCount = 0;

	ArrayList<Node> rootNodes;

	/*
	 * Retorna o indice que o n está.
	 * O indice da raiz de cada componente o identifica unicamente.
	 * Isso é usado para determinar quando 2 elementos são da mesma componente
	 */
	public int find(Node n) {
		Node current = n;

		// Leva o ponteiro para a raiz
		while (current.pai != null)
			current = current.pai;

		Node root = current;

		/*
		 * Leva o ponteiro para a raiz novamente, mas adiciona cada nó abaixo
		 * de uma filha da raiz. Faz isso para poder encontrar a raiz mais rapidamente
		 */
		current = n;
		while (current != root) {
			Node temp = current.pai;
			current.pai = root;
			current = temp;
		}

		return root.i;
	}

	/*
	 * COmbina componentes que contém i e j
	 */
	public void union(Node i, Node j) {
		int indexI = find(i);
		int indexJ = find(j);

		// Já são da mesma componente
		if (indexI == indexJ)
			return;

		// Pega a raiz de cada componente
		Node a = this.rootNodes.get(indexI);
		Node b = this.rootNodes.get(indexJ);

		// Adiciona a arvore menor na raiz da maior
		if (a.posicao < b.posicao) {
			a.pai = b;
		} else if (a.posicao > b.posicao) {
			b.pai = a;
		} else {
			b.pai = a;
			a.posicao++;
		}

		this.setCount--;
	}

	/*
	 * Usa uma lista de vertice para criar um componente UnionFind
	 */
	public void makeSets(List<Vertice> vertices) {
		for (Vertice v : vertices)
			makeSet(v);
	}

	/*
	 * Cria componente com um vértice
	 */
	public void makeSet(Vertice vertex) {
		Node n = new Node(0, rootNodes.size(), null);
		vertex.setNode(n);
		this.rootNodes.add(n);
		this.setCount++;
		this.nodeCount++;
	}

	public UnionFind(List<Vertice> vertices) {
		this.rootNodes = new ArrayList<Node>(vertices.size());
		makeSets(vertices);
	}
}