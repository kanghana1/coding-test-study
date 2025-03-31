import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// 자바에서는 최소힙과 최대힙을 우선순위큐를 이용하여 구현합니다.
// PriorityQueue의 기본은 최소힙이기 때문에, Comparator.reverseOrder() 를 통해서 그 안의 비교해주는 값을 거꾸로 하였습니다.
// Comparator의 compare 메소드는 (o1, o2) 두 요소를 비교하여
// - 음수를 반환하면 o1이 o2보다 우선순위가 높은 것이고
// - 양수를 반환하면 o2가 o1보다 우선순위가 높은 것입니다.
// 이를 람다식으로도 표현할 수 있습니다.
// (ex) PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1)) o2가 o1보다 커서 양수가 반환되면 o2가 더 높은 우선순위가 됩니다.

public class BOJ_11279 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int n = sc.nextInt();

        for (int i = 0; i < n; i++){
            int x = sc.nextInt();
            if (x == 0 && pq.isEmpty())
                System.out.println(0);
            else if (x == 0 & !pq.isEmpty())
                System.out.println(pq.poll());
            else
                pq.add(x);

        }
    }
}
