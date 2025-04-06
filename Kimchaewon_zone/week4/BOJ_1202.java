import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 초안 
// 처음에는 가치 / 무게를 고집 -> 생각해보니 fractional 아님
// bag의 무게보다 작은 것 중에서 가치가 높은 것을 뽑으면 되겠다.
// result에서 최대 무게 Ci의 범위 때문에 Long형으로 변경함.

public class BOJ_1202{
    public static int n;
    public static List<int[]> jewels = new ArrayList<>();
    public static boolean[] used;
    public static int idx = 0;
    static PriorityQueue<Integer> pq;


    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        used = new boolean[n];
        pq =  new PriorityQueue<>((o1, o2) -> Integer.compare(o2 , o1));
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v =  Integer.parseInt(st.nextToken());

            jewels.add(new int[]{m,v});
        }

        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < k; i++){
            st =  new StringTokenizer(br.readLine());
            int c =  Integer.parseInt(st.nextToken());
            bags.add(c);
        }

        Collections.sort(jewels, Comparator.comparingInt(o -> o[0])); // 무게 낮은 순으로 보기
        Collections.sort(bags); // 가방의 무게가 작은 것부터 넣기

        Long result = 0L;
        for (int bag : bags){
            result += findJewel(bag);
        }
        System.out.println(result);
    }

    public static int findJewel(int c){
        // 가방의 무게보다 작거나 같은 보석 중에, 가치가 제일 높은 것을 뽑으면 된다.
        while (idx < n && jewels.get(idx)[0] <= c){
            pq.add(jewels.get(idx)[1]);
            idx++;
        }
        // 이전에 탐색했던 것이 만일 가치가 높다면 우선순위 큐에 의해서 제일 위에 있을 것이다.

        if (!pq.isEmpty())
            return pq.poll();

        return 0;
    }
}
