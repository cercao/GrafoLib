/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafios.dikstra;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author meira
 */
public class GrafoPonderado {
 
    private double[][] adj;
    private int n;
    private final int INVALID = Integer.MAX_VALUE;
    private ArrayList<Integer> percurso;
    private boolean[] visitado = null;
 
    public void setN(int n) {
        this.n = n;
        adj = new double[n][n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (u != v) {
                    adj[u][v] = INVALID;
                } else {
                    adj[u][u] = 0;
                }
            }
        }
    }
 
    public void addAresta(int u, int v, double w) {
        adj[u][v] = w;
        adj[v][u] = w;
    }
 
    public void removeArestas(int u, int v) {
        adj[u][v] = INVALID;
        adj[v][u] = INVALID;
    }
 
    public int grau(int v) {
        int grau = 0;
        for (int i = 0; i < n; i++) {
            if (i!=v&&adj[v][i] != INVALID) {
                grau++;
            }
        }
        return grau;
    }
 
    public int[] vizinhos(int v) {
        int grau = grau(v);
        int[] vet = new int[grau];
        int top = 0;
        for (int i = 0; i < n; i++) {
            if (v != i && adj[v][i] != INVALID) {
                vet[top++] = i;
            }
        }
        return vet;
    }
 
    public void carrega(File f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linha;
            linha = br.readLine();
            this.n = Integer.parseInt(linha);
            this.setN(this.n);
            linha = br.readLine();
            while (linha != null && linha.length() > 2) {
                System.out.println(linha);
                String[] list = linha.split(" ");
                int u = Integer.parseInt(list[0]);
                int v = Integer.parseInt(list[1]);
                double w = Double.parseDouble(list[2]);
 
                this.addAresta(u, v, w);
                linha = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
    }
 
    public void print(int precision) {
        String aux = "%6." + precision + "f";
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
 
                if (adj[i][j] != INVALID) {
                    System.out.printf(aux, adj[i][j]);
                } else {
                    System.out.printf(" null ");
                }
            }
            System.out.printf("\n");
        }
    }
 
    double custo(int[] rota) {
        double custo = 0;
        for (int i = 0; i < rota.length - 1; i++) {
            custo += this.adj[rota[i]][rota[i + 1]];
        }
        custo += this.adj[rota[rota.length - 1]][rota[0]];
        return custo;
    }
 
    public void print(int[] rota) {
        for (int i = 0; i < rota.length; i++) {
            System.out.print(rota[i] + "-");
        }
        System.out.print(rota[0] + ".  Custo=" + this.custo(rota) + "\n");
    }
    public int getN() {
        return n;
    }
    public int[] dfs(int v) {
        this.visitado = new boolean[this.n];
        for (int i = 0; i < n; i++) {
            visitado[i] = false;
        }
        this.percurso = new ArrayList<>();
        dfsRec(v);
        int[] sol = new int[percurso.size()];
        for (int i = 0; i < sol.length; i++) {
            sol[i] = percurso.get(i);
        }
        return sol;
    }
    private void dfsRec(int v) {
        visitado[v] = true;
        System.out.printf("%2d -> ", v);
        if (this.percurso.size() % 10 == 9) {
            System.out.println();
        }
        this.percurso.add(v);
        int[] viz = this.vizinhos(v);
        for (int id = 0; id < viz.length; id++) {
            int v2 = viz[id];
            if (adj[v][v2] != INVALID) {
                if (!visitado[v2]) {
                    dfsRec(v2);
                    System.out.print("");
                }
            }
        }
    }
    private int[] dfsIterativo(int v) {
        this.visitado = new boolean[this.n];
        for (int i = 0; i < n; i++) {
            visitado[i] = false;
        }
        this.percurso = new ArrayList<>();
        int pilha[] = new int[n];
        int top = 0;
        pilha[top++] = v;
        visitado[v] = true;
        while (top > 0) {
            int w = pilha[--top];
            System.out.printf("%2d -> ", w);
            if (this.percurso.size() % 10 == 9) {
                System.out.println();
            }
            percurso.add(w);
            int[] viz = vizinhos(w);
            for (int id = 0; id < viz.length; id++) {
                int vi = viz[id];
                if (!visitado[vi]) {
                    visitado[vi] = true;
                    pilha[top++] = vi;
                }
            }
        }
        int[] sol = new int[percurso.size()];
        for (int i = 0; i < sol.length; i++) {
            sol[i] = percurso.get(i);
        }
        return sol;
    }
    private int[] buscaLargura(int v) {
        this.visitado = new boolean[this.n];
        for (int i = 0; i < n; i++) {
            visitado[i] = false;
        }
        this.percurso = new ArrayList<>();
        int fila[] = new int[n];
        int ini = 0;
        int fim = 0;
        fila[fim++] = v;
        visitado[v] = true;
        while (ini != fim) {
            int w = fila[ini++];
            System.out.printf("%2d -> ", w);
            if (this.percurso.size() % 10 == 9) {
                System.out.println();
            }
            percurso.add(w);
            int[] viz = vizinhos(w);
            for (int id = 0; id < viz.length; id++) {
                int vi = viz[id];
                if (!visitado[vi]) {
                    visitado[vi] = true;
                    fila[fim++] = vi;
                }
            }
        }
        int[] sol = new int[percurso.size()];
        for (int i = 0; i < sol.length; i++) {
            sol[i] = percurso.get(i);
        }
        return sol;
    }
     
     
    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
 
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                if (adj[u][v]!=INVALID) {
                    Edge e = new Edge(u, v,adj[u][v] );
                    edges.add(e);
                }
            }
        }
        return edges;
    }
     
    public static void main(String args[]) {
        GrafoPonderado g = new GrafoPonderado();
        File f = new File("g1.txt");
        System.out.println(f.exists());
        g.carrega(f);
        //g.print(2);
        ShortestPath dij = new ShortestPath(g, 0);
        dij.executa();
        dij.printPath(5);
    }
    public double getW(int i, int j){
        return this.adj[i][j];
    }
}