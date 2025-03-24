import java.io.*;
import java.util.*;

// 그래프가 주어졌을 때 서로의 엣지를 세는 방법
// 1 -> 2가 1개면, 2 -> 1도 1개라는 거를 잘 이용해보면 연산의 횟수를 줄일 수 있지 않을까?
// 두 달 전에 파이썬으로 원트원클했는데, 지금은 또 모르겠어서 진짜 나한테 질 수는 없다는 마음으로 임했습니다.

public class BOJ_1389 {
    public static int n;
    public static int[][] relations = new int[n + 1][n + 1];
    public static List<List<Integer>> users = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 유저 수
        int m = Integer.parseInt(st.nextToken()); // 관계 수


        for (int i = 0; i <= n; i++){
            users.add(new ArrayList<>()); // 유저의 수는 1부터 n까지 존재하므로
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int user1 = Integer.parseInt(st.nextToken());
            int user2 = Integer.parseInt(st.nextToken());

            users.get(user1).add(user2); // 서로 친구 추가
            users.get(user2).add(user1);
        }

        // users는 제대로 완성됨.

        int result = 0; // 출력할 사람
        int min = 10000000; // 비교대상

        for (int i = 1; i <= n; i++){
            int cnt = bfs(i);
            //System.out.println(result + " " + cnt);
            if (cnt < min){
                min = cnt;
                result = i;
            }
        }
        System.out.println(result);
    }
    static int bfs(int user){
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n + 1];
        int[] distance = new int[n + 1];

        q.add(user);
        visited[user] = 1;

        while (!q.isEmpty()){
            int cur = q.poll();

            for (int next : users.get(cur)){
                if (visited[next] == 0){
                    visited[next] = 1;
                    q.add(next);

                    distance[next] = distance[cur] + 1;
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= n; i++){
            sum += distance[i];
        }
        return sum;
    }
}
