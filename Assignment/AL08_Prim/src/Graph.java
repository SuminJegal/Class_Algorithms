/**
 * Created by jegalsumin on 2016. 11. 16..
 */
public class Graph {

    String[] vertecies;
    Edge[] edges;

    Graph(int numOfVerteciex, int numOfEdges){
        vertecies = new String[numOfVerteciex];
        edges = new Edge[numOfEdges];
    }

    public void setVertecies(String[] )

    class Edge{
        String startVertex;
        String endVertex;
        int weight;

        Edge(String startVertex, String endVertex, int weight){
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }
    }
}
