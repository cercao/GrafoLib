package desafios.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import desafios.DesenhaPontos;

@SuppressWarnings("unchecked")

public class KruskalMST_estado {
	static ArrayList<Vertice> vertices;
	static BufferedWriter out = null;
	static int[][][] figura;
	static List<String> combinacoesPossivel;
	static boolean[][] adjEstados;
	static int dx = 0;
	static int dy = 0;
	static int n;
	static int ne;
	static int xmax = Integer.MIN_VALUE;
	static int ymax = Integer.MIN_VALUE;
	static int xmin = Integer.MAX_VALUE;
	static int ymin = Integer.MAX_VALUE;
	static final File dir = new File("");

	public static void main(String[] args) {

		System.out.println((new Date()).toString() + ": Inicializa imagem");

		inicializaImagem();

		for (Estado e : Estado.values()) {
			// desenha os pontos, recupera total no n
			System.out.println((new Date()).toString() + ": Inicializa carregamento dos pontos: " + e.toString());

			carregaPontos(e);

			/* Run the tests for size n */
			float test2 = encontraMST(e);
			
			System.out.printf("Custo: %f\n", test2);
		}
		
		// imprime o arquivo
		try {
			
			for (int i = dx - 1; i >= 0; i--) {
				for (int j = 0; j < dy; j++) {
					out.write(figura[i][j][0] + " " + figura[i][j][1] + " " + figura[i][j][2] + " ");
				}
				out.write("\n");
			}	
			
			out.write("\n");
			out.close();
			
			System.out.println((new Date()).toString() + ": Impressão concluída.");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public static void gravarArvore(List<Aresta> tree) {
		// desenha a arvore
		for (int i = 0; i < tree.size(); i++) {
			Aresta a = tree.get(i);

			// Monta a origem e destino de uma linha (fator de
			// escala 30)vertices.get(e.getU());
			int lat1 = (int) (vertices.get(a.getU()).getLat() * 30);
			int lon1 = (int) (vertices.get(a.getU()).getLon() * 30);

			int lat2 = (int) (vertices.get(a.getV()).getLat() * 30);
			int lon2 = (int) (vertices.get(a.getV()).getLon() * 30);

			desenhaLinha(lat1, lon1, lat2, lon2);
		}

	}

	public static void carregaPontos(Estado e) {
		try {

			// limpa os vertices e arestas antigos
			vertices = new ArrayList<Vertice>();

			int xmax = Integer.MIN_VALUE;
			int ymax = Integer.MIN_VALUE;
			int xmin = Integer.MAX_VALUE;
			int ymin = Integer.MAX_VALUE;

			// primeira vez encontra os limites xmax, xmin, ymax, ymin
			// segunda vez imprime o arquivo ppm

			int total = 0;
			for (int vez = 0; vez < 2; vez++) {

				if (vez == 1) {

					// coordenadas
					BufferedReader b = new BufferedReader(new FileReader(new File("coordenadas.txt")));

					// leitura
					String linha = b.readLine();

					while (linha != null && linha.length() > 0) {
						String[] lista = linha.split(";");
						String estado = lista[2];

						if (!estado.equals(e.toString())) {
							linha = b.readLine();
							continue;
						}

						double a1 = Double.parseDouble(lista[3]);
						double a2 = Double.parseDouble(lista[4]);

						if (vez == 0) {
							total++;
						} else {
							if (vertices == null) {
								vertices = new ArrayList<Vertice>(total);
								n = total;
							}

							// marca quantidade de nós
							// if (estado.equals("BA")) {
							//if (!estado.equals("N") && !estado.equals("M"))
							vertices.add(new Vertice(a1, a2, Estado.valueOf(estado).valor));
							// }
						}
						assert (ymin <= ymax);

						linha = b.readLine();
					}

				}
			}
 
		} catch (IOException ex) {
			Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static void inicializaImagem() {
		try {
			xmax = Integer.MIN_VALUE;
			ymax = Integer.MIN_VALUE;
			xmin = Integer.MAX_VALUE;
			ymin = Integer.MAX_VALUE;


			// primeira vez encontra os limites xmax, xmin, ymax, ymin
			// segunda vez imprime o arquivo ppm

			int total = 0;
			for (int vez = 0; vez < 2; vez++) {

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

					double a1 = Double.parseDouble(lista[3]);
					double a2 = Double.parseDouble(lista[4]);

					// converte em inteiro com um fator de escala 30.
					int x = (int) (a1 * 30);
					int y = (int) (a2 * 30);

					if (vez == 0) {
						// procura min e max
						if (x < xmin)
							xmin = x;
						if (x > xmax)
							xmax = x;
						if (y < ymin)
							ymin = y;
						if (y > ymax)
							ymax = y;

						// if (estado.equals("BA"))
						total++;

					}
					assert (ymin <= ymax);

					linha = b.readLine();
				}
			}

			// vertices = new ArrayList<Vertice>(total);
		} catch (IOException ex) {
			Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/***
	 * Carrega as arestas, faz o sort, monta UnionFind e aplica kruskal
	 * 
	 * @return peso
	 */
	public static float encontraMST(Estado e) {

		/* Create a list of Arestas */
		List<Aresta> arestas = new ArrayList<Aresta>();
		
		System.out.printf("Qtd de vértices: " + vertices.size());

		System.out.println((new Date()).toString() + ": Inicializa carregamento das arestas: " + e.toString());
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {

				if (i == j)
					continue;

				Vertice a = vertices.get(i);
				Vertice b = vertices.get(j);

				Aresta aresta = new Aresta(i, j, Vertice.distancia(a, b));
				arestas.add(aresta);
			}
		}

		System.out.println((new Date()).toString() + ": Terminou carregamento das arestas");
		
		UnionFind d = new UnionFind(vertices);
		
		List<Aresta> tree = new ArrayList<Aresta>();

		// Comparação modificada
		Collections.sort(arestas);

		// Kruskal 
		for (Aresta a : arestas) {
			Vertice u = vertices.get(a.getU());
			Vertice v = vertices.get(a.getV());
			if (d.find(u.getNode()) != d.find(v.getNode())) {
				// Os vértices v e u não estão na mesma componente 
				tree.add(a);

				// Faz união
				d.union(u.getNode(), v.getNode());
			}
		}

		float sum = 0;

		for (Aresta a : tree) {
			sum += Math.sqrt(a.getWeight());
		}

		System.out.println("Soma: " + sum);

		// Imprime a tree
		gravarArvore(tree);

		System.out.println("Concluída gravação da árvore mínima");

		/* Now return the sum */
		return sum;
	}
	
	public static void setBranco(int i, int j) {
		figura[i][j][0] = 255;
		figura[i][j][1] = 255;
		figura[i][j][2] = 255;
	}
	
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

}