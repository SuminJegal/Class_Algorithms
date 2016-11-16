/**
 * Created by jegalsumin on 2016. 11. 16..
 */
public class Main {

    public static void main(String[] args){

        Graph graph = new Graph(9);
        graph.setVertecies(new char[]{'a','b','c','d','e','f','g','h','i'});
        graph.setEdges('a','b',4);
        graph.setEdges('a','h',8);
        graph.setEdges('b','c',8);
        graph.setEdges('b','h',11);
        graph.setEdges('c','d',7);
        graph.setEdges('c','f',4);
        graph.setEdges('c','i',2);
        graph.setEdges('d','e',9);
        graph.setEdges('d','f',14);
        graph.setEdges('e','f',10);
        graph.setEdges('f','g',2);
        graph.setEdges('g','h',1);
        graph.setEdges('g','i',6);
        graph.setEdges('h','i',7);

        PrimAlgorithm p = new PrimAlgorithm(graph);
        p.getMinimunSpanningTree();
    }
}
