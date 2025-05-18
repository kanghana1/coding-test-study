// 할인이든 뭐든, 연속된 k개에서 무료 쿠폰을 쓸 수 있는지 보면 되겠다
// 처음에 문제를 잘못 이해해서, 연속된 k를 무조건 먹을 때, 연속된 k가 모두 다른 종류여야 할인받을 수 있다고 생각했음.
// -> 그래서 투포인터와 비슷하게 이중 반복문을 사용했었는데, 다시 보니 연속된 k는 아무거나 먹을 수 있는 것이었음.

import java.util.Scanner;

public class BOJ_2531 {
    public static int[] check = new int[3001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int d = sc.nextInt();
        int k = sc.nextInt();
        int c = sc.nextInt();

        int[] arr = new int[N]; // 초밥

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        // 무조건 4개를 먹게끔?
        int cnt = 0;
        int ans = 0;
        for (int i = 0; i < k; i++){
            // 초기 4개 접시에서 개수
            check[arr[i]]++;
            if (check[arr[i]] == 1)
                cnt++;
        }

        if (check[c] == 0)
            ans = cnt + 1;
        else
            ans = cnt;

        int head = 0;
        int tail = k;

        while (head < N){
            check[arr[tail]]++;
            if (check[arr[tail]] == 1)
                cnt++;

            check[arr[head]]--;
            if (check[arr[head]] == 0)
                cnt--;

            int total = cnt;
            if (check[c] == 0)
                total = cnt + 1;

            ans = Math.max(ans, total);

            head++;
            tail = (tail + 1) % N;
        }
        System.out.println(ans);
    }

}
