package org.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 그래프 오랜만 ..... 근데 그래프에 포커스가 맞춰진 느낌보단 BFS 문제같았다
* 상당히 간만에 게시판 데이터 없이 풀어서 기분이 좋았다. 다른 java 사용자보다 시간은 절반정도로 줄였지만 메모리를 왕창 썼다.
* 시간과 메모리 중 어느 부분을 중요하게 다룰지 고민해봐야겠음 (나는 개인적으로 시간이긴 하다)
*
* 이 문제는 인접한 나라를 구하는 게 우선이다. 그래서 groups 배열을 생성해 같은 인접한 나라는 같은 숫자를 넣는 방식을 이용했다.
* 터무니없는 값을 넣으면 이해하기 힘들기 때문에 N * i + j + 1 값을 사용했다. (몇번째에 있는 나라인지)
*
* 반복문을 돌면서 모든 나라에서 bfs를 돌리되, 이미 group에 0이 아닌 숫자가 있는 경우는 구해져 있는 값을 넣는다.
* 예를 들어, 1, 2 나라가 인접했다면 이미 1에서 돈 bfs에 의해 2도 그룹넘버가 1로 바뀌어있을 것이고, 그럼 첫번쨰 나라와 같은
* 값을 넣어주면 된다.
* 만약 0이면 bfs를 돌린다. 이때 인접한 나라가 있는지 미리 확인해 없으면 그냥 무시하고 반복문 실행, 있으면 bfs 함수를 호출했다.
*
* */
public class BOJ_16234 {
    static int[][] world; // 나라들의 값을 넣음
    static int[][] groups; // 그룹넘버를 넣음 -> 이걸 통해 인접국가들 확인

    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};

    static int max; // R
    static int min; // L

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        world = new int[N][N];
        groups = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                world[i][j] = Integer.parseInt(st.nextToken()); // 국가의 인구수를 채운다
            }
        }

        int cnt = 0; // 며칠간 발생하는지 카운트
        while (true) {
//            print();
            boolean isOpen = false; // 국경선 오픈 여부
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (groups[i][j] == 0) { // 그룹넘버가 0이면
                        boolean enter = false; // 들어가야 하는지 (인접국이 있을지) 여부
                        for (int k = 0 ; k < 4 ; k++) {
                            if (canGo(i + dy[k], j + dx[k]) && Math.abs(world[i][j] - world[i + dy[k]][j + dx[k]]) >= min && Math.abs(world[i][j] - world[i + dy[k]][j + dx[k]]) <= max) {
                                enter = true; // 인접국 있으니까 들어가기
                                break;
                            }
                        }
                        if (!enter) continue;
                        isOpen = true; // 한 번이라도 돌 수 있으니까 국경선 open 가능
                        int avg = bfs(i, j, N * i + j + 1); // bfs 실행, 인접국들 평균 반환 (각 칸 인구수)
                        world[i][j] = avg; // 시작국의 인구수 갱신
                    } else { // 그룹넘버가 0이 아니면 -> 이미 어딘가의 인접국
                        int value = groups[i][j] - 1; // 그룹넘버 꺼내고
                        int y = value / N; // 그룹넘버를 이용해 시작국가 인덱스 찾기
                        int x = value % N;
                        world[i][j] = world[y][x]; // 시작국과 같은 값으로 넣기
                    }
                }
            }
            if (!isOpen) break; // 국경오픈 안했다 -> 종료
            cnt++;
            groups = new int[N][N]; // 그룹넘버 초기화
        }
        System.out.println(cnt);
    }

    private static int bfs(int y, int x, int groupId) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x));
        groups[y][x] = groupId;
        int sum = world[y][x]; // 인접국의 인구 합
        int cnt = 1; // 인접국 개수

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (canGo(ny, nx)) {
                    int diff = Math.abs(world[node.y][node.x] - world[ny][nx]); // 인구수 차이
                    if (diff >= min && diff <= max) { // 조건에 맞으면 큐에 넣기
                        queue.add(new Node(ny, nx));
                        groups[ny][nx] = groupId;
                        sum += world[ny][nx];
                        cnt++;
                    }

                }
            }
        }

        return sum / cnt; // 평균 반환
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < world.length && x >= 0 && x < world.length && groups[y][x] == 0;
    }

    // 테스트용 print
    private static void print() {
        System.out.printf("===========================\n");
        for (int i = 0 ; i < groups.length ; i++) {
            for (int j = 0 ; j < groups.length ; j++) {
                System.out.print(world[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Node{
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
