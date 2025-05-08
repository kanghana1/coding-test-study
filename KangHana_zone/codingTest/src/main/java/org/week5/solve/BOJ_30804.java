package org.week5.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 같은 숫자가 2개만 들어가도록 조절하되, 그 길이가 가장 긴 경우를 찾는 문제였다.
* 이렇게 연속되는 자리를 찾을 때는 슬라이딩윈도우 기법을 많이 쓴다.
* left와 right를 정하고, 그 사이에 들어가있는 숫자의 종류와 개수를 카운트하면 답을 구할 수 있다.
*
* 편의를 위해 왼쪽에서 시작해서 다른 숫자의 개수가 2를 넘어갈 때까지 오른쪽으로 범위를 넓힌다.
* 이후 3이상이 되면 left를 조절해 범위를 좁히는 과정을 반복해주고, 답을 max로 갱신해주었다.
*
* */
public class BOJ_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // input 배열
        int[] count = new int[10]; // 0 ~ 9까지의 개수 카운트 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0; // 탕후루 과일 종류
        int left = 0; // left idx
        int ans = 0; // 갱신할 정답변수

        for (int right = 0 ; right < N ; right++) {
            if (count[arr[right]] == 0) cnt++; // count배열의 값이 0이라는 것은 새로운 값이라는 의미 -> 종류 + 1
            count[arr[right]]++; // 해당과일 개수도 늘림

            while (cnt > 2) { // 종류가 3 이상이 될 경우, left를 조절해 2 이하가 되게 함
                count[arr[left]]--;
                if (count[arr[left]] == 0) cnt--;
                left++;
            }
            ans = Math.max(ans, right - left + 1); // 이후 답 갱신
        }
        System.out.println(ans);
    }
}