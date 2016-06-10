/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafios.dikstra;

/**
 *
 * @author meira
 */
public class Heap {
    GrafoPonderado g;
    No heap[];
    int size;
    //index[v] é a posicao do vertice v no heap.
    int index[];
    public Heap(GrafoPonderado g){
        this.g=g;
        heap = new No[g.getN()];
        index = new int[g.getN()];
        for(int i=0;i<g.getN();i++){
            heap[i] = new No(i);
            index[i]=i;
        }
        size=g.getN();
    }    
    public int extraMin(){
        int min = heap[0].vertex;
        heap[0]=heap[size-1];
        size--;
        heapfyBaixo(0);
        return min;
    }
    public void heapfyBaixo(int pos){
        int fe = filhoE(pos);
        int fd = filhoD(pos);
        int min=pos;
        if(fe<size&&heap[fe].d<heap[min].d){
            min=fe;
        }
        if(fd<size&&heap[fd].d<heap[min].d){
            min=fd;
        }
        if(min!=pos){
            int v1 = heap[min].vertex;
            int v2 = heap[pos].vertex;
            
            int aux2 = index[v1];
            index[v1]=index[v2];
            index[v2]=aux2;
            
            No aux=heap[min];
            heap[min]=heap[pos];
            heap[pos]=aux;
            
            
            
            heapfyBaixo(min);            
        }                
    }
    
    public void decreaseKey(int v, double d){
        int pos = index[v];
        heap[pos].d=d;
        heapfyCima(pos);
    }
    
    public void heapfyCima(int pos){
        if(pos==0){
            return;
        }
        int pai = pai(pos);
        
        if(heap[pai].d>heap[pos].d){
            int v1 = heap[pai].vertex;
            int v2 = heap[pos].vertex;
            
            int aux2 = index[v1];
            index[v1]=index[v2];
            index[v2]=aux2;
            
            No aux=heap[pai];
            heap[pai]=heap[pos];
            heap[pos]=aux;
            
            
            
            heapfyCima(pai);            
        }                
    }
    
    
    int filhoE(int i){
        return 2*i+1;
    }
    int filhoD(int i){
        return 2*i+2;
    }
    int pai(int pos){
        return (pos-1)/2;
        
    }
     public boolean isEmpty(){
        return size==0;
    }
    
}


