package org.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 그냥 박치기로 풀었는데 이렇게 푸는 방법밖에 없나 싶은 풀이이다 좀 무식하게 푼 느낌
* 그냥 동쪽으로 이동할 경우, 서쪽, 남쪽, 북쪽 각각의 상황에 따라서
* 함수를 만들고, swap을 했다
*
* + 처음에 문제를 제대로 안 읽어서 map 값 자체를 변경해야 한다는 걸 인지하지 못해서
*   한번 틀렸음 ..
* */

public class BOJ_14499 {

    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;

    static int[] dice = new int[7]; // 주사위 값 저장
    static int[][] map; // 지도 값 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dice[6] = map[startY][startX]; // 첫 밑면 값 갱신 (첫 주사위 값은 0이라고 주어졌기 때문에 이렇게 넣음)

        // 0, 0, 0, 0, 0, 0 -> 위 앞 오 왼 뒤 아래
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < K ; i++) {
            int now = Integer.parseInt(st.nextToken()); // 방향

            // 지금보니까 이 if문을 dy, dx해서 for문으로 돌리는게 더 깔끔할 거 같긴 함 근데 고치기 귀찮
            if (now == EAST) {
                if (!canGo(startY, startX + 1)) continue; // 갈 수 있는지 여부 체크
                startX++;
                goEast(); // swap역할 함수
                mapCheck(startY, startX);

            } else if (now == WEST) {
                if (!canGo(startY, startX - 1)) continue;
                startX--;
                goWest(); // swap역할 함수
                mapCheck(startY, startX);

            } else if (now == SOUTH) {
                if (!canGo(startY + 1, startX)) continue;
                startY++;
                goSouth(); // swap역할 함수
                mapCheck(startY, startX);

            } else if (now == NORTH) {
                if (!canGo(startY - 1, startX)) continue;
                startY--;
                goNorth(); // swap역할 함수
                mapCheck(startY, startX);
            }
            sb.append(dice[1] + "\n");
        }
        System.out.println(sb);
    }

    private static void mapCheck(int y, int x) {
        if (map[y][x] == 0) {
            map[y][x] = dice[6];
        } else {
            dice[6] = map[y][x];
            map[y][x] = 0;
        }
    }

    private static void goNorth() {
        int swap = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = swap;
    }

    private static void goSouth() {
        int swap = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = swap;
    }

    private static void goWest() {
        int swap = dice[1];
        dice[1] = dice[3];
        dice[3] = dice[6];
        dice[6] = dice[4];
        dice[4] = swap;
    }

    private static void goEast() {
        int swap = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = swap;
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length;
    }
}
