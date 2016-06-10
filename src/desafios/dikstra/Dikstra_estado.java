package desafios.dikstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import desafios.DesenhaPontos;
import desafios.kruskal.Aresta;
import desafios.kruskal.Estado;
import desafios.kruskal.Vertice;
import desafios.kruskal.converter.converter;


@SuppressWarnings("unchecked")

public class Dikstra_estado {
	static GrafoPonderado g = new GrafoPonderado();  
	static ArrayList<Vertice2> vertices;
	static BufferedWriter out = null;
	static int[][][] figura;
	static int[] PI;
	static double maiorDistancia = 0;
	static List<String> combinacoesPossivel;
	static boolean[][] adjEstados;
	static int dx = 0;
	static int dy = 0;
	static int n;
	static int menorIndice = 0;
	static int maiorIndice = 0;
	static double menorValor = Double.MAX_VALUE;
	static double maiorValor = 0;
	static int ne;
	static int xmax = Integer.MIN_VALUE;
	static int ymax = Integer.MIN_VALUE;
	static int xmin = Integer.MAX_VALUE;
	static int ymin = Integer.MAX_VALUE;
	static ArrayList<Aresta2> arestas;
	static Boolean deveFiltrar = false;
	static Estado e = Estado.AM;
	static final File dir = new File("");

