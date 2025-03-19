import java.util.LinkedList;
import java.util.Queue;

// BFS는 큐로 구현한다.
public class BFS {
    public static int[][] arr =  {{}, {2, 3}, {1, 4}, {3, 4}, {2, 3}};
    //            {{}, {2, 3}, {1, 4}, {3, 4}, {2, 3}};  1 -> 2, 3 -> 4 - > 1 의 다이아몬드꼴의 그래프입니다.
    //            {{}, {2, 3}, {1, 4, 5}, {1}, {2}, {1, 2}, {7}, {6}}; 1 -> 2, 3 -> 4, 5 / 6 - 7 의 그래프입니다.
    public static int[] visited = new int[arr.length]; // 서로 연결되어 있을 때, 상위 레벨에서 이미 방문한 경우라면 중복 방문하지 않도록 하기 위해 visited 배열을 사용합니다.

    public static void main(String[] args) {
        for (int i = 1; i < arr.length; i++) {        // 그래프의 모든 노드에 대해 BFS를 수행합니다.
            if (visited[i] == 0){                     // (아직까지 방문하지 않은 노드가 있다면) == (모든 노드가 연결된 그래프가 아니라면)
                bfs(i, arr);                          // 해당 노드를 시작점으로 하는 bfs를 수행합니다.
                System.out.println();
            }
        }
    }

    /*
    * BFS는 한 번에 여러 개의 노드를 탐색하는 탐색 기법입니다.
    * 최대한 빠른 경로를 찾을 때,
    * DFS는 하나의 브랜치의 깊이가 무한대일 때, 성능이 안 좋아지는 반면,
    * BFS는 같은 레벨의 노드를 탐색하기 때문에 DFS보다 빠르게 경로를 찾을 수 있습니다.
    *
    * BFS에서는 일반적으로 Queue를 사용하여 탐색할 노드를 관리합니다. (FIFO 구조)
    * 먼저 탐색할 노드를 큐에 추가하면, 해당 노드가 먼저 처리됩니다.
    * 큐에서 노드(cur)를 꺼낸 후, 그 노드와 연결된 노드들을 탐색하여 큐에 추가합니다.
    * 이를 통해 현재 레벨의 노드를 모두 탐색한 후, 다음 레벨의 노드를 탐색하게 됩니다.
    * 그래프에서 중복 방문을 방지하기 위해 for문에서 visited 여부를 확인합니다. 
    * */
    static void bfs(int index, int[][] arr){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);

        while (!queue.isEmpty()){
//            System.out.println(queue);
            int cur = queue.poll();
            /*
            * 순환 그래프일 때, 예시(1 - 2, 3 - 4)
            * 2와 연결된 4를 넣고, 3과 연결된 4를 queue에 넣기 때문에
            * 방문하지 않은 4가 queue에 2번 들어가게 됩니다.
            * 특수한 경우지만, 레벨에 따라서 순환할 때, 다음 레벨에서 탐색해야 할 노드가 같을 때를 방지하기 위해서 체크합니다.
            */
            if (visited[cur] == 1)
                continue;

            visited[cur] = 1;  // queue에서 꺼내면, 탐색했다고 표시합니다.

            System.out.print("노드 " + cur);
            for (int i = 0; i < arr[cur].length; i++){
                if (visited[arr[cur][i]] == 0){ // 현재 노드의 자식 노드들을 방문하지 않았다면, (아래 레벨의 노드라면)
                    queue.add(arr[cur][i]);     // queue에 넣습니다.
                }
            }
        }
    }
}
