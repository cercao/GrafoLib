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

public class KruskalMST {
	static ArrayList<Vertice> vertices;
	static int[][][] figura;	
	static List<String> combinacoesPossivel; 
	static boolean[][] adjEstados; 
	static int n;
	static int ne;
	static int xmax = Integer.MIN_VALUE;
	static int ymax = Integer.MIN_VALUE;
	static int xmin = Integer.MAX_VALUE;
	static int ymin = Integer.MAX_VALUE;
	static final File dir = new File("");

	public static void main(String[] args) {

		System.out.println((new Date()).toString() + ": Inicializa adj de estados" );
		
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
		
		adjEstados = new boolean[27][27];
		
		// Inicializa combinações possíveis de estados do brasil
		for(int i = 0; i<= 26; i++){
			for(int j = 0; j <= 26; j++){
				if(i == j)
					adjEstados[i][j] = true;
				else{
					String e1 = Estado.values()[i].toString();
					String e2 = Estado.values()[j].toString();												
					
					if (combinacoesPossivel.contains(e1 + e2))
						adjEstados[i][j] = true;					
				}
				
			}				
		}		
		
		// desenha os pontos, recupera total no n
		System.out.println((new Date()).toString() + ": Inicializa carregamento dos pontos" );
		carregaPontos();

		// calcula o numero de arestas (grafo completo)
		ne = (n * (n - 1)) / 2;

		/* Run the tests for size n */
		float test2 = encontraMST();

		System.out.printf("Tamanho: %d:\n", n);
		System.out.printf("Custo: %f\n", test2);

	}

	public static void imprimeArvore(List<Aresta> tree) {
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
						Aresta e = tree.get(i);

						// Monta a origem e destino de uma linha (fator de
						// escala 30)vertices.get(e.getU());
						int lat1 = (int) (vertices.get(e.getU()).getLat() * 30);
						int lon1 = (int) (vertices.get(e.getU()).getLon() * 30);

						int lat2 = (int) (vertices.get(e.getV()).getLat() * 30);
						int lon2 = (int) (vertices.get(e.getV()).getLon() * 30);

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

			// vertices = new ArrayList<Vertice>(total);
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
							vertices = new ArrayList<Vertice>(total);
							n = total;
						}

						// marca quantidade de nós
						//if (estado.equals("BA")) {
						if (!estado.equals("N") && !estado.equals("M"))							
							vertices.add(new Vertice(a1, a2, Estado.valueOf(estado).valor));
						//}
					}
					assert (ymin <= ymax);

					linha = b.readLine();
				}

			}

		} catch (IOException ex) {
			Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	/***
	 * Carrega as arestas, faz o sort, monta UnionFind e aplica kruskal
	 * @return peso
	 */
	public static float encontraMST() {

		List<Aresta> arestas = new ArrayList<Aresta>();
				
		// qtd --- 100
		// atual-- x
		// x = 100*atual/qtd
		// carrega arestas
		
		int cont = 0;
		int logInt = (vertices.size() * vertices.size());
		System.out.println((new Date()).toString() + ": Inicializa carregamento das arestas com tamanho: " + vertices.size() * vertices.size() );
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {										
					
				if (i == j)
					continue;
				
				Vertice a = vertices.get(i);
				Vertice b = vertices.get(j);	
				
				// Se é um estado vizinho..
				if (adjEstados[a.getEstado()][b.getEstado()]){			
				
					Aresta e = new Aresta(i, j, Vertice.distancia(a, b));
					arestas.add(e);
				}
			}
		}

		System.out.println((new Date()).toString() + ": Terminou carregamento das arestas");

		UnionFind d = new UnionFind(vertices);

		List<Aresta> tree = new ArrayList<Aresta>();

		// Comparação modificada
		Collections.sort(arestas);

		System.out.println((new Date()).toString() + ": Inicia Kruskal");
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
		
		System.out.println((new Date()).toString() + ": Finalizou Kruskal");

		/*
		 * Before summing, complete the final Vertice distance calculation; we
		 * achieve a slight speed-up here because there will be strictly less
		 * than nC2 edges in the minimum spanning tree.
		 */
		float sum = 0;

		for (Aresta e : tree) {
			sum += Math.sqrt(e.getWeight());
		}

		System.out.println("Soma: " + sum);

		// Imprime a tree
		imprimeArvore(tree);

		System.out.println("Concluída impressao da árvore mínima");

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