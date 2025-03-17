package org.week1.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16173 {
    static int[][] map;
    static int N;
    static int[] dy = new int[]{0, 1}; // 오른, 아래 좌표 배열
    static int[] dx = new int[]{1, 0};
    static boolean ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) { // 입력값 담기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = false; // 기본 false, 정상 도착여부 확인되면 true로 변경
        dfs(0, 0, map[0][0], -1); // (0,0)에서 시작, 초기 방향은 -1로 지정
        System.out.println(ans ? "HaruHaru" : "Hing");
    }
    // y : 현재 y idx, x : 현재 x idx, rest : 남은 이동 칸, dir: 이동중인 방향 (방향변경 불가하므로 파라미터로 넘김)
    private static void dfs(int y, int x, int rest, int dir) {
        if (rest < 0) return; // 좌표의 값이 0이었으면 -1로 들어옴 -> 해당경로 사용 불가
        // 도착여부 확인
        if (y == N - 1 && x == N - 1 && rest == 0) { // 정상도착 -> true로 값 변경
            ans = true;
            return;
        } else if (y == N - 1 && x == N - 1) return; // 도착은 했으나 이동해야 할 칸 수가 남은 경우 -> 여전히 false이므로 그냥 리턴

        for (int i = 0 ; i < 2 ; i++) { // i == 0 -> right, i == 1 -> down
            if (canGo(y + dy[i], x + dx[i]) ) { // 배열 경계 확인, idx가 0 ~ N - 1 의 경우만 이동 가능
                if (rest == 0) { // 모든 칸을 갔으니 새로 읽어줌
                    int value = map[y][x];
                    dfs(y + dy[i], x + dx[i], value - 1, i); // 이렇게 한 칸 이동했으므로 rest에는 value - 1들어감, 현재 이동한 방향이 마지막 인수로 들어감
                } else { // 아직 이동해야 할 칸이 남은 경우
                    if (i == dir || dir == -1) { // 직전 이동 방향과 같은 방향이거나, 초기 (-1) 이동의 경우에만 이동 가능
                        dfs(y + dy[i], x + dx[i], rest - 1, i);
                    }
                }
            }
        }
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}