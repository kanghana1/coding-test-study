import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 최솟값이 갱신될 때를 반영.
// BFS랑 다른 점이 없는 것 같다? 가중치가 1로 같기 때문에 유사한 것임.

public class BOJ_18352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n, m, k, x를 입력 받습니다.
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        // 그래프를 생성합니다.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        // 도시 간의 관계를 이어줍니다.
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
        }

        // 최소힙으로 거리를 계산하다가, cost가 k인 도시가 나오면 cnt += 1
        // cost가 k 이상이면 탐색 종료
        // -> 이렇게 되면 중간에 갱신되는 값을 포함시키지 못함.
        // -> 예를 들어 1-4 의 거리가 4가 되어서 종료시켰는데, 최단거리가 3일 수도 있음.
        // -> 이 점을 반영하려면은 visited만 확인하는 게 아니라, distance를 계속해서 갱신해줘야 함.
        // -> visited 배열을 distance 배열로 수정한 후에 최단 경로를 사용하도록 함.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[x] = 0;
        minHeap.add(new int[]{x, 0});

        while (!minHeap.isEmpty()){
            int[] cur = minHeap.poll();
            int node = cur[0], cost = cur[1];

            if (distance[node] < cost) continue; // 해당 노드까지의 거리가 이미 최단 거리라면, 해당 노드로 가는 경로는 더 이상 탐색하지 않습니다.

            // 갱신될 여부를 판단합니다.
            for (int next : graph.get(node)){
                if (distance[next] > cost + 1){ // 기존의 방법이 현재의 cur 도시를 경유해서 건너가는 것보다 길다면,
                    distance[next] = cost + 1;  // 현재의 cur도시를 경유하여 가는 것으로 최단 경로를 수정합니다.
                    minHeap.add(new int[]{next, cost + 1});
                }
            }
        }
        boolean found = false;
        for (int i = 1; i <= n; i++)
        {
            if (distance[i] == k) {
                found = true;
                System.out.println(i);
            }
        }
        if (!found) System.out.println(-1);

    }
}
