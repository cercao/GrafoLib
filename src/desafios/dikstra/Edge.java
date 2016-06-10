package desafios.dikstra;
 
public class Edge implements Comparable<Edge>{
     
    /** vertex 1*/
    private int v1;
    /** vertex 2 */
    private int v2;
    /** edge weight */
    private double weight;
     
    public Edge(int v1, int v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
     
    public int getV1() {
        return v1;
    }
     
    public void setV1(int v1) {
        this.v1 = v1;
    }
     
    public int getV2() {
        return v2;
    }
     
    public void setV22(int v2) {
        this.v2 = v2;
    }
     
    public double getWeight() {
        return weight;
    }
     
    public void setWeigth(double weigth) {
        this.weight = weigth;
    }
     
    @Override
    public String toString() {
        return "(" + v1 + "," + v2+ " )[" + weight + "]"; 
    }
 
    @Override
    public int compareTo(Edge o) {
       if(this.weight==o.weight){
           return 0;
       }
       if(this.weight<o.weight){
           return -1;
       }else{
           return 1;
       }
    }        
}