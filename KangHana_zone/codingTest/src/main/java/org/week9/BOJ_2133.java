package org.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 정말 오랜만에 푸는 dp문제였다 !!!!!!!!! 어려웠음
* 처음 점화식 세울 떄, An = 3 * An-2 + 2 * An-4 라고 생각했는데, 매 턴마다
* 새로 나타나는 경우가 2개 있다는 걸 뒤늦게 알아버렸다
*
* 그래서 결국 An = 3 * An-2 + 2 * An-4 ... + 2 * A0 임을 알았는데 어쩌란거지 싶었음
* 게시판 찾아보니까 이거에 대한 설명이 있었는데 읽어도 모르겠어서 힌트만 얻어서 풀었다
*
* dp 더 풀어야겟음 ..
* */
public class BOJ_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N % 2 == 1) { // 홀수일 땐 무조건 0!!! 있을 수가 없음
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];

        dp[0] = 1;
        dp[2] = 3;
        if (N == 2) {
            System.out.println(dp[2]); // 이거 안해주면 ArrayIndexOutOfBound 뜸
            return;
        }

        for (int i = 4 ; i <= N ; i += 2) {
            dp[i] = dp[i - 2] * 4 - dp[i - 4]; // 도출한 점화식에서 An이랑 An-2 연립하면 이렇게 나옴
        }

        System.out.println(dp[N]);
    }
}
