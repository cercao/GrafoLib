package desafios.kruskal;

public class Node {
	int posicao; // nodes embaixo deste
	int i; // indice 
	Node pai;

	public Node(int r, int i, Node p) {
		this.posicao = r;
		this.i = i;
		this.pai = p;
	}
}

