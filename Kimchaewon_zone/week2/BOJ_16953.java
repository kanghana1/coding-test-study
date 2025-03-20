import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* 문제를 보고 처음 든 생각 : 조건에 맞는 수를 Queue에 계속 넣고 하나씩 빼서 B가 되는지 아닌지를 확인하면 되겠다고 생각하였습니다.
* BFS를 이용하면 금방 풀 것이라고 생각하였습니다.

* 문제의 로직을 짜는 것 자체는 어렵지 않았는데, 4%에서 오류가 발생하였습니다.
* 분명 로직에는 전혀 문제가 없다고 생각해서 질문 게시판을 찾아보니,
* 너무 큰 수가 들어왔을 때(int의 범위를 범어서는), 계산을 실시하면 int의 범위를 넘어가서 제대로 된 값이 안나온다는 것이었습니다.
* 그래서 Long형으로 바꿔보았습니다. (Long타입은 범위가 매우 크기 때문입니다. -2^63 ~ 2 ^ 63 - 1)

* 그럼에도 불구하고 "틀렸습니다"라고 떴습니다. 이유가 무엇인지 잘 모르겠어서 구글에 "Long형 비교" 에 대해서 검색해보았습니다.
* 찾아보니, Long형은 == 으로 비교했을 때 문제가 난다고 합니다. (-128 ~ 127)까지는 괜찮은데 그 이상을 넘어가면 비교하는 데 문제가 발생한다고 합니다.
* 왜냐하면 Long형은 결국 Wrapper type이니까, 결국에는 주솟값을 저장하고 있는 객체이기 때문입니다.
* 그래서 equals를 사용하는 것이 안전한 방법이라고 하여 코드를 수정하여 해결하였습니다.
*
* 테스트 케이스가 2 162인데, A가 162가 되어도 A == B라는 조건에 걸리지 않는 점이 이상하다고 생각했는데,
* 위의 이유 때문임을 덕분에 알았습니다.
*
* 추가적으로 파이썬에서는 리스트에 튜플을 넣어서 사용하면 되었었는데, 자바에서는 Queue<int[]> 이런 식으로 리스트를 넣어 사용할 수 있는 점이 신기하였습니다.
*/
public class BOJ_16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long A = sc.nextLong();
        Long B = sc.nextLong();      // B의 범위를 고려하여 Long타입으로 선언하였습니다.

        Long cnt = XWantToBeY(A, B); // A가 B가 되는 가장 작은 cnt를 구하도록 합니다.
        System.out.println(cnt + 1);
    }
    static Long XWantToBeY(Long A, Long B){
        Queue<Long[]> queue = new LinkedList<>(); // BFS를 위한 Queue를 생성합니다.
        queue.add(new Long[] {A, 0L});            // (현재 숫자, 현재 연산 횟수) 로 넣습니다.

        while (queue.isEmpty() == false) {
            Long[] pair = queue.poll();           // 큐의 맨 처음 숫자를 확인합니다.
            Long cur = pair[0], cnt = pair[1];
//            System.out.println(cur + " " + cnt);

            if (cur.equals(B)) return cnt;        // 현재 숫자가 B라면 그에 해당하는 연산 횟수를 반환합니다.
            if (cur > B) continue;                // 현재 숫자가 B보다 크다면 다음 연산으로 넘어가지 않고, Queue에서 빼내는 연산만 합니다.

            if (cur * 2 <= B) queue.add(new Long []{cur * 2, cnt + 1}); // 현재의 값을 두 배 해서 비교합니다.
            if (cur * 10 + 1 <= B) queue.add(new Long []{cur * 10 + 1, cnt + 1});  // 현재의 값의 오른쪽에 1을 추가해서 비교합니다. 

        }
        return -2L; // Queue에 남아있는 게 없는데, B와 같지 않다면, -2를 출력합니다.(메인 코드에서 +1을 공통적으로 해서 출력하기 때문에)
    }

}
