import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.PriorityQueue;
// 결국에는 처음 접근했던 방법은 맞는데, 거기서
// 서로소 집합인 거를 확인해주는 알고리즘을 넣으면 된다!
class computer{
    int a, b, cost;
    public computer(int a, int b, int cost){
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
}

public class BOJ_1922{
    public static int[] parent;
    public static PriorityQueue<computer> pq;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n =  Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        
        pq = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));
        for (int i = 0; i < m; i++){
            st =  new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new computer(a,b,cost));
        }
        parent = new int[n+1];
        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int cost = 0;
        for (int i = 0; i < m; i++){
            computer curr =  pq.poll();
            if (find(curr.a) != find(curr.b)) {
                union(curr.a, curr.b);
                cost += curr.cost;
            }
        }
        System.out.println(cost);

    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }

    private static int find(int x){
        if (parent[x] == x)
            return x;
        else
            return find(parent[x]);
    }
}
