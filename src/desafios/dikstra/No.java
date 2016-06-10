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
public class No {
    int vertex;
    double d;
    public No(int vertex){
        this.vertex=vertex;
        this.d = Double.MAX_VALUE;
    }
}
