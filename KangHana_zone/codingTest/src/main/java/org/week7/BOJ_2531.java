package org.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 슬라이딩 윈도우로 풀 수 있던 문제였다.
* 대신 회전초밥은 순환하기 때문에, 배열의 마지막 초밥 + 맨 앞에서 eatCnt - 1 개의 초밥을
* 고를 경우를 대비하여 배열의 크기를 N + eatCnt - 1로 지정해 넣어두었다.
* 예) 1, 3, 2, 5, 7 이 주어지고, eatCnt가 3이면 1, 3, 2, 5, 7, 1, 3 으로 담았다
*
* */

public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int kind = Integer.parseInt(st.nextToken());
        int eatCnt = Integer.parseInt(st.nextToken());
        int coupon = Integer.parseInt(st.nextToken());

        int[] input = new int[N + eatCnt - 1];
        int[] sushi = new int[kind + 1]; // 각 초밥의 개수 카운트 배열
        for (int i = 0 ; i < N ; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N ; i < input.length ; i++) {
            input[i] = input[i % N];
        }


        int cnt = 0; // 종류 카운트
        for (int i = 0 ; i < eatCnt ; i++) {
            if (sushi[input[i]] == 0) cnt++;
            sushi[input[i]]++;
        }
        int max = cnt;
        if (sushi[coupon] == 0) max += 1; // 쿠폰 쓸 수 있는 초밥이 포함 안 된 경우 -> +1

        for (int i = eatCnt ; i < input.length ; i++) {
            int left = i - eatCnt;
            if (sushi[input[left]] == 1) {
                cnt--;
            }
            sushi[input[left]]--;


            if (sushi[input[i]] == 0) cnt++;
            sushi[input[i]]++;


            if (sushi[coupon] == 0) max = Math.max(max, cnt + 1);
            else max = Math.max(max, cnt);
        }
        System.out.println(max);

    }
}