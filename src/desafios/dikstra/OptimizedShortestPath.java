/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafios.dikstra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author meira
 */
public class OptimizedShortestPath {

    int pi[];
    GrafoPonderado g;
    double d[];
    Heap Q;
    boolean processado[];
    
    int n;
    int invalido = -1;
    //start
    int s;
    int e;

    public OptimizedShortestPath(GrafoPonderado g, int s, int e) {
        this.g = g;
        this.e = e;
        n = g.getN();
        pi = new int[n];
        d = new double[n];
        Q = new Heap(g);
        processado = new boolean[n];
        for (int i = 0; i < n; i++) {
            pi[i] = invalido;
            d[i] = Integer.MAX_VALUE;   
            processado[i]=false;
        }
        d[s] = 0;
        pi[s] = s;
        Q.decreaseKey(s, 0);
    }

    public void executa() {
        while (!Q.isEmpty()) {
            int u = Q.extraMin();
            processado[u]=true;
            
            // verifica se é o último
            //if (u == this.e)
            //	break;            	
            
            int viz[] = g.vizinhos(u);
            for (int i = 0; i < viz.length; i++) {
                int vizi = viz[i];
                if (!processado[vizi]) {
                    if (d[vizi] > d[u] + g.getW(u, vizi)) {
                        d[vizi] = d[u] + g.getW(u, vizi);
                        pi[vizi] = u;
                        Q.decreaseKey(vizi,d[vizi]);
                    }
                }
            }
        }
    }   
    
    public int[] getPI()
    {    	
    	return pi;
    }
    
    public int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
   

    public void printPath(int orig) {
    	
    	
        while (pi[orig] != orig) {
            System.out.print(orig + "--");
            orig = pi[orig];
        }

        System.out.println(orig + "\n");
    }
   
}
