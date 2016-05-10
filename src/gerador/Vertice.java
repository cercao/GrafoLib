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
public class Vertice {
    
    int id;
    int x,y;
    public Vertice(int x, int y,int id){
        this.x=x;
        this.y=y;
        this.id=id;
    }
    public String toString(){
        return "("+x+","+y+")"+"["+id+"]";
    }
    
}
