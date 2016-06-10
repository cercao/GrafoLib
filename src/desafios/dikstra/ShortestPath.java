/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafios.dikstra;
 
import java.util.ArrayList;
 
/**
 *
 * @author meira
 */
public class ShortestPath {
 
    int pi[];
    GrafoPonderado g;
    double d[];
    ArrayList<Integer> Q;
    ArrayList<Integer> S;
    int n;
    int invalido = -1;
    //start
    int s;
    // end
    int e;
 
    public ShortestPath(GrafoPonderado g, int s) {
        this.g = g;
        n = g.getN();
        pi = new int[n];
        d = new double[n];
        Q = new ArrayList<>();
        S = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pi[i] = invalido;
            d[i] = Integer.MAX_VALUE;
            Q.add(i);
        }
        d[s] = 0;
        pi[s] = s;
    }
 
    public void executa() {
        while (!Q.isEmpty()) {
            int u = extraiMin();
            int viz[] = g.vizinhos(u);
            for (int i = 0; i < viz.length; i++) {
                int vizi = viz[i];
                if (Q.contains(vizi)) {
                	// a distancia do vizinho atual é maior que a distancia do nó atual + a distancia do nó atual até o nó vizinho ?
                    if (d[vizi] > d[u] + g.getW(u, vizi)) {
                        d[vizi] = d[u] + g.getW(u, vizi);
                        pi[vizi] = u;
                    }
                }
            }
        }
    }
 
    private int extraiMin() {
        int minPos = 0;
        int minVertex = Q.get(minPos);
        for (int i = 1; i < Q.size(); i++) {
            if (d[Q.get(i)] < d[Q.get(minPos)]) {
                minPos = i;
                minVertex = Q.get(minPos);
            }
        }
        Q.remove(minPos);
        return minVertex;
    }
 
    public void printPath(int orig) {
        while (pi[orig] != orig) {
            System.out.print(orig + "--");
            orig = pi[orig];
        }
 
        System.out.println(orig + "\n");
    }
 
}