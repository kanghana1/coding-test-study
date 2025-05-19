import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 나름 자바를 자바 처럼 쓰기 위해서 int[] 안쓰고, 객체를 저장
class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class BOJ_16234 {
    public static int n, l, r;
    public static int[][] map;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static boolean[][] visited;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        int day = 0;

        // 맵 만들기
        map = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){ // 하루 단위로 국경선이 열리는지를 판단
            boolean moved = false;
            visited = new boolean[n][n];

            // 처음 시작 위치 (0, 0)서부터 기준점을 잡기.
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (visited[i][j]) continue; // 이미 탐색한 곳 외에 또 같은 날에 국경선이 열리는 지점이 있는지를 찾기 위해서 해당 조건 필요

                    // 좌표 저장 배열
                    ArrayList<Node> points = new ArrayList<>();

                    // BFS 용 큐
                    Queue<Node> q = new LinkedList<>();

                    // 현재 위치가 가능하다면 아래의 BFS를 정상적으로 수행할 것이고,
                    // 현재 위치가 불가능하다면, for문을 통해서 시작점을 다시 구한다.
                    q.add(new Node(i, j));
                    points.add(new Node(i, j));
                    visited[i][j] = true;
                    int sum = map[i][j];

                    while (!q.isEmpty()){
                        Node cur = q.poll();
                        for (int d = 0; d < 4; d++){
                            int nx = cur.x + dx[d];
                            int ny = cur.y + dy[d];
                            Node next = new Node(nx, ny);

                            if (!inRange(nx, ny) || visited[nx][ny]){ // 방문한 적이 있거나, 맵을 벗어나면,
                                continue;
                            }

                            if (isOpen(cur, next)){ // 국경선이 열리는 조건에 부합하면, 계속 탐색.
                                visited[nx][ny] = true;
                                q.add(next);
                                points.add(next);
                                sum += map[nx][ny];
                            }
                        }
                    }

                    if (points.size() > 1){ // 본인 이외에 다른 국가도 가능하면, moved = true
                        moved = true;
                        int avg = sum / points.size();
                        for (Node node : points){
                            map[node.x][node.y] = avg;
                        }
                    }
                }
            }
            if (!moved)
                break;
            day++;

        }
        System.out.print(day);

    }
    public static boolean inRange(int x, int y){
        if (x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
    public static boolean isOpen(Node curr, Node next){
        int diff = Math.abs(map[curr.x][curr.y] - map[next.x][next.y]);
        if (l <= diff && diff <= r)
            return true;
        return false;
    }
}
