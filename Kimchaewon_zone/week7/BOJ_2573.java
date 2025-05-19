import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

// 4 4
//0 0 0 0
//0 3 1 0
//0 1 3 0
//0 0 0 0

// https://forward-gradually.tistory.com/67

// 메모리도 남들보다 크고, 시간도 오래걸렸다면? -> 더 좋은 방법이 있지 않을까?

class Node{
    int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class BOJ_2573{
    public static int n, m;
    public static int[][] map;
    public static int[][] map_tmp;
    public static int[][] visited;
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        map_tmp = new int[n][m];

        // 맵 입력받기
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                map_tmp[i][j] = map[i][j];
            }
        }

        int year = 0;
        while (true){
            boolean melted = false;

            // 각각 0이 아닌 곳을 돌면서, 동서남북 확인 후에 0의 개수만큼 감소시키기
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    map_tmp[i][j] = map[i][j];
                }
            }
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    if (map_tmp[i][j] == 0)
                        continue;
                    melting(i, j);
                    melted = true;
                }
            }

//            for (int i = 0; i < n; i++){
//                for (int j = 0; j < m; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            if (!melted) {
                System.out.println("0");
                break;
            }
            year++;

            // BFS를 실시해서 몇 개의 덩어리가 있는지 확인하기
            Queue<Node> q = new LinkedList<>();
            visited = new int[n][m];
            int mass = 0;

            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    if (map[i][j] == 0 || visited[i][j] == 1) continue;

                    q.add(new Node(i, j));
                    visited[i][j] = 1;

                    while (!q.isEmpty()){
                        Node cur = q.poll();
                        visited[cur.x][cur.y] = 1;
//                        System.out.println(cur.x + " " + cur.y);
                        for (int k = 0; k < 4; k++){
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];
                            if (visited[nx][ny] == 0 && inRange(nx, ny) && map[nx][ny] != 0) {
                                q.add(new Node(nx, ny));
                                visited[nx][ny] = 1;
                            }
                        }
                    }
                    mass++;
                }
            }

            if (mass >= 2){
                System.out.print(year);
                break;
            }
        }


    }

    public static void melting(int x, int y){
        int cnt = 0;
        for (int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (map_tmp[newX][newY] == 0)
                cnt++;
        }

        if (map_tmp[x][y] - cnt < 0) map[x][y] = 0;
        else map[x][y] = map_tmp[x][y] - cnt;
    }

    public static boolean inRange(int x, int y){
        if (0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }
}
