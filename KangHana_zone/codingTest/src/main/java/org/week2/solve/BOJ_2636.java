package org.week2.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* 이 문제의 포인트는 치즈에 뚫린 구멍과 공기를 구별하는 것이었다.
* input배열의 값이 1인 부분을 기준으로 bfs를 돌며 처리하면, 값이 0인 부분이 두가지 경우가 된다.
* 1. 공기와 접촉되는 부분 -> 현재 위치가 c로 변경. 정상적인 처리이다
* 2. 그냥 구멍 -> 현재와 변함 없어야 한다.
*
* 이렇게 구멍을 구별해내기가 불가능하다고 생각했다. 그러면 반대로, X친 부분, 즉 무조건 치즈가 없는
* 부분을 이용해 여기서 bfs를 돌린다. 이때 만나는 치즈는 다 변화시키면 그만이다! 아주 간단한 문제가 됐다.
*
* c가 된 치즈들은 Queue에 담아두었다. 다음턴에 Queue에 들어있는 값을 이용해 bfs를 돌리면 된다.
* 이때 방문하지 앖고 값이 0인 부분, 즉 이전 턴에서 구멍이었던 부분을 만난다면 얘도 큐에 담는다.
*
*
* 처음 값을 입력받을 때, 값이 1인 부분의 개수를 미리 세어두고, 처리할 때마다 -1씩 해준다.
* 이를 위한 변수는 total로 지정해두었다.
* 한 턴이 끝났을 때 total이 0이 아니라면, 다른 변수에 값을 옮겨담고 다시 bfs를 돌린다.
* 0이면 옮겨담았던 변수의 값을 출력한다. 즉, 이전턴의 나머지값을 출력하는 셈이 된다.
*
* */
public class BOJ_2636 {
    static int[][] inputs; // 입력값을 담는 배열
    static int total = 0; // 총 치즈의 개수
    static boolean[][] isVisit; // 방문체크 배열 -> 초기화 할 필요가 없기에 전역변수마냥 사용
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    static Queue<int[]> prevCheck = new ArrayDeque<>(); // 다음턴에 돌릴 bfs 후보들
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        inputs = new int[r][c];
        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < c ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
                if (inputs[i][j] == 1) total++; // 미리 개수를 세어준다.
            }
        }

        // 여기까진 배열채우기 코드였다.

        isVisit = new boolean[r][c];
        int prevCnt = total; // total값을 옮겨담아주었다. 이제부터 여기에 이전 턴의 치즈 개수가 들어간다.
        int cnt = 0; // 턴 수를 세어준다.
        prevCheck.add(new int[]{0, 0}); // {0,0}은 문제에 의해 무조건 값이 0이다. 이것 만으로 첫 턴을 돌릴 수 있다.
        while (total > 0) {
            prevCnt = total;
            aircheck(); // bfs
            cnt++;
        }
        System.out.println(cnt + "\n" + prevCnt);
    }

    private static void aircheck() {
        Queue<int[]> next = new ArrayDeque<>(); // 다음턴에 bfs돌릴 것들. 즉 c로 표시될 치즈를 담는다.
        while(!prevCheck.isEmpty()) {
            int[] nowIdx = prevCheck.poll();
            for (int i = 0 ; i < 4 ; i++) {
                if (canGo(nowIdx[0] + dy[i], nowIdx[1] + dx[i])) { // 전주 채원이의 피드백 반영 Vv
                    if (inputs[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] == 1) { // 치즈 발견 ! c로 만들고, total-- 처리
                        isVisit[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] = true;
                        inputs[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] = 0;
                        next.add(new int[]{nowIdx[0] + dy[i], nowIdx[1] + dx[i]}); // 다음 턴을 위한 큐에 넣는다
                        total--;
                    } else { // 방문하지 않은 0은 이전턴에서의 구멍이다. 방문처리를 하고 지금 사용중인 큐에 넣는다.
                        prevCheck.add(new int[]{nowIdx[0] + dy[i], nowIdx[1] + dx[i]});
                        isVisit[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] = true;
                    }
                }
            }
        }
        prevCheck = next; // 큐를 바꿔치기 한다. 다음턴에 쓸 재료를 넣는 셈이다.
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs[0].length && !isVisit[y][x];
    }
}
