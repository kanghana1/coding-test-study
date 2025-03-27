package org.week3.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 제일 오래걸린 문제였다. 방문여부를 그냥 처리하면 다른 방향에서 왔을 때 방문할 수 없기 때문에
* 동서남북 네 방향에 대한 접근을 각각 체크해야한다. -> isVisit을 3차원 배열로 생성하였다
*
* 방향을 바꾼 횟수를 기준으로 우선순위 큐를 생성해 다익스트라 알고리즘을 썼다.
* 그런데 계속 특정 반례에서 답을 찾을 수 없었다.
* -> 방향 변경은 적지만 이동 거리가 길도록 우회하는 경우 이미 방문했다면 답을 찾을 수 없었음
*
* 그래서 dp를 도입해주었다. 방문을 했는데 더 최적의 방문인경우 or 방문한 적 없는 경우에
* 큐에 노드를 넣어주고 dp도 갱신해주었다.
*
* */
public class BOJ_6087 {
    static int[][] inputs;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int startY = -1, startX = 0, endY = -1, endX = 0;
        inputs = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char ch = str.charAt(j);
                if (ch == '*') inputs[i][j] = 1; // int형 다루는 게 더 편해서 길을 0, 벽을 1로 세팅
                else if (ch == 'C') {
                    if (startY == -1) {
                        startY = i;
                        startX = j;
                    } else {
                        endY = i;
                        endX = j;
                    }
                }
            }
        }

        int ans = find(startY, startX, endY, endX); // 로직 수행 -> 정답 반환
        System.out.println(ans);
    }

    private static int find(int startY, int startX, int endY, int endX) {
        PriorityQueue<Node> queue = new PriorityQueue<>(); // 우선순위 큐 생성 (방향변경 횟수 기준 오름차순)
        boolean[][][] isVisit = new boolean[inputs.length][inputs[0].length][4]; // 방문배열
        int[][] dp = new int[inputs.length][inputs[0].length]; // dp

        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE); // 최소값 갱신을 위해 max로 초기화
        dp[startY][startX] = 0;// 시작점 0으로 초기화

        for (int i = 0; i < 4; i++) {
            queue.add(new Node(startY, startX, i, 0));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.y == endY && node.x == endX) return node.cnt;

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (canGo(ny, nx) && inputs[ny][nx] == 0) { // index를 벗어나지 않고, 길인 경우
                    int nextCnt = node.cnt + (node.dir == i ? 0 : 1); // 같은방향이면 그대로, 다른방향이면 cnt + 1
                    if (!isVisit[ny][nx][i] || nextCnt < dp[ny][nx]) { // 방문을 안 했거나, 방문하고 최적이거나!!
                        isVisit[ny][nx][i] = true;
                        dp[ny][nx] = nextCnt;
                        queue.add(new Node(ny, nx, i, nextCnt));
                    }
                }
            }
        }
        return -1; // 도달 불가능한 경우
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs[0].length;
    }
}

class Node implements Comparable<Node> {
    int y, x, dir, cnt;

    public Node(int y, int x, int dir, int cnt) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        return this.cnt - o.cnt;
    }
}
