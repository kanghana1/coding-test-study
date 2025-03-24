import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][][] visited = new int[n][m][2];

        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        for (int i = 0; i < n; i++){
            String line = br.readLine();
            for (int j = 0; j < m; j++)
                map[i][j] = line.charAt(j) - '0';
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = 1;

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], broken = cur[2], dist = cur[3];

            if (x == (n - 1) && y == (m - 1)){
                System.out.println(dist + 1);
                return;
            }


            for (int i = 0; i < 4; i++){
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 벽인 경우
                if (map[nx][ny] == 1 && broken == 0 && visited[nx][ny][1] == 0){
                    visited[nx][ny][0] = 1;
                    q.add(new int[]{nx, ny, 1, dist + 1});
                }

                // 빈 공간인 경우
                if (map[nx][ny] == 0 && visited[nx][ny][broken] == 0){
                    visited[nx][ny][broken] = 1;
                    q.add(new int[]{nx, ny, broken, dist + 1});
                }
            }
        }
        System.out.println(-1);
    }
}
