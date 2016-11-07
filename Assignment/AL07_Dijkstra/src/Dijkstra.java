/**
 * Created by jegalsumin on 2016. 11. 7..
 */
public class Dijkstra {

    private char[] vertex;
    private Edge[] edges;
    private int setEdge;

    public void setVertex(char[] vertecies){
        vertex = vertecies;
    }

    public void setNumOfEdge(int numOfEdge){
        edges = new Edge[numOfEdge];
    }

    public void setEdges(char start, char end, int distance){
        if(setEdge >= edges.length){
            System.out.printf("You already set all of edges!");
        }
        edges[setEdge]= new Edge(start, end, distance);
        setEdge++;
    }



    class Edge {
        char startPoint;
        char endPoint;
        int distance;

        Edge(char startPoint, char endPoint, int distance){
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.distance = distance;
        }
    }
}
