package desafios;

import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")

public class KruskalMST {
	static ArrayList<Vertex> vertices;
	static int[][][] figura;
	static List<String> combinacoesPossivel; 
	static int n;
	static int ne;
	static int xmax = Integer.MIN_VALUE;
	static int ymax = Integer.MIN_VALUE;
	static int xmin = Integer.MAX_VALUE;
	static int ymin = Integer.MAX_VALUE;
	static final File dir = new File("");

	public static void setPreto(int i, int j) {
		figura[i][j][0] = 0;
		figura[i][j][1] = 0;
		figura[i][j][2] = 0;
	}

	public static void desenhaLinha(int x1, int y1, int x2, int y2) {
		// delta of exact value and rounded value of the dependant variable
		int d = 0;

		int dy = Math.abs(y2 - y1);
		int dx = Math.abs(x2 - x1);

		int dy2 = (dy << 1); // slope scaling factors to avoid floating
		int dx2 = (dx << 1); // point

		int ix = x1 < x2 ? 1 : -1; // increment direction
		int iy = y1 < y2 ? 1 : -1;
		// (x - xmin, y - ymin)
		if (dy <= dx) {
			for (;;) {
				setPreto(x1 - xmin, y1 - ymin);
				if (x1 == x2)
					break;
				x1 += ix;
				d += dy2;
				if (d > dx) {
					y1 += iy;
					d -= dx2;
				}
			}
		} else {
			for (;;) {
				setPreto(x1 - xmin, y1 - ymin);
				if (y1 == y2)
					break;
				y1 += iy;
				d += dx2;
				if (d > dy) {
					x1 += ix;
					d -= dy2;
				}
			}
		}
	}

	public static void setBranco(int i, int j) {
		figura[i][j][0] = 255;
		figura[i][j][1] = 255;
		figura[i][j][2] = 255;
	}

