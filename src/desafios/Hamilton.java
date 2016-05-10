package desafios;

import java.util.HashMap;
import java.util.Map;

public class Hamilton {
    int verticesNum;
    char[] vertices;
    char[] path;
    int size;
    Map<Character,Integer> vertexIndexMap = new HashMap<>();
    public Hamilton(int verticesNum) {
        this.verticesNum = verticesNum;
        vertices = new char[verticesNum];
        path = new char[verticesNum];
    }

    public void addVertex(char v) {
        vertexIndexMap.put(v, size);
        vertices[size++] = v;
    }

    private boolean isValidNodeInPath(int [][] graph, int indexOfNewVerex ,int pos) {
        char lastVertexInPath = path[pos-1];
        int indexOfLastVertexInPath = vertexIndexMap.get(lastVertexInPath);
        // check if the new vertex is adjacent to the previously added vertex in the path]
        if(graph[indexOfNewVerex][indexOfLastVertexInPath] == 0){
            return false;
        }
        // check if the new vertex has not already been added in the path
        for(int i = 0; i < pos; i++){ 
            if(vertices[indexOfNewVerex] == path[i]){
                return false;
            }
        }
        return true;
    }

    private boolean validateHamiltonianCycleUtil(int [][] graph, int pos){
        // base condition
        if(pos == verticesNum){
            char lastVertexInPath = path[pos-1];
            int indexOfVertexlastInPath = vertexIndexMap.get(lastVertexInPath);
            if(graph[0][indexOfVertexlastInPath] == 1){
                return true;
            }
            else {
                return false;
            }
        }
        // here we try to add v in the path at position pos
        // we start from 1 as 0th vertex from the array vertices is already in the path as source node
        for(int i = 1; i < verticesNum; i++){
            if(isValidNodeInPath(graph,i,pos)){
                path[pos] = vertices[i];// include new node in the path
                if(validateHamiltonianCycleUtil(graph, pos+1)){
                    return true;
                }
                path[pos] = '$';// reset the position and try to put another node at this position.
            }
        }
        // no valid vertex found to be added in the path, and all nodes still not covered ,return false
        return false;
    }

    public boolean isHamiltonianCycle(int[][] graph) {
        for (int i = 0; i < verticesNum; i++) {
            path[i] = '$';
        }
        path[0] = vertices[0]; // vertices[0] is included as a source node of
                                // the cycle
        if (validateHamiltonianCycleUtil(graph, 1)) {
            
            System.out.println("Path exists::");
            for(int i = 0 ; i < verticesNum; i++){
                System.out.print(path[i]+" ");
            }
            System.out.print(path[0]);
            return true;
        }
        System.out.println("No path exists!!");
        return false;
    }
    
    public static void main(String[] args) {
        Hamilton hamiltonianCycle = new Hamilton(5);
        hamiltonianCycle.addVertex('a');
        hamiltonianCycle.addVertex('b');
        hamiltonianCycle.addVertex('c');
        hamiltonianCycle.addVertex('d');
        hamiltonianCycle.addVertex('e');
        /* Let us create the following graph
        (a)--(b)--(c)
         |   / \   |
         |  /   \  |
         | /     \ |
        (d)-------(e)    */
        int graph[][] = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
            };
        hamiltonianCycle.isHamiltonianCycle(graph);
    }
}