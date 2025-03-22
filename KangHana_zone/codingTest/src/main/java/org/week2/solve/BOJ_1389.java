package org.week2.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* 간단한 BFS 문제로 생각하고 접근 -> 배열을 인자로 받는 큐를 이용한 탓에 메모리 초과 발생
* 어디서든 메모리를 줄여보고자 isVisit 배열 대신 BitSet 사용 -> 여전히 메모리초과
* -> BFS 말고 다른 방법 고안 다짐 ...................
*
* 문제 자체는 최단경로를 구하는 문제와 같음 -> 다익스트라 혹은 플로이드 사용 가능
* BFS를 공부하는 중이라 BFS와 접근방식이 비슷한 다익스트라를  사용하는게 좋았겠지만 메모리초과 2트로 힘이 빠져서
* 플로이드-워셜 알고리즘을 이용하기로 했다. (사실 플로이트-워셜을 많이 사용해보지 않아서 쓰고싶었다)
*
* */
public class BOJ_1389 {
    static int[][] floyd;
    static int INF = 10000000; // 이거 Integer.MAX_VALUE 쓰면 안된다. 알고리즘 특성상 값 + 값 을 하는 일이 생기는데, 이떄 integer 범위를 넘어가서 마이너스 값이 들어간다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        floyd = new int[N + 1][N + 1];

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (i == j) {
                    floyd[i][j] = 0; // 자기자신 -> 0으로 넣어줌
                } else {
                    floyd[i][j] = INF; // -> 디폴트는 INF로
                }
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            floyd[a][b] = 1;
            floyd[b][a] = 1; // 양방향 유의
        }

        relation();
        int min = INF;
        int idx = 0;
        for (int i = 1 ; i <= N ; i++) {
            int sum = 0;
            for (int j = 1 ; j <= N ; j++) {
                if (floyd[i][j] != INF) {
                    sum += floyd[i][j]; // 베이컨 값 구하기
                }
            }
            if (min > sum) { // 등호를 넣으면 최솟값이 여러개 일 떄 i가 더 큰게 들어가므로 등호를 빼줌
                min = sum;
                idx = i; // 최소가 바뀌면 idx 갱신.
            }
        }

        System.out.println(idx);
    }

    private static void relation() {
        for (int i = 1 ; i < floyd.length ; i++) { // 경유지를 가장 밖에 써줘야함. 습관대로 가장 안에 쓰면 배열이 다 채워지지 않은 상태로 경유지가 온 배열을 쓸게 됨......
            for (int j = 1 ; j < floyd.length ; j++) {
                for (int k = 1 ; k < floyd.length ; k++) {
                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k]);
                }
            }
        }
    }
}