	public static void main(String[] args) {

		// Inicializa combinações possíveis de estados do brasil
		combinacoesPossivel = new ArrayList<String>();
		combinacoesPossivel.add("ACAM");
		combinacoesPossivel.add("ACAC");
		combinacoesPossivel.add("AMAM");
		combinacoesPossivel.add("AMAC");
		combinacoesPossivel.add("AMRO");
		combinacoesPossivel.add("AMMT");
		combinacoesPossivel.add("AMPA");
		combinacoesPossivel.add("AMRR");
		combinacoesPossivel.add("RRAM");
		combinacoesPossivel.add("RRRR");
		combinacoesPossivel.add("RRPA");
		combinacoesPossivel.add("ROAM");
		combinacoesPossivel.add("RORO");
		combinacoesPossivel.add("ROMT");
		combinacoesPossivel.add("PAAM");
		combinacoesPossivel.add("PAPA");
		combinacoesPossivel.add("PAAP");
		combinacoesPossivel.add("PAMT");
		combinacoesPossivel.add("PATO");
		combinacoesPossivel.add("PAMA");
		combinacoesPossivel.add("PARR");
		combinacoesPossivel.add("APPA");
		combinacoesPossivel.add("APAP");
		combinacoesPossivel.add("APPA");
		combinacoesPossivel.add("MTMT");
		combinacoesPossivel.add("MTMS");
		combinacoesPossivel.add("MTRO");
		combinacoesPossivel.add("MTAM");
		combinacoesPossivel.add("MTPA");
		combinacoesPossivel.add("MTTO");
		combinacoesPossivel.add("MTGO");
		combinacoesPossivel.add("TOTO");
		combinacoesPossivel.add("TOMA");
		combinacoesPossivel.add("TOGO");
		combinacoesPossivel.add("TOMT");
		combinacoesPossivel.add("TOPA");
		combinacoesPossivel.add("TOPI");
		combinacoesPossivel.add("TOBA");
		combinacoesPossivel.add("MAMA");
		combinacoesPossivel.add("MAPI");
		combinacoesPossivel.add("MAPA");
		combinacoesPossivel.add("MATO");
		combinacoesPossivel.add("MABA");
		combinacoesPossivel.add("MAMA");
		combinacoesPossivel.add("PIPI");
		combinacoesPossivel.add("PIMA");
		combinacoesPossivel.add("PIBA");
		combinacoesPossivel.add("PICE");
		combinacoesPossivel.add("PIPE");
		combinacoesPossivel.add("PITO");
		combinacoesPossivel.add("PIPE");
		combinacoesPossivel.add("CECE");
		combinacoesPossivel.add("CERN");
		combinacoesPossivel.add("CEPB");
		combinacoesPossivel.add("CEPE");
		combinacoesPossivel.add("CEPI");
		combinacoesPossivel.add("RNRN");
		combinacoesPossivel.add("RNCE");
		combinacoesPossivel.add("RNPB");
		combinacoesPossivel.add("PBPB");
		combinacoesPossivel.add("PBRN");
		combinacoesPossivel.add("PBPE");
		combinacoesPossivel.add("PBCE");
		combinacoesPossivel.add("PEPE");
		combinacoesPossivel.add("PEPB");
		combinacoesPossivel.add("PEAL");
		combinacoesPossivel.add("PEPI");
		combinacoesPossivel.add("PECE");
		combinacoesPossivel.add("ALAL");
		combinacoesPossivel.add("ALPE");
		combinacoesPossivel.add("ALBA");
		combinacoesPossivel.add("ALSE");
		combinacoesPossivel.add("SESE");
		combinacoesPossivel.add("SEBA");
		combinacoesPossivel.add("BABA");
		combinacoesPossivel.add("BAPI");
		combinacoesPossivel.add("BAPE");
		combinacoesPossivel.add("BASE");
		combinacoesPossivel.add("BAAL");
		combinacoesPossivel.add("BATO");
		combinacoesPossivel.add("BAGO");
		combinacoesPossivel.add("BAMG");
		combinacoesPossivel.add("BAES");
		combinacoesPossivel.add("ESES");
		combinacoesPossivel.add("ESMG");
		combinacoesPossivel.add("ESBA");
		combinacoesPossivel.add("ESRJ");
		combinacoesPossivel.add("MGMG");
		combinacoesPossivel.add("MGES");
		combinacoesPossivel.add("MGRJ");
		combinacoesPossivel.add("MGGO");
		combinacoesPossivel.add("MGSP");
		combinacoesPossivel.add("MGBA");
		combinacoesPossivel.add("MGMS");
		combinacoesPossivel.add("RJRJ");
		combinacoesPossivel.add("RJES");
		combinacoesPossivel.add("RJMG");
		combinacoesPossivel.add("RJSP");
		combinacoesPossivel.add("GOGO");
		combinacoesPossivel.add("GODF");
		combinacoesPossivel.add("DFGO");
		combinacoesPossivel.add("GOMT");
		combinacoesPossivel.add("GOBA");
		combinacoesPossivel.add("GOMG");
		combinacoesPossivel.add("GOMS");
		combinacoesPossivel.add("GOTO");
		combinacoesPossivel.add("MSMS");
		combinacoesPossivel.add("MSMT");
		combinacoesPossivel.add("MSSP");
		combinacoesPossivel.add("MSMG");
		combinacoesPossivel.add("MSGO");
		combinacoesPossivel.add("MSPR");
		combinacoesPossivel.add("SPSP");
		combinacoesPossivel.add("SPMS");
		combinacoesPossivel.add("SPMG");
		combinacoesPossivel.add("SPRJ");
		combinacoesPossivel.add("SPPR");
		combinacoesPossivel.add("PRPR");
		combinacoesPossivel.add("PRSP");
		combinacoesPossivel.add("PRMS");
		combinacoesPossivel.add("PRSC");
		combinacoesPossivel.add("SCSC");
		combinacoesPossivel.add("SCPR");
		combinacoesPossivel.add("SCRS");
		combinacoesPossivel.add("RSRS");
		combinacoesPossivel.add("RSSC");
		
		// desenha os pontos, recupera total no n
		carregaPontos();

		// calcula o numero de arestas (grafo completo)
		ne = (n * (n - 1)) / 2;

		/* Run the tests for size n */
		float test2 = encontraMST();

		System.out.printf("Tamanho: %d:\n", n);
		System.out.printf("Custo: %f\n", test2);

	}

