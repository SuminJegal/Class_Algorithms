/**
 * Created by jegalsumin on 2016. 11. 7..
 */
public class Main {

    public static void main(String[] args){

        Dijkstra d = new Dijkstra();
        d.setVertex(new char[]{'A','B','C','D','E'});
        d.setNumOfEdge(9);
        d.setEdges('A','B',10);
        d.setEdges('A','C',3);
        d.setEdges('B','C',1);
        d.setEdges('B','D',2);
        d.setEdges('C','B',4);
        d.setEdges('C','D',8);
        d.setEdges('C','E',2);
        d.setEdges('D','E',7);
        d.setEdges('E','D',9);
    }
}
