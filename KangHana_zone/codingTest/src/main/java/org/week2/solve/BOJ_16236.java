package org.week2.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 메모리 초과 남. 고쳐야함!!!!!!!!
public class BOJ_16236 {
    static List<List<int[]>> fish = new ArrayList<>();
    static int[][] inputs;
    static int sharkSize = 2;
    static int[] dy = new int[]{-1, 0, 1, 0}; // 위 왼 아래 오
    static int[] dx = new int[]{0, -1, 0, 1};
    static int eatCnt = 0;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputs = new int[N][N];

        for (int i = 0 ; i <= 6 ; i++) {
            fish.add(new ArrayList<>());
        }

        int sharkY = 0;
        int sharkX = 0;

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
                if (inputs[i][j] == 9) {
                    sharkY = i;
                    sharkX = j;
                } else if (inputs[i][j] == 0) {
                    continue;
                } else {
                    fish.get(inputs[i][j]).add(new int[]{i, j});
                }
            }
        }
        while (true) {
            boolean end = false;
            for (int i = 1 ; i < sharkSize ; i++) {
                if (fish.get(i).isEmpty()) {
                    end = true;
                    break;
                }
            }
            if (end) break;
            findFish(sharkY, sharkX);
        }
        System.out.println(ans);
    }

    private static void findFish(int startY, int startX) {
        Queue<Node> queue = setQueue(startY, startX); // 먹을 수 있는 크기의 물고기
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nowY = node.y + dy[i];
                int nowX = node.x + dx[i];
                if (canGo(nowY, nowX)) {
                    if (inputs[nowY][nowX] == 9) {
                        fish.get(inputs[node.index[0]][node.index[1]]).remove(node.index);
                        eatCnt++;
                        ans += node.cnt;
                        if (eatCnt == sharkSize) {
                            sharkSize++;
                            eatCnt = 0;
                        }
                        return;
                    } else {
                        queue.add(new Node(node.index, nowY, nowX, node.cnt + 1));
                    }
                }

            }
        }
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs.length && sharkSize >= inputs[y][x];
    }

    private static Queue<Node> setQueue(int startY, int startX) {
        List<int[]> lst = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 1 ; i < sharkSize ; i++) {
            if (fish.get(i).isEmpty()) continue;
            for (int[] index : fish.get(i)) {
                lst.add(index);
            }
        }
        if (lst.isEmpty()) return queue;
        Collections.sort(lst, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        for (int[] index : lst) {
            queue.add(new Node(index, index[0], index[1], 0));
        }
        return queue;
    }

}

class Node {
    public int[] index;
    public int y;
    public int x;
    public int cnt;
    public Node(int[] arr, int y, int x, int cnt) {
        this.index = arr;
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}
