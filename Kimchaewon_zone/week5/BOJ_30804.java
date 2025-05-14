import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
* 10
* 7 5 5 2 4 2 2 5 5 5
* ans : 5
* output : 4
*/

public class BOJ_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 과일의 개수
        int[] fruit = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            fruit[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        int head = 0; // 첫번째 원소를 가리킨다. -> 현재 범위에서 과일의 개수가 3종류 이상이면 head++;
        int tail = 0; // 현재 범위에서 과일의 개수가 2종류 이하이면 tail++;

        int[] countArr = new int[10];
        countArr[fruit[tail]]++;
        while (tail < n && head <= tail){
            // tail이 가리키는 원소를 여기서 더해버려서 중복으로 더해지는 경우가 발생했었음.
            int sum = 0;
            for (int i = 1; i <= 9; i++){
                if (countArr[i] != 0)
                    sum++;
            }

            if (sum <= 2){
                ans = Math.max(ans, tail - head + 1);
                tail++;

                if (tail < n)
                    countArr[fruit[tail]]++;
            }
            else {
                countArr[fruit[head]]--;
                head++;
            }

        }
        System.out.println(ans);
    }
}
