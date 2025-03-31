import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
/*
문제를 보고 처음 들었던 생각: 
BFS로 풀 수 있다고 생각하였습니다.
여기에 cost가 작은 순으로 queue를 정렬하면, 도착 지점에서의 cost가 가장 작을 것이라고 생각하였습니다.

Black을 어떻게 처리할지가 고민이었는데,
이전에 풀었던 "벽 부수고 이동하기"와 같은 문제처럼, 벽을 부순 여부에 따라 3차원 배열을 사용해야 하나? 라는 생각이 들었습니다.
하지만, 이 문제에서는 단순히 "검은 방을 몇 번 흰 방으로 바꿔야 하는지"만 확인하면 되기 때문에,
검은 방을 지난 횟수(=cost)만 잘 관리하면 충분하다고 판단했습니다.

--- 

문제 1 : 메모리 초과 발생
-> visited 배열을 단순 방문 여부를 확인하는 용도로 쓰다보니, 
cost가 최소가 아닌 경우에도 계속해서 pq에 담게 되었고, 그 결과 메모리 초과가 발생하였습니다.

해결 1 : visited 배열에 현재까지 밟은 검은색의 최소 개수를 넣어주었습니다.
새로운 위치로 이동할 때, 현재까지의 cost가 visited에 기록된 값보다 작을 경우에만 갱신하고 큐에 넣습니다.  
이렇게 하면 visited여부를 확인함과 동시에, 최소한의 cost만을 유지하는 계산을 할 수 있으므로, 메모리 초과를 방지할 수 있다고 생각합니다. 

---

문제 2: NullPointer Error
-> while문을 벗어나서 print문에서 pq.poll()[2](=cost) 를 실행하여서 오류가 났었습니다.
지금까지는 제대로 출력이 되어서 이상한 점을 느끼지 못했었는데, 이는 운이 좋았던 것이었습니다.

테스트 케이스를 추측해보자면, ㅁㅁㅁㅁㅁㅁ 처럼 한 줄로 되어 있을 때, 
마지막으로 도착하는 지점에서 pq.poll을 시행하고 반복문을 벗어나면, pq가 비어있을 것입니다.
그런 상황에서 pq.poll을 통해 출력하면, NullPointerError가 발생할 것입니다.

해결 2 : 도착 지점에 도달했을 때, break 대신 바로 cost를 출력하고 return하도록 수정했습니다.  
*/
public class BOJ_2665 {
    public static void main(String[] args) throws IOException {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();

        int n = Integer.parseInt(st);
        int[][] map = new int[n][n];

        // 맵 만들기
        for (int i = 0; i < n; i++){
            st = br.readLine();
            for (int j = 0; j < n; j++)
                map[i][j] = st.charAt(j) - '0';

        }

        // PriorityQueue
        // x, y, cost(black을 몇 번 지나왔는지)로 넣습니다. 
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]); // cost가 작은 순대로 pq를 정렬합니다. (도착지점에 도달했을 때, cost가 최소임을 보장합니다.)
        int[][] visited = new int[n][n]; // cost를 저장할 배열 
        for (int i = 0; i < n; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);

        // 초기 위치, 방의 색에 따라 cost를 다르게 설정합니다. 
        if (map[0][0] == 1)
            pq.add(new int[]{0, 0, 0});
        else
            pq.add(new int[]{0, 0, 1});
        visited[0][0] = 0;

        // 모든 위치를 탐색합니다.
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], cost = cur[2];
          
            if (x == (n - 1) && y == (n - 1)) { // 도착지점에 도달했을 때, 해당 cost를 출력하고 함수를 종료합니다. 
                System.out.println(cost);
                break;
            }

            if (cost > visited[x][y]) continue; // 만약 현재 위치에서의 cost가 이미 저장된 최소 cost보다 크다면, 더 이상 해당 경로를 이용하여 탐색하지 않습니다.

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i], ny  = y + dy[i];
                int nextCost = cost;
                if (nx >= 0 && nx < n && ny >= 0 && ny < n){ // 다음 위치가 map의 범위 안에 있을 때, 
                    if (map[nx][ny] == 0)
                        nextCost = cost + 1;
                    if (nextCost < visited[nx][ny]) { // 다음 위치로 가는 경로의 cost가 이미 저장된 최소 cost보다도 작다면, 
                        visited[nx][ny] = nextCost;   // 해당 위치의 cost를 변경하여 주고,
                        pq.add(new int[]{nx, ny, nextCost});  // 해당 경로를 이용한 탐색을 계속 실시합니다. 
                    }
                }
            }


        }
    }
}
