package org.week5.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 백트래킹 : dfs + 분기
* 조건이 있는 (중간에 끊을 수 있는) 완전탐색이라고 생각하고 풀었다.
* 해당 지점의 인접한 방문지가 있는 경우 재귀를 하지 않고, 나머지 경우 재귀를 돌렸다.
* 종료조건은 cnt == K 로 지정했다
*
* 이때 y와 x를 각각 해서 이중반복문으로 돌리니 누락되는 부분이 생겨서 답이 틀렸다고 떴다
* -> 일차원으로 idx 한개를 만들어서
* y = idx / M
* x = idx % M
* 으로 만들어 모든 인덱스를 순회해볼 수 있도록 코드를 작성했다.
* */
public class BOJ_18290 {
    static int[][] maps;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    static boolean[][] isVisit;
    static int M;
    static int K;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        isVisit = new boolean[N][M];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(0, 0, 0);
        System.out.println(ans);
    }

    private static void backtracking(int idx, int cnt, int sum) {
        if (cnt == K) { // 종료조건 -> 이때마다 ans를 max로 갱신한다
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = idx ; i < maps.length * M ; i++) {
            // idx 즉 현 위치부터 이후만 탐색 (1,3과 3,1은 같기 때문에 앞쪽을 다시 탐색할 필요 없음)
            int y = i / M;
            int x = i % M;
            if (isVisit[y][x] || isNeighbor(y, x)) continue; // 방문했거나, 인접한 경우 재귀 X

            isVisit[y][x] = true;
            backtracking(i + 1, cnt + 1, sum + maps[y][x]);
            isVisit[y][x] = false;
        }
    }
    // 인접한 곳에 방문한 곳이 있는 경우
    private static boolean isNeighbor(int y, int x) {
        for (int i = 0 ; i < 4 ; i++) {
            if (canGo(y + dy[i], x + dx[i])) { // 인덱스 에러 방지
                if (isVisit[y + dy[i]][x + dx[i]]) return true;
            }
        }
        return false;
    }


    private static boolean canGo(int y, int x) {
        return y >= 0 && y < maps.length && x >= 0 && x < maps[0].length;
    }
}
