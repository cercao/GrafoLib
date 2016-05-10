package desafios.kruskal;

import java.util.List;

public class Aresta implements Comparable {
	private float weight;
	private int u, v;

	public Aresta(int u, int v) {
		this.u = u;
		this.v = v;
	}

	public Aresta(int u, int v, float d) {
		this(u, v);
		this.weight = d;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(float w) {
		this.weight = w;
	}

	public int getU() {
		return this.u;
	}

	public int getV() {
		return this.v;
	}

	// Comparador expecificado
	public int compareTo(Object a) {
		Aresta aresta = (Aresta) a;

		if (this.getWeight() < aresta.getWeight())
			return -1;
		else if (this.getWeight() > aresta.getWeight())
			return 1;
		else
			return 0;
	}

	public static float sum(List<Aresta> arestas) {
		float sum = 0;

		for (Aresta e : arestas) {
			sum += e.getWeight();
		}

		return sum;
	}
}