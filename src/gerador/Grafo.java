/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador;

/**
 *
 * @author meira
 */
public class Grafo {

    public void geraPasseioCavalo() {
        int size = 30;
        int n = size * size;
        int m[][] = new int[n][n];
        Vertice mvet[][] = new Vertice[n][n];
        Vertice vet[] = new Vertice[n];
        //System.out.println("teste");
        int top = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Vertice v = new Vertice(i, j, top);
                vet[top++] = v;
                mvet[i][j] = v;
            }
        }
        for (int i = 0; i < n; i++) {
            //System.out.println(vet[i]);
        }
        System.out.println(n);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int i2 = 0; i2 < size; i2++) {
                    for (int j2 = 0; j2 < size; j2++) {
                        if (i2 == i + 2) {
                            if ((j == j2 + 1) || (j == j2 - 1)) {
                                //System.out.println(mvet[i][j] + "--" + mvet[i2][j2]);
                                int id1 = mvet[i][j].id;
                                int id2 = mvet[i2][j2].id;
                                System.out.printf("%d %d\n", id1, id2);
                            }
                        }
                        if (i2 == i + 1) {
                            if ((j == j2 + 2) || (j == j2 - 2)) {
                                //System.out.println(mvet[i][j] + "--" + mvet[i2][j2]);
                                int id1 = mvet[i][j].id;
                                int id2 = mvet[i2][j2].id;
                                System.out.printf("%d %d\n", id1, id2);

                            }
                        }

                    }
                }
            }
        }
    }

    public void geraGrafoKSize(int size) {

        int n = size * size;
        int m[][] = new int[n][n];
        Vertice mvet[][] = new Vertice[n][n];
        Vertice vet[] = new Vertice[n];
        //System.out.println("teste");
        int top = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Vertice v = new Vertice(i, j, top);
                vet[top++] = v;
                mvet[i][j] = v;
            }
        }
        for (int i = 0; i < n; i++) {
            //System.out.println(vet[i]);
        }
        System.out.println(n);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int i2 = 0; i2 < size; i2++) {
                    for (int j2 = 0; j2 < size; j2++) {
                        if (aresta(i, j, i2, j2)) {
                            int id1 = mvet[i][j].id;
                            int id2 = mvet[i2][j2].id;
                            if(id1<id2){
                                System.out.printf("%d %d\n", id1, id2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void geraGrafoPiao(int size) {

        int n = size * size;
        int m[][] = new int[n][n];
        int mvet[][] = new int[n][n];
        
        //System.out.println("teste");
        int top = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mvet[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            //System.out.println(vet[i]);
        }
        
        mvet[0][0] = 1;
        
        System.out.println(n);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if (i + 1 < size){            	
	            	mvet[i + 1][j] = 1;
	            	mvet[j][i + 1] = 1;
            	}
            	
            	if (i - 1 > 0){
            		mvet[i - 1][j] = 1;
	            	mvet[j][i - 1] = 1;
            	}
            	
            	if (j + 1 < size){            	
	            	mvet[j + 1][j] = 1;
	            	mvet[j][j + 1] = 1;
            	}
            	
            	if (j - 1 > 0){
            		mvet[j - 1][j] = 1;
	            	mvet[j][j - 1] = 1;
            	}
            	
            	
            }
        }
        
        
		System.out.print("   | ");	
		// imprimindo header
		for (int i = 0; i < mvet.length; i++) {					      
			System.out.print(String.format("%02d", i) + " | ");		   		   
			
		}	
		
		System.out.println();
		
		for (int i = 0; i < mvet.length; i++) {
			
			System.out.print( String.format("%02d", i) + " | ");
		    for (int j = 0; j < mvet[0].length; j++) {
		    	
		        System.out.print(String.format("%02d", mvet[i][j]) + " | ");
		    }
		    System.out.print("\n");
		}					
    }

    
    public boolean aresta(int i, int j, int i2, int j2) {
        if (i == i2) {
            return false;
        }
        if (j == j2) {
            return false;
        }
        int dx = Math.abs(i - i2);
        int dy = Math.abs(j - j2);
        return dx != dy;
    }

}
