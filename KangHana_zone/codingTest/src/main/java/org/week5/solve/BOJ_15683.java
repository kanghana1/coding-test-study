package org.week5.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
* 백트래킹을 다시 공부해야겠다고 느낀 문제였다 ....
* 탐색한 위치들을 저장하기 위해 계속 이차원 bool 배열을 사용했는데, 이게 공유가 되어 원하는
* 값이 도출되지 않아 문제가 생겼다.
* 그래서 배열을 포기하고, 탐색한 위치 좌표를 리스트에 넣어두고, dfs가 끝나면 rollbak하는 방식으로
* 진행하고자 했다.
*
* 처음에 방향별로 함수를 만들어버려서 이 리스트 또한 공유되어 문제가 생겼다.
* 매번 리스트를 새로 생성해야 하는데, 메모리나 시간적 리스크가 크다고 판단되었다.
*
* 결론적으로는 함수에 역할을 잘 나누어 리스트나 배열이 공유되는 일이 없도록 설계했다.
* 3일동안 안 되어서 gpt의 힘을 빌리고 말았다 ...................
*
* */
public class BOJ_15683 {
    static int r;
    static int c;
    static int[] dy = new int[]{0, 1, 0, -1}; // 오, 아래, 왼, 위
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[][] maps; // map, 1~5까지는 cctv 타입, 6은 벽이다. 나는 방문한 위치에 7을 넣었다
    static int ans = Integer.MAX_VALUE;
    static List<int[]> cctv = new ArrayList<>(); // cctv만 따로 담아두고, dfs의 종료조건으로 사용했다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        maps = new int[r][c];

        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < c ; j++) {
                int value = Integer.parseInt(st.nextToken());
                maps[i][j] = value;
                if (value >= 1 && value <= 5) {
                    cctv.add(new int[]{i, j});
                }
            }
        }
        dfs(0);
        System.out.println(ans);
    }

    /*
    * dfs 함수
    * 종료조건이 된다면 종료하고, 이후에는 재귀만을 담당한다. 주요 함수는 별도로 만들었다.
    * */
    private static void dfs(int idx) {
        if (idx == cctv.size()) { // 마지막 cctv를 탐색한 후 (모든 cctv 탐색이 끝났다는 뜻)
            int cnt = 0;
            for (int i = 0 ; i < r ; i++) {
                for (int j = 0 ; j < c ; j++) {
                    if (maps[i][j] == 0) cnt++; // 사각지대 개수 카운트
                }
            }
            ans = Math.min(cnt, ans);
            return;
        }

        int y = cctv.get(idx)[0];
        int x = cctv.get(idx)[1];
        int value = maps[y][x];


        if (value == 1) { // 1인 경우에는 각각 상하좌우가 개별적으로 탐색된다. 3번쨰 파라미터에 이게 들어간다.
            dfsWithDir(y, x, new int[]{0}, idx);
            dfsWithDir(y, x, new int[]{1}, idx);
            dfsWithDir(y, x, new int[]{2}, idx);
            dfsWithDir(y, x, new int[]{3}, idx);

        } else if (value == 2) { // 2인 경우 마주보는 방향이 탐색된다. 그래서 0,2 / 1,3이 된다.
            dfsWithDir(y, x, new int[]{0, 2}, idx);
            dfsWithDir(y, x, new int[]{1, 3}, idx);

        } else if (value == 3) {
            dfsWithDir(y, x, new int[]{0, 1}, idx);
            dfsWithDir(y, x, new int[]{1, 2}, idx);
            dfsWithDir(y, x, new int[]{2, 3}, idx);
            dfsWithDir(y, x, new int[]{3, 0}, idx);

        } else if (value == 4) {
            dfsWithDir(y, x, new int[]{0, 1, 2}, idx);
            dfsWithDir(y, x, new int[]{0, 1, 3}, idx);
            dfsWithDir(y, x, new int[]{1, 2, 3}, idx);
            dfsWithDir(y, x, new int[]{0, 2, 3}, idx);

        } else if (value == 5) {
            dfsWithDir(y, x, new int[]{0, 1, 2, 3}, idx);
        }

    }

    /*
    * dfsWithDir
    * cctv 방향까지 확정된 상태의 탐색을 진행한다.
    * 이제 방문한 cctv의 개수를 카운트하는 작업을 진행하며, 모든 탐색방향의 탐색이 끝나면
    * dfs와 rollback을 진행한다.
    * 가독성을 위해 탐색과정은 search 함수로 빼두었다.
    * */
    private static void dfsWithDir(int y, int x, int[] dirs, int idx) {
        List<int[]> visited = new ArrayList<>(); // 방문한 위치의 좌표를 담기 위한 리스트. 롤백시 사용

        for (int dir : dirs) { // 탐색해야하는 방향들을 각각 탐색할 수 있게 반복문 사용
            search(y, x, dir, visited);
        }

        dfs(idx + 1);
        rollback(visited);
    }

    /*
    * search
    * 여러 방향 중 하나의 경로 (하나의 방향) 만을 탐색한다.
    * 방향의 종류별로 함수를 따로 뽑으면 리스트가 공유되어 에러가 발생하기 때문에
    * 하나의 함수로 모든 방향을 처리할 수 있게 고안하였다.
    * 0은 오른쪽, 1은 아래 이런식으로 인덱스를 사용해 탐색 방향을 인지할 수 있고,
    * dy, dx 배열을 이용해 다음 탐색 위치의 인덱스를 뽑아낼 수 있도록 했다.
    * 여기에서 방문한 곳의 값을 7로 갱신하고, 방문리스트에 위치인덱스를 넣도록 만들었다.
     * */
    private static void search(int y, int x, int dir, List<int[]> visited) {
        while (true) {
            y += dy[dir]; // 탐색할 y좌표
            x += dx[dir]; // 탐색할 x좌표

            if (y < 0 || y >= r || x < 0 || x >= c) break; // 유효성 체크
            if (maps[y][x] == 6) break; // 벽 만나면 끝

            if (maps[y][x] == 0) {
                maps[y][x] = 7; // 7로 갱신
                visited.add(new int[]{y, x}); // 방문 리스트에 담기
            }
        }
    }

    /*
    * rollback
    * dfs 탐색이 끝나면 7로 바꾼 자리를 다시 0으로 만들어야 한다.
    * 방문리스트를 이용해 그 역할을 해주는 함수라고 이해하면 된다.
    * */
    private static void rollback(List<int[]> visit) {
        for (int[] arr : visit) {
            maps[arr[0]][arr[1]] = 0;
        }
    }

}