	public static void imprimeArvore(List<Edge> tree) {
		try {
			xmax = Integer.MIN_VALUE;
			ymax = Integer.MIN_VALUE;
			xmin = Integer.MAX_VALUE;
			ymin = Integer.MAX_VALUE;

			// primeira vez encontra os limites xmax, xmin, ymax, ymin
			// segunda vez imprime o arquivo ppm

			int total = 0;
			for (int vez = 0; vez < 2; vez++) {
				int dx = 0;
				int dy = 0;
				BufferedWriter out = null;
				if (vez == 1) {
					dx = xmax - xmin + 1;
					dy = ymax - ymin + 1;
					// cada posicao da matrix eh uma coordenada x,y com tres
					// dimensoes [x][y][0] [x][y][1] [x][y][2] para R-G-B
					figura = new int[dx][dy][3];
					out = new BufferedWriter(new FileWriter(new File("_aux.ppm")));
					// cabecalho da figura ppm
					// http://en.wikipedia.org/wiki/Netpbm_format#PPM_example
					out.write("P3\n");
					out.write(dy + " " + dx + "\n");
					out.write("255\n");

					// inicializa matriz quadriculada
					for (int i = 0; i < dx; i++) {
						for (int j = 0; j < dy; j++) {
							if (i % 100 == 9 || j % 100 == 9) {
								setPreto(i, j);
							} else {
								setBranco(i, j);
							}
						}
					}

				}

				// coordenadas.
				BufferedReader b = new BufferedReader(new FileReader(new File("coordenadas.txt")));

				// leitura
				String linha = b.readLine();

				while (linha != null && linha.length() > 0) {
					String[] lista = linha.split(";");
					String estado = lista[2];

					// ******** TESTE BAHIA - cancela se são for bahia
					//if (!estado.equals("BA")) {
					//	linha = b.readLine();
					//	continue;
					//}

					double a1 = Double.parseDouble(lista[3]);
					double a2 = Double.parseDouble(lista[4]);

					// converte em inteiro com um fator de escala 30.
					int x = (int) (a1 * 30);
					int y = (int) (a2 * 30);

					if (vez == 0) {
						// procura min e max
						if (x < xmin) xmin = x;
						if (x > xmax) xmax = x;
						if (y < ymin) ymin = y;
						if (y > ymax) ymax = y;

						//if (estado.equals("BA"))
						total++;

					}
					assert (ymin <= ymax);

					linha = b.readLine();
				}
				if (vez == 1) {

					// desenha a arvore
					for (int i = 0; i < tree.size(); i++) {
						Edge e = tree.get(i);

						// Monta a origem e destino de uma linha (fator de
						// escala 30)
						int lat1 = (int) (e.getU().getLat() * 30);
						int lon1 = (int) (e.getU().getLon() * 30);

						int lat2 = (int) (e.getV().getLat() * 30);
						int lon2 = (int) (e.getV().getLon() * 30);

						desenhaLinha(lat1, lon1, lat2, lon2);
					}

					// imprime o arquivo.
					for (int i = dx - 1; i >= 0; i--) {
						for (int j = 0; j < dy; j++) {
							out.write(
									figura[i][j][0] + " " + 
									figura[i][j][1] + " " + 
									figura[i][j][2] + " ");
						}
						out.write("\n");
					}

					out.write("\n");
					out.close();
				}
			}

			// vertices = new ArrayList<Vertex>(total);
		} catch (IOException ex) {
			Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static void carregaPontos() {
		try {
			int xmax = Integer.MIN_VALUE;
			int ymax = Integer.MIN_VALUE;
			int xmin = Integer.MAX_VALUE;
			int ymin = Integer.MAX_VALUE;

			// primeira vez encontra os limites xmax, xmin, ymax, ymin
			// segunda vez imprime o arquivo ppm

			int total = 0;
			for (int vez = 0; vez < 2; vez++) {

				// coordenadas
				BufferedReader b = new BufferedReader(new FileReader(new File("coordenadas.txt")));

				// leitura
				String linha = b.readLine();

				while (linha != null && linha.length() > 0) {
					String[] lista = linha.split(";");
					String estado = lista[2];

					// ******** TESTE BAHIA - cancela se são for bahia
					//if (!estado.equals("BA")) {
					//	linha = b.readLine();
					//	continue;
					//}

					double a1 = Double.parseDouble(lista[3]);
					double a2 = Double.parseDouble(lista[4]);

					if (vez == 0) {						
							total++;
					} else {
						if (vertices == null) {
							vertices = new ArrayList<Vertex>(total);
							n = total;
						}

						// marca quantidade de nós
						//if (estado.equals("BA")) {
						vertices.add(new Vertex(a1, a2, estado.intern()));
						//}
					}
					assert (ymin <= ymax);

					linha = b.readLine();
				}

			}

			// vertices = new ArrayList<Vertex>(total);
		} catch (IOException ex) {
			Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	/***
	 * Carrega as arestas, faz o sort, monta UnionFind e aplica kruskal
	 * @return peso
	 */
	public static float encontraMST() {

		/* Create a list of edges */
		List<Edge> edges = new ArrayList<Edge>();
				
		// qtd --- 100
		// atual-- x
		// x = 100*atual/qtd
		// carrega arestas
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				
				if (i == j)
					continue;
				
				Vertex a = vertices.get(i);
				Vertex b = vertices.get(j);	
				
				if (!combinacoesPossivel.contains(a.getEstado() + b.getEstado()))
					continue;
				
				Edge e = new Edge(a, b, Vertex.distancia(a, b));
				edges.add(e);
			}
		}

		/* Create the disjoint-set data structure */
		DisjointSet d = new DisjointSet(vertices);

		/* Create a list of edges */
		List<Edge> tree = new ArrayList<Edge>();

		/* Java's modified version of mergesort guarantees nlog(n) time here */
		Collections.sort(edges);

		/* Kruskal's algorithm */
		for (Edge e : edges) {
			Vertex u = e.getU();
			Vertex v = e.getV();
			if (d.find(u.getNode()) != d.find(v.getNode())) {
				/* Vertices v and u are not in the same component */
				tree.add(e);

				/* Union them in the tree */
				d.union(u.getNode(), v.getNode());
			}
		}

		/*
		 * Before summing, complete the final vertex distance calculation; we
		 * achieve a slight speed-up here because there will be strictly less
		 * than nC2 edges in the minimum spanning tree.
		 */
		float sum = 0;

		for (Edge e : tree) {
			sum += Math.sqrt(e.getWeight());
		}

		System.out.println("Soma: " + sum);

		// Imprime a tree
		imprimeArvore(tree);

		System.out.println("Concluída impressao da árvore mínima");

		/* Now return the sum */
		return sum;
	}
}

/*
 * Class representing a single vertex, holding a pointer to a node in the
 * disjoint-set data structure. Also contains facilities for calculating simple
 * and Euclidean distances.
 */
class Vertex {
	private double lat;
	private double lon;
	private String estado;
	private Node n;

	public Vertex(double _lat, double _lon, String _estado) {
		this.setLat(_lat);
		this.setLon(_lon);
		this.setEstado(_estado);
	}

	public void setNode(Node n) {
		this.n = n;
	}

	public Node getNode() {
		return this.n;
	}

	public static double distancia(Vertex a, Vertex b) {
		double R = 6371.0; // raio da terrakm
		double dLat = (b.getLat() - a.getLat()) * Math.PI / 180.0;
		double dLon = (b.getLon() - a.getLon()) * Math.PI / 180;

		double x = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(a.getLat() * Math.PI / 180)
				* Math.cos(b.getLat() * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
		double z = R * y;
		return z;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}

/*
 * Class representing a single edge, holding pointers to the vertices it
 * connects. Also includes facilities for calculating sums of edge weights.
 */
class Edge implements Comparable {
	private double weight;
	private Vertex u, v;

	public Edge(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
	}

	public Edge(Vertex u, Vertex v, double d) {
		this(u, v);
		this.weight = d;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double w) {
		this.weight = w;
	}

	public Vertex getU() {
		return this.u;
	}

	public Vertex getV() {
		return this.v;
	}

	public int compareTo(Object o) {
		Edge other = (Edge) o;

		if (this.getWeight() < other.getWeight())
			return -1;
		else if (this.getWeight() > other.getWeight())
			return 1;
		else
			return 0;
	}

	public static float sum(List<Edge> edges) {
		float sum = 0;

		for (Edge e : edges) {
			sum += e.getWeight();
		}

		return sum;
	}
}

/*
 * Implementation of a node, to be used with the DisjointSet tree structure.
 */
class Node {
	int rank; // the approximate count of nodes below this node
	int index; // a unique index for each node in the tree
	Node parent;

	public Node(int r, int i, Node p) {
		this.rank = r;
		this.index = i;
		this.parent = p;
	}
}

/*
 * A simple implementation of the disjoint-set data structure. Elements of
 * disjoint sets are represented in a tree, in which each node references its
 * parent. A "union by rank" strategy is used to optimize the combination of two
 * trees, making sure to always attach a smaller tree to the root of the larger
 * tree.
 */
class DisjointSet {
	private int nodeCount = 0;
	private int setCount = 0;

	ArrayList<Node> rootNodes;

	/*
	 * Returns the index of the set that n is currently in. The index of the
	 * root node of each set uniquely identifies the set. This is used to
	 * determine whether two elements are in the same set.
	 */
	public int find(Node n) {
		Node current = n;

		/* Ride the pointer up to the root node */
		while (current.parent != null)
			current = current.parent;

		Node root = current;

		/*
		 * Ride the pointer up to the root node again, but make each node below
		 * a direct child of the root. This alters the tree such that future
		 * calls will reach the root more quickly.
		 */
		current = n;
		while (current != root) {
			Node temp = current.parent;
			current.parent = root;
			current = temp;
		}

		return root.index;
	}

	/*
	 * Combines the sets containing nodes i and j.
	 */
	public void union(Node i, Node j) {
		int indexI = find(i);
		int indexJ = find(j);

		/* Are these nodes already part of the same set? */
		if (indexI == indexJ)
			return;

		/* Get the root nodes of each set (this will run in constant time) */
		Node a = this.rootNodes.get(indexI);
		Node b = this.rootNodes.get(indexJ);

		/* Attach the smaller tree to the root of the larger tree */
		if (a.rank < b.rank) {
			a.parent = b;
		} else if (a.rank > b.rank) {
			b.parent = a;
		} else {
			b.parent = a;
			a.rank++;
		}

		this.setCount--;
	}

	/*
	 * Takes a list of n vertices and creates n disjoint singleton sets.
	 */
	public void makeSets(List<Vertex> vertices) {
		for (Vertex v : vertices)
			makeSet(v);
	}

	/*
	 * Creates a singleton set containing one vertex.
	 */
	public void makeSet(Vertex vertex) {
		Node n = new Node(0, rootNodes.size(), null);
		vertex.setNode(n);
		this.rootNodes.add(n);
		this.setCount++;
		this.nodeCount++;
	}

	public DisjointSet(List<Vertex> vertices) {
		this.rootNodes = new ArrayList<Node>(vertices.size());
		makeSets(vertices);
	}
}