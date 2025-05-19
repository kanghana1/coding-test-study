package org.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 여러 방법이 있겠지만 투포인터로 문제를 풀어보았다.
* 현재몸무게와 이전몸무게 포인터를 따로 두고 입력받은 값과 현재 계산한 값의 크기를 비교해 포인터를 옮기는
* 방식으로 문제를 풀었다
*
*
* */
public class BOJ_1484 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        for (int i = 0 ; i <= N ; i++) {
            arr[i] = i; // 몸무게들 넣기 -> 입력받은 N보다 같거나 작은 값까지만 넣으면 됨
        }

        StringBuilder sb = new StringBuilder();
        int before = 1;
        int now = 1;
        while (now <= N && now >= before) {
            int value = (now - before) * (now + before); // G값 구하기
            if (value < N) {
                now++;
            } else if (value > N) {
                before++;
            } else {
                sb.append(arr[now]).append("\n"); // 값이 N과 같으면 -> 빌더에 넣고 now인덱스 ++
                now++;
            }
        }
        if (sb.isEmpty()) sb.append(-1);
        System.out.println(sb);
    }
}
