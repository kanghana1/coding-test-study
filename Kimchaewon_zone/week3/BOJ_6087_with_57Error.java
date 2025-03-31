import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// x좌표, y좌표가 헷갈림.
// 57% 에서 틀림 -> 일단 시간, 공간 복잡도는 괜찮은 듯.
// -1을 빼는 로직이 틀린 거 같음.
public class BOJ_6087_with_57Error {
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
        ArrayList<Integer> prints = new ArrayList<>();

        // y좌표, x좌표, 거울 개수, 현재 방향
        // 현재 방향과 앞으로 탐색할 방향의 위치가 다르다면 거울 개수를 추가한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        int[][] mirrors = new int[h][w]; // 해당 위치에서의 최소 거울 개수

        for (int i = 0; i < h; i++)
            Arrays.fill(mirrors[i], Integer.MAX_VALUE);

        pq.add(new int[]{start_y, start_x, 0, -1});
        cnt = 0;

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int y = cur[0], x = cur[1], mirror = cur[2], dir = cur[3];
//            System.out.println("y좌표 : " + y + "," + "x좌표 : " + x + ", 방향 : " + dir + "," + mirror);

            if (y == end_y && x == end_x){
                prints.add(mirror);
            }

            for (int k = 0; k < 4; k++){
                mirror = cur[2];
                int ny = y + dy[k], nx = x + dx[k];
                if (ny >= 0 && ny < h && nx >= 0 && nx < w){
                    if (arr[ny][nx] == '*') continue;
                    if (dir != -1 && dir != k) mirror += 1;
                    if (mirror <= mirrors[ny][nx]){
                        mirrors[ny][nx] = mirror;
                        pq.add(new int[]{ny, nx, mirror, k});
                    }
                }
            }
        }
        int minimum = Integer.MAX_VALUE;
        for (int elem : prints){
            minimum = Math.min(elem, minimum);
        }
        System.out.println(minimum);
    }
}
