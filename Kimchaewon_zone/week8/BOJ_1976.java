import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1976{
    public static int[] city;
    public static int[][] map;
    public static boolean[] visited;
    public static int n;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];


        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        city = new int[m];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++){
            city[i] = Integer.parseInt(st.nextToken());
        }

        BFS(city[0] - 1);
        for (int i = 0; i < m; i++){
            if (!visited[city[i] - 1]){
                System.out.print("NO");
                System.exit(0);
            }
        }
        System.out.print("YES");

    }

    public static void BFS(int city){
        visited = new boolean[n];
        visited[city] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(city);

        while (!q.isEmpty()){
            int currCity = q.poll();
            for (int i = 0; i < map[currCity].length; i++){
                if (map[currCity][i] == 1 && !visited[i]){ // visited[i] = true를 해버리면, 다른 경우는 못 보는 거 아닌가? -> 안 봐도 된다.
                                                           // i 도시까지 가는 방법이 있는 것만 확인하면 되기 때문이다. 
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
