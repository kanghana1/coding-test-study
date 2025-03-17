import java.io.*;
import java.util.*;

/*
* 문제 보고 처음 든 생각 : DFS를 이용해서 갈 수 있는 방향이 하나라도 있으면은 true를 반환하면 되겠다는 생각이 들었습니다.
*
* 고민 :
* 오른쪽과 아래 방향으로만 이동할 수 있는데, 현재 위치에서 이동할 수 있는 거리만큼 한 번에 어떻게 이동할 수 있을까?
*
* 잘못된 접근 :
* 처음에는 한 번에 2칸 갈 수 있을 때, 가능한 위치가 (0, 2), (2, 0), (1, 1)라고 생각했었습니다.
* 이렇게 움직이는 줄 알고 코드를 짰는데, 틀렸습니다.
* 실제로는 한번에 한 방향(아래, 오른쪽)으로만 갈 수 있었던 것이었습니다.

* 해결 방법 :
* 'nx = x + d[0] * move' 로 수정하여서 한번에 바로 움직일 수 있도록 하였습니다.

* 문제 해결 과정
* 1. 85% 메모리 초과 :
*  - 방문한 곳 체크하기를 하지 않아서 중복 탐색을 하여 DFS가 깊어져서 메모리 초과가 난 것 같습니다.
*  - 한번 방문한 자리는 그쪽으로는 갈 수 없었거나, 갈 수 있거나를 정리한 것이기 때문에 방문한 곳을 체크해야 했습니다.
*  -> visited 추가하여 방문한 곳을 다시 탐색하지 않도록 수정 -> 성공

* DFS에서 주의할 점 :
* 자꾸 헷갈리는 부분입니다.
* DFS에서 더 탐색할 때, 이미 그 곳을 방문해서 true를 반환하지 않았다면, 더 이상 그 경로로는 더 이상 탐색할 필요가 없습니다.
*/
public class Main{
    public static int[][] map;
    public static int n;
    public static int[][] directions = {{0, 1}, {1, 0}};
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 맵의 크기

        // 맵 선언.
        map = new int[n][n];
        // 각 맵을 방문했는지 확인하기 위해 배열 선언.
        visited = new boolean[n][n];

        // 맵 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 탐색
        boolean isTrue = dfs(0, 0, map[0][0]);
        if (isTrue) System.out.println("HaruHaru");
        else System.out.println("Hing");


    }

    // 0, 0에서부터 갈 수 있는 경로를 탐색합니다.
    private static boolean dfs(int x, int y, int move){
        if (x < 0 || y < 0 || x >= n || y >= n) return false;
        if (map[x][y] == -1) // 해당 경로의 도착점이 목표지점(-1)이라면,
            return true;     // true를 반환합니다.
        if (visited[x][y])   // 이미 방문한 곳이라면, (만약 방문했었는데, true로 반환되었더라면, 함수가 끝났을 것이므로)
            return false;    // false를 반환합니다.

        visited[x][y] = true; // 방문 처리

        for (int[] d : directions){ // 오른쪽과 아래 방향으로 이동
            int nx = x + d[0] * move;
            int ny = y + d[1] * move;

            // 다음 위치가 범위 내에 있을 때만 탐색합니다.
            if ((0 <= nx && nx < n) && (0 <= ny && ny < n))
                /*
                * 문제점 :
                * - 'dfs(nx, ny, map[nx][ny)' 만 호출하면, 재귀 호출이 계속 반복되면서 'false'만 반환되었습니다.
                * - 즉, 목표 지점(-1)에 도달할 수 있는 경로를 찾더라도, 이를 활용하지 못하였습니다.
                *
                * 해결 방법:
                * - 'if(dfs(nx, ny, map[nx][ny)) return true' 형태로 수정하여,
                *   하나의 경로라도 목표 지점에 도달하면 즉시 탐색을 종료하고 'true'를 반환하도록 변경하였습니다.
                *
                * 느낀점 :
                * - 재귀 함수에서 반환 값을 활용하는 방식이 아직 익숙하지 않습니다.
                * - DFS에서 'true'가 반환되는 순간 탐색을 멈추는 원리를 명확히 이해해야 한다고 느꼈습니다.
                */
                if (dfs(nx, ny, map[nx][ny]))
                    return true;
        }

        // 모든 경우를 탐색했지만 도달 불가능한 경우 false를 반환합니다.
        return false;
    }
}