	public static void main(String[] args) throws IOException {

		System.out.println(getDateString() + ": Inicializa Imagem");
		inicializaImagem();
		
		System.out.println(getDateString() + ": Inicializa Matriz Adj Filtro de Estados");
		combinacoesPossivel = new ArrayList<String>();
		combinacoesPossivel.add("RSSC");
		combinacoesPossivel.add("RSRS");
		combinacoesPossivel.add("SCRS");
		combinacoesPossivel.add("SCSC");
		combinacoesPossivel.add("SCPR");
		combinacoesPossivel.add("PRPR");
		combinacoesPossivel.add("PRSP");
		combinacoesPossivel.add("PRMS");
		combinacoesPossivel.add("SPSP");
		combinacoesPossivel.add("SPMG");
		combinacoesPossivel.add("SPMS");
		combinacoesPossivel.add("MSMS");
		combinacoesPossivel.add("MSGO");
		combinacoesPossivel.add("MTGO");
		combinacoesPossivel.add("GOMT");
		combinacoesPossivel.add("MTPA");
		combinacoesPossivel.add("MTMT");
		combinacoesPossivel.add("GOMG");
		combinacoesPossivel.add("MGMG");
		combinacoesPossivel.add("MGGO");
		combinacoesPossivel.add("GOGO");
		combinacoesPossivel.add("GODF");
		combinacoesPossivel.add("DFGO");
		combinacoesPossivel.add("DFDF");
		combinacoesPossivel.add("PAPA");
		combinacoesPossivel.add("AMMT");
		combinacoesPossivel.add("MTAM");
		combinacoesPossivel.add("PAAP");
		combinacoesPossivel.add("AMAM");
		combinacoesPossivel.add("AMRR");
		combinacoesPossivel.add("RRAM");
		combinacoesPossivel.add("RRRR");
		combinacoesPossivel.add("MSMS");
		combinacoesPossivel.add("MSMT");
		combinacoesPossivel.add("MTMS");
				
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
		
		System.out.println(getDateString() + ": Carrega arquivo de coordenadas");
		// coordenadas
		BufferedReader b = new BufferedReader(new FileReader(new File("coordenadas.txt")));

		// leitura
		String linha;
		
		linha = b.readLine();
				
		List<Linha> linhas = new ArrayList<Linha>();

		// Carrega coordenadas
		while (linha != null && linha.length() > 0) {
			String[] lista = linha.split(";");
			String estado = lista[2];		

			Linha l = new Linha();
			l.setCidade(lista[0]);
			l.setEstado_descricao(lista[1]);
			l.setEstado(lista[2]);			
			l.setLat(Double.parseDouble(lista[3]));
			l.setLng(Double.parseDouble(lista[4]));
			
			// TODO: filtrando pela amazonia, temporário		
			if (deveFiltrar && !estado.equals(e.toString()))				
				linha = b.readLine();
			else{
				linhas.add(l);
				linha = b.readLine();
			}
			
			
		}
		
		// ordena por estado e latidute
		System.out.println(getDateString() + ": Ordena por Lat e encontra maior e menor ponto");
		//order(linhas, 1);	
		
		double menor = menorValor;
		double maior = maiorValor;
		
		System.out.println(getDateString() + ": Qtd linhas: " + linhas.size() + "; Maior: " + maior  + "; Menor: " + menor);
		
		System.out.println(getDateString() + ": Carrega vertices");
		carregaPontos();		
		
		System.out.println(getDateString() + ": Carrega arestas");
		carregaArestas();					
		
		// aplica dikstra (atencao, nao passar o valor da aresta e sim a posicao do cara no array de vértices)
		//dijkstra(vertices, arestas, indexMenorLat, indexMaiorLat);
		System.out.println(getDateString() + ": Inicia Optimized Shortest Path");
        OptimizedShortestPath dij = new OptimizedShortestPath(g, menorIndice, maiorIndice);
        dij.executa();
        System.out.println(getDateString() + ": Finaliza Optimized Shortest Path");
        //dij.printPath(indexMenorLat);
        PI = dij.getPI();
        System.out.println(getDateString() + ": Desenha caminho encontrado");
        gravarCaminho(PI, maiorIndice);
			
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
			
			System.out.println(getDateString() + ": Finaliza arquivo");
			System.out.println(getDateString() + ": Converte PPM para PNG");
			converter.converter("_aux.ppm", "dikstraPais1.png");
			//System.out.println((new Date()).toString() + ": Impressão concluída.");
			System.out.println(getDateString() + ": Fim");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public static void gravarCaminho(int[] pi, int orig) {

		int count = 0;
		while (pi[orig] != orig) {
						            
			// escala 30)vertices.get(e.getU());
			// faz a linha entre a origem (mais distante do ponto inicial) até o nó anterior a ela
			int lat1 = (int)(vertices.get(orig).getLat() * 30);
			int lon1 = (int)(vertices.get(orig).getLon() * 30);

			int lat2 = (int)(vertices.get(pi[orig]).getLat() * 30);
			int lon2 = (int)(vertices.get(pi[orig]).getLon() * 30);		

			desenhaLinha(lat1, lon1, lat2, lon2);

            // atualiza origem
            orig = pi[orig];
        }
	}
	
	private static void order(List<Linha> linhas, int latLng) {

	    Collections.sort(linhas, new Comparator() {

	        public int compare(Object o1, Object o2) {

	            String x1 = ((Linha) o1).getEstado();
	            String x2 = ((Linha) o2).getEstado();
	            int sComp = x1.compareTo(x2);

	            if (sComp != 0) {
	               return sComp;
	            } else {
	               Double l1 = latLng == 1 ? ((Linha) o1).getLat() : ((Linha) o1).getLng();
	               Double l2 = latLng == 1 ? ((Linha) o2).getLat() : ((Linha) o2).getLng();
	               return l1.compareTo(l2);
	            }
	        }
	    });
	}
	
	private static String getDateString() {
	    return (new Date()).toString() ;
	}
		
	public static void carregaPontos() {
		try {

			// limpa os vertices e arestas antigos
			vertices = new ArrayList<Vertice2>();

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
					int count = 0;
					while (linha != null && linha.length() > 0) {
						String[] lista = linha.split(";");
						String estado = lista[2];

						if (deveFiltrar && !estado.equals(e.toString())) {
							linha = b.readLine();
							continue;
						}						

						double a1 = Double.parseDouble(lista[3]);
						double a2 = Double.parseDouble(lista[4]);

						if (vez == 0) {
							total++;
						} else {
							if (vertices == null) {
								vertices = new ArrayList<Vertice2>(total);
								n = total;
							}

							// marca quantidade de nós
							// if (estado.equals("BA")) {
							//if (!estado.equals("N") && !estado.equals("M"))
							
							Vertice2 v1 = new Vertice2(a1, a2, Estado.valueOf(estado).valor);
							
							if (v1.getLat() < menorValor ){
								menorValor = v1.getLat();
								menorIndice = count;
							}
							
							if (v1.getLat() > maiorValor ){
								maiorValor = v1.getLat();
								maiorIndice = count;
							}
							
							vertices.add(v1);
							// }
						}
						assert (ymin <= ymax);
						count++;
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
	 * Carrega as arestas
	 * 
	 */
	public static void carregaArestas() {

//		arestas = new ArrayList<Aresta2>();
//				
//		for (int i = 0; i < vertices.size(); i++) {
//			for (int j = 0; j < vertices.size(); j++) {
//
//				if (i == j)
//					continue;
//
//				Vertice2 a = vertices.get(i);
//				Vertice2 b = vertices.get(j);
//
//				Aresta2 aresta = new Aresta2(i, j, Vertice2.distancia(a, b));
//				arestas.add(aresta);
//			}
//		}
		
		g.setN(vertices.size());
		
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
	
				if (i == j)
					continue;

				Vertice2 a = vertices.get(i);
				Vertice2 b = vertices.get(j);
					
				// Se é um estado vizinho..
				if (adjEstados[a.getEstado()][b.getEstado()]){							
					g.addAresta(i, j, Vertice2.distancia(a, b));
				}
			}


        }
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
	
	static class Linha {
		private double lat;
		private double lng;
		private String cidade;
	    private String estado;
	    private String estado_descricao;
	    
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public String getEstado_descricao() {
			return estado_descricao;
		}
		public void setEstado_descricao(String estado_descricao) {
			this.estado_descricao = estado_descricao;
		}
		public String getCidade() {
			return cidade;
		}
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}	    	  
	}
	
	public static void dijkstra(ArrayList<Vertice2> vexs, ArrayList<Aresta2> edges, int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);

		boolean[] visited = new boolean[vexs.size()];
		// No caminho mais curto de um índice de vértice
		int[] parent = new int[vexs.size()];
		// O ponto de partida do caminho mais curto para o vértice atual
		double[] sp = new double[vexs.size()];
		parent[start] = -1;
		for (int i = 0; i < sp.length; i++) {
			sp[i] = Integer.MAX_VALUE;
		}
		
		sp[start] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			visited[current] = true;
			if (current == end) {
				// done
				StringBuilder sb = new StringBuilder();
				while (current != -1) {
					sb.insert(0, current + " ");
					current = parent[current];
				}
				sb.append("---- " + sp[end]);
				System.out.println(sb.toString());
				return;
			}
			for (Aresta2 e : edges) {
				int w, s;
				if (e.getU() == current && !visited[e.getV()]) {
					w = e.getU();
					s = e.getV();
				} else if (e.getV() == current && !visited[e.getU()]) {
					w = e.getV();
					s = e.getU();
				} else {
					continue;
				}
				// Encontrar um lado disponíveis
				double a = (sp[w] + e.getWeight());
				double b = sp[s];
				if (a < b) {
					sp[s] = a;
					parent[s] = w;
				}
				if (!queue.contains(s)) {
					queue.offer(s);
				}
			}
		}
	}
}

class Vertex2 {
	String data;

	public Vertex2(String data) {
		this.data = data;
	}
}

class Edge2 {
	int begin;
	int end;
	int weight;

	public Edge2(int begin, int end, int weight) {
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
}