import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_2217 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < n; i++) {
            pq.add(sc.nextInt());
        }
        // 가장 무게가 높은 로프부터 선택하면
        // 본인 외에는 앞에 있는 로프가 없기 때문에
        // 본인이 들 수 있는 최대 * 1개가 된다.
        
        // 즉, 뒤로 갈수록 점점 가벼워져서
        // 본인이 들 수 있는 최대 무게 * (본인 앞에 있는 로프의 개수)가 된다.
        // 이 과정에서의 최댓값을 고르면 된다. 
        
        // 여기서 그리디를 사용한 부분은 
        // -> 일단 무게가 높은 것부터 선택하면 
        // -> 본인 외에는 다른 사람은 들 수가 없다는 논리를 적용한 부분이다. 
        int ans = 0;
        for (int i = 0; i < n; i++){
            ans = Math.max(ans, pq.poll() * (i + 1));
        }
        System.out.println(ans);
    }
}
