package desafios.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TesteCoordenadas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
		List<Linha> linhasParaRemover = new ArrayList<Linha>();
		int auxi = 0;
		//while (linhasParaRemover.size() > 0 || auxi == 0) {
			auxi = 1;
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
				if (l.getEstado().equals(Estado.AM.toString()))
					linhas.add(l);
				
				linha = b.readLine();
			}
			
			// ordena por estado e latidute
			order(linhas, 1);			
			
			// Agrupa por estado
			ArrayList<ArrayList<Linha>> linhasPorEstado = new ArrayList<ArrayList<Linha>>(); 
			Linha l1 = new Linha();
			ArrayList<Linha> laux = new ArrayList<Linha>();
			for (Linha l : linhas){		
				
				laux.add(l);
				
				// se estiver vazio ou for diferente do item anterior, cria nova lista			
				if (laux.size() != 1 && !l.getEstado().equals(l1.getEstado()) ) {
					linhasPorEstado.add(laux);
					laux = new ArrayList<Linha>();
				}					
				
				l1 = l;
				
			}
			
			// para cada estado, ordena as cidades por latitude, remove incompativeis
			// e faz a mesma coisa por latitude		 
			
			File fout = new File("out.txt");
			FileOutputStream fos = new FileOutputStream(fout);
		 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
			for (int i = 0; i < linhas.size(); i++) {
				bw.write(linhas.get(i).getCidade()+";"+ linhas.get(i).getEstado_descricao()+";"+linhas.get(i).getEstado() +";"+linhas.get(i).getLat() + ";" + linhas.get(i).getLng());
				bw.newLine();
			}
		 
			bw.close();
		//}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
