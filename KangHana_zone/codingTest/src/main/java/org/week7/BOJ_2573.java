package org.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 1번문제와 비슷한 문제였으나, 한 메소드에서 대부분의 경우를 처리하려는 습관의 단점을 알 수 있던 문제였다
* 섬 개수를 탐색하기 위한 bfs 안에서 빙산 높이를 바로바로 깎으려 했는데 이렇게 하면 직전에 온 방향의 섬은 제외하고 탐색하는 등
* 불편하고 비효율적인 부분이 있었다.
*
* 그래서 탐색과 빙산을 깎는 함수를 별도로 설정하고, 깎일 빙산의 높이를 담을 배열을 별도로 생성해서 문제를 풀었다.
* 이렇게 하니 56퍼센트에서 시간초과가 걸리던 문제를 해결할 수 있었다.
* */

public class BOJ_2573 {
    static boolean[][] isVisit; // 방문배열
    static int[][] inputs; // 입력받는 배열
    static int[][] melt; // 녹여야 할 양 담음
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        inputs = new int[N][M];
        isVisit = new boolean[N][M];
        melt = new int[N][M];

        for (int i =  0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandCnt = 0; // 한 턴의 섬 개수를 받는 배열. 2 이상이면 종료
        int turn = 0; // 턴 수 확인 (결과값)
        while (true) {
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    if (inputs[i][j] != 0 && !isVisit[i][j]) { // 방문 안 한 빙산 -> bfs로 탐색
                        bfs(i, j);
                        islandCnt++; // 섬 개수 + 1
                    }
                    if (islandCnt >= 2) { // 섬 개수 확인
                        System.out.println(turn);
                        return;
                    }

                }
            }
            if (islandCnt == 0) { // 섬이 아예 없는 경우 (다 녹은 경우) 처리
                System.out.println(0);
                return;
            }

            // 섬 녹이기. bfs 탐색이 모두 끝난 후, melt 배열에 담긴 값씩 녹이면 됨
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    if (melt[i][j] != 0) {
                        if (inputs[i][j] > melt[i][j]) inputs[i][j] -= melt[i][j];
                        else inputs[i][j] = 0;
                        melt[i][j] = 0; // 초기화
                    }
                }
            }

            islandCnt = 0;
            turn++;
            isVisit = new boolean[N][M];
        }
    }

    private static void bfs(int y, int x) {
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node(y, x);
        queue.add(node);
        isVisit[y][x] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            meltIce(now.y, now.x); // 현 위치 기준 녹일 높이 계산
            for (int i = 0; i < 4 ; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (canGo(ny, nx) && !isVisit[ny][nx] && inputs[ny][nx] > 0) {
                    queue.add(new Node(ny, nx));
                    isVisit[ny][nx] = true;
                }
            }
        }
    }
    private static void meltIce(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (canGo(ny,nx)) {
                if (inputs[ny][nx] == 0) {
                    melt[y][x]++;
                }
            }
        }
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs[0].length;
    }

    static class Node {
        int y;
        int x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
