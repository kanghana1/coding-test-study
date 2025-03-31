import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// x좌표, y좌표가 헷갈림.
// 왜 모든 방향을 모든 지점에서 고려해야 하는지가 의문임.
// 모든 방향의 배열을 고려해서 3차원을 만드는 것도 이해가 잘 안됨.
// 이해 되는 듯하면서도 이전 논리가 왜 안맞는지, 오류가 나는지 헷갈림.

// 대신 방향을 바로 고려했던 점이 스스로 생각하기에 발전한 부분인 거 같음

public class BOJ_6087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken()); // 가로
        int h = Integer.parseInt(st.nextToken()); // 세로

        // dir = 0, 1, 2, 3
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        char[][] arr = new char[h][w];
        int[][] idx = {{0, 0}, {0, 0}};  // (y, x)
        int cnt = 0;

        for (int i = 0; i < h; i++){
            String input = br.readLine();
            for (int j = 0; j < w; j++){
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == 'C') {
                    idx[cnt][0] = i; // y좌표
                    idx[cnt][1] = j; // x좌표
                    cnt += 1;
                }
            }
        }

        int start_y = idx[0][0], start_x = idx[0][1];
        int end_y = idx[1][0], end_x = idx[1][1];

        // y좌표, x좌표, 거울 개수, 현재 방향
        // 현재 방향과 앞으로 탐색할 방향의 위치가 다르다면 거울 개수를 추가한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        int[][][] mirrors = new int[h][w][4]; // 해당 위치에서의 최소 거울 개수

        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                Arrays.fill(mirrors[i][j], Integer.MAX_VALUE);
        for (int d = 0; d < 4; d++){
            mirrors[start_y][start_x][d] = 0;
            pq.add(new int[]{start_y, start_x, 0, d});
        }

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0], x = cur[1], mirror = cur[2], dir = cur[3];

            for (int k = 0; k < 4; k++){
                mirror = cur[2];
                int ny = y + dy[k], nx = x + dx[k];
                if (ny >= 0 && ny < h && nx >= 0 && nx < w && arr[ny][nx] != '*'){
                    if (dir != k) mirror += 1;
                    if (mirror < mirrors[ny][nx][k]){
                        mirrors[ny][nx][k] = mirror;
                        pq.add(new int[]{ny, nx, mirror, k});
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++)
            ans = Math.min(ans, mirrors[end_y][end_x][d]);

        System.out.println(ans);
    }
}
