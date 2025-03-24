package org.week2.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 꼬아서 생각하니까 한없이 어려웠던 문제였다 (처음에 거리 기준으로 이중 리스트 만들어서 썡쇼함 -> 메모리초과 엔딩)
* 상어가 물고기에는 우선순위가 있고, 우선순위는 다음과 같다.
* 1. 거리가 가까워야 한다.
* 2. 거리가 같다면, y인덱스가 작아야 한다. (가장 위에 있는)
* 3. 거리도 같고 y인덱스도 같다면 x인덱스가 작아야 한다.
* 
* 해당 우선순위를 이용하기 위해서, BFS를 기반으로 이용하되 먹을 수 있는 물고기를 우선순위 큐에 넣는다.
* 우선순위 큐에 위 케이스를 적용하고, 가장 우선순위가 높은 물고기를 먹는다.
* 
* 물고기를 먹으면 현재 상어의 위치가 현 위치로 초기화 되고, 위 과정을 반복한다.
* 
*
* */

public class BOJ_16236 {
    static int[][] inputs;
    static int sharkSize = 2; // 상어의 첫 크기는 2이다.
    static int[] dy = new int[]{-1, 0, 1, 0}; // 위 왼 아래 오
    static int[] dx = new int[]{0, -1, 0, 1};
    static int sharkY;
    static int sharkX;
    static int eatCnt = 0; // 먹은 물고기 수 ->  상어 크기와 같아지면 size++, eatCnt는 0으로 초기화
    static int ans = 0; // 이동 수 저장 -> 정답으로 제출
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputs = new int[N][N];


        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
                if (inputs[i][j] == 9) {
                    sharkY = i; // 현 상어의 위치를 저장한다
                    sharkX = j;
                    inputs[i][j] = 0; // 9라는 숫자가 개인적으로 걸리적거려서 0으로 초기화했다
                }
            }
        }

        while(true) { 
            Node fish = findfish(sharkY, sharkX); // 우선순위가 가장 높은 물고기
            if (fish == null) break; // 더이상 먹을 수 있는 물고기가 없을 때까지 반복한다
            ans += fish.cnt; // 물고기와의 거리 누적
            sharkY = fish.y; // 상어 위치 초기화
            sharkX = fish.x;
            inputs[sharkY][sharkX] = 0; // 더이상 물고기가 없으니 초기화
            eatCnt++; // 먹은 수 추가

            if (eatCnt == sharkSize) {
                eatCnt = 0;
                sharkSize++;
            }
        }
        System.out.println(ans);
    }

    private static Node findfish(int y, int x) {
        PriorityQueue<Node> canEat = new PriorityQueue<>(); // 먹을 수 있는 물고기들 넣어주기
        Queue<Node> queue = new ArrayDeque<>(); // bfs를 위한
        boolean[][] isVisit = new boolean[inputs.length][inputs.length]; // 방문여부 확인
        queue.add(new Node(y, x, 0));
        isVisit[y][x] = true;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if (canGo(nextY, nextX) && !isVisit[nextY][nextX]) {
                    isVisit[nextY][nextX] = true;
                    Node next = new Node(nextY, nextX, now.cnt + 1);
                    if (inputs[nextY][nextX] < sharkSize && inputs[nextY][nextX] >= 1) { // 먹을 수 있는 경우
                        canEat.add(next);
                    } else { // 0 혹은 자신과 같은 크기의 물고기 -> 지나갈 수만 있는
                        queue.add(next);
                    }

                }
            }
        }
        return canEat.isEmpty() ? null : canEat.poll(); // 먹을 수 있는 물고기가 없다면 null, 있으면 반환
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs.length && inputs[y][x] <= sharkSize;
    }
}

class Node implements Comparable<Node> {
    public int y;
    public int x;
    public int cnt;
    public Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) { // 우선순위 큐에 넣을 객체와 객체를 어떤 기준으로 정렬할 것인지 명시
        if (this.cnt != o.cnt) return Integer.compare(this.cnt, o.cnt); // 1. 거리 비교
        if (this.y != o.y) return Integer.compare(this.y, o.y); // 2. 거리 같으면 y 비교 
        return Integer.compare(this.x, o.x); // 3. 다 같으면 x 비교
    }
}
