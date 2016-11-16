/**
 * Created by jegalsumin on 2016. 11. 11..
 */
public class PrimAlgorithm {

    Graph graph;
    boolean[] visited;

    public PrimAlgorithm(Graph inputGraph){
        graph = inputGraph;
        visited = new boolean[graph.vertecies.length];
    }

    public void getMinimunSpanningTree(){
        int totalWeightOfMST = 0;
        int currentVisitingVertexIndex;
        String currentPopEdge;
        PriorityQueue priorityQueue = new PriorityQueue();
        currentVisitingVertexIndex = 0;
        System.out.printf("w< ,%c> = %d\n",graph.vertecies[currentVisitingVertexIndex], 0);
        while (visited[currentVisitingVertexIndex]==false) {
            visited[currentVisitingVertexIndex] = true;
            for(int i=0; i<graph.vertecies.length; i++){
                if(graph.edges[currentVisitingVertexIndex][i]<Integer.MAX_VALUE && visited[i]==false){
                    priorityQueue.insert(graph.vertecies[currentVisitingVertexIndex]+""+graph.vertecies[i],
                            graph.edges[currentVisitingVertexIndex][i]);
                }
            }
            while(visited[graph.getIndexOfThisVertex(priorityQueue.min().getValue().charAt(1))]){
                priorityQueue.popMin();
                if(priorityQueue.size()==0){
                    break;
                }
            }
            if(priorityQueue.size()==0){
                break;
            }
            currentPopEdge = priorityQueue.min().getValue();
            totalWeightOfMST += priorityQueue.min().getKey();
            System.out.printf("w<%c,%c> = %d\n",currentPopEdge.charAt(0),currentPopEdge.charAt(1),priorityQueue.popMin().getKey());
            currentVisitingVertexIndex = graph.getIndexOfThisVertex(currentPopEdge.charAt(1));
        }
        System.out.println();
        System.out.println("w<MST> = "+totalWeightOfMST);

    }
}
