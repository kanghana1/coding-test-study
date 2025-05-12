package org.week6.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 슬라이딩 윈도우처럼 풀음
* 가장 왼쪽에서 K크기에 대한 sum을 구한 후, 한 칸씩 옆으로 이동하면서 새로운 범위로 들어오게 된
* 값을 더하고, 범위 밖으로 나가게된 값을 빼서 갱신 -> 최댓값도 갱신
* */
public class BOJ_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N];// input 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0 ; i < cnt ; i++) {
            sum += inputs[i]; // window size만큼 더해두기
        }
        int max = sum;
        for (int i = cnt ; i < N ; i++) {
            sum -= inputs[i - cnt];
            sum += inputs[i];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
