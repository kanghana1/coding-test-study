package org.week5.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654 {
    static StringBuilder sb = new StringBuilder();
    static int[] inputs;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(inputs);
        boolean[] isVisit = new boolean[N];
        int[] ans = new int[M];
        backtracking(0, ans, isVisit);
        System.out.println(sb);
    }

    private static void backtracking(int cnt, int[] ans, boolean[] isVisit) {
        if (cnt == M) {
            for (int a : ans) {
                sb.append(a + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0 ; i < N ; i++) {
            if (!isVisit[i]) {
                ans[cnt] = inputs[i];
                isVisit[i] = true;
                backtracking(cnt + 1, ans, isVisit);
                isVisit[i] = false;
            }
        }
    }
}
