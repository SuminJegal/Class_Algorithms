/**
 * Created by jegalsumin on 2016. 11. 16..
 */
public class Graph {

    char[] vertecies;
    int[][] edges;

    Graph(int numOfVertecies){
        vertecies = new char[numOfVertecies];
        edges = new int[numOfVertecies][numOfVertecies];
        for(int i=0; i<vertecies.length; i++){
            for(int j=0; j<vertecies.length; j++){
                edges[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    //Array 'vertiecies' is always in order.
    public void setVertecies(char[] vertecies){
        this.vertecies = vertecies;
    }

    public void setEdges(char start, char end, int weight) {
        edges[getIndexOfThisVertex(start)][getIndexOfThisVertex(end)] = weight;
        edges[getIndexOfThisVertex(end)][getIndexOfThisVertex(start)] = weight;
    }

    public int getIndexOfThisVertex(char vertex){
        return (int)vertex - (int)(vertecies[0]);
    }
}
