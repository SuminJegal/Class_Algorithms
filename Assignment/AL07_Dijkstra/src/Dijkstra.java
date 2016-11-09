/**
 * Created by jegalsumin on 2016. 11. 7..
 */
public class Dijkstra {

    private String[] vertex;
    private int[][] edges;
    private int[] distance;
    private int[] visit;

    public void setVertexAndInitialize(String[] vertecies){
        vertex = vertecies;
        edges = new int[vertecies.length][vertecies.length];
        distance = new int[vertex.length];
        visit = new int[vertex.length];
        for(int i=0; i<vertex.length; i++){
            for(int j=0; j<vertex.length; j++){
                edges[i][j] = Integer.MAX_VALUE;
            }
            distance[i] = Integer.MAX_VALUE;
        }
    }

    public void setEdges(String start, String end, int distance){
        edges[index(start)][index(end)] = distance;
    }

    public void setStart(String wantToStart){
        distance[index(wantToStart)] = 0;
    }

    public void getMinimunDistanceByDijkstra(){
        int sCount = 0;
        System.out.println("Dijkstra's Algorithm");
        PriorityQueue priorityQueue = new PriorityQueue();
        for(int i=0; i<vertex.length; i++){
            priorityQueue.insert(vertex[i],distance[i]);
        }
        while(priorityQueue.size()>0){
            System.out.println("=====================================================");
            String currentVisitVertex = priorityQueue.min().getValue();
            distance[index(currentVisitVertex)] = priorityQueue.popMin().getKey();
            System.out.printf("S[%d] : d[%S] = %d\n",sCount++, currentVisitVertex, distance[index(currentVisitVertex)]);
            System.out.println("-----------------------------------------------------");
            visit[index(currentVisitVertex)] = 1;
            for(int i=0; i<vertex.length; i++){
                if (visit[i] == 0) {
                    int qCount = 0;
                    System.out.printf("Q[%d] : d[%S] = %10d  ",qCount++, vertex[i], distance[i]);
                    int mayChangableValue = edges[index(currentVisitVertex)][i]+distance[index(currentVisitVertex)];
                    if(mayChangableValue>0 && mayChangableValue<distance[i]){
                        priorityQueue.modifyKey(vertex[i], mayChangableValue);
                        distance[i] = mayChangableValue;
                        System.out.printf("->  d[%S] = %d",vertex[i],mayChangableValue);
                    }
                    System.out.println();
                }
            }
            System.out.println();
        }

    }


    private int index(String input){
        for(int i=0; i<vertex.length; i++){
            if(vertex[i].equals(input)) return i;
        }
        return vertex.length;
    }

}
