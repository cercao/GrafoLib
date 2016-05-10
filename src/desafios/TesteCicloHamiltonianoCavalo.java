package desafios;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class TesteCicloHamiltonianoCavalo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Loga
		Date d = new Date();
		System.out.println(d.toString() + ": Iniciando..");
		
		// Carrega dados do arquivo, baseado no pdf passa em sala de aula
		List<String> lines = 
				Files.readAllLines(
						Paths.get(
								"C:/Users/Lucas Rodrigues/workspace/GraphLib/src/cavalo36.txt"),
                StandardCharsets.UTF_8);
		
		// Inicializa variaveis
		int matrixTamanho = 0;
		int[][] matrix = null;

		int index = 0;
		for (String line : lines) {
			if (index == 0){
				matrixTamanho = Integer.parseInt(line);	
				System.out.println("Iniciando matriz..");
				matrix = new int[matrixTamanho][matrixTamanho];	

				// inicializa com zeros
				for (int i = 0; i < matrix.length; i++) {
				    for (int j = 0; j < matrix[0].length; j++) {
				        matrix[i][j] = 0;
				    }
				}
				
				index++;
				continue;
			}		
			
			// Encontra os vértices de uma aresta
			String[] coords =  line.split("\\s+");			
			
			// Adiciona vertice
			matrix[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])] = 1;											
			matrix[Integer.parseInt(coords[1])][Integer.parseInt(coords[0])] = 1;									
			
			index++;
		}		
				
//		System.out.println("Imprimindo matriz..");
//		for (int i = 0; i < matrix.length; i++) {
//		    for (int j = 0; j < matrix[0].length; j++) {
//		        System.out.print(matrix[i][j] + " ");
//		    }
//		    System.out.print("\n");
//		}						
		
		System.out.println("Iniciando teste..");
				
		CicloHamiltonianoCavalo ch = new CicloHamiltonianoCavalo();		
		//int V = matrixTamanho;

		ch.buscarCicloHamiltoniano(matrix);
		
		// Loga
		d = new Date();
		System.out.println(d.toString() + ": Fim..");
	}

}
