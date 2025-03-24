package org.week2.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 간단했고 시야만 살짝 바꾸면 bfs로 풀 필요가 전혀 없던 문제였으나 bfs훈련중이라 bfs를 이용했다.
* 1년 전에 처음 이 문제를 풀었을 때에는 단순한 반복문을 이용했다. (while)
*
* A를 B로 바꾸는 문제라, 작은 수(A)에 2를 곱하는 경우, 1을 붙이는 경우 두 가지를
* bfs로 뻗어가는 식을 생각할 수 있지만, 반대로 B에서 A로 간다고 생각하면
* 두가지 경우로 갈라지지 않는다
*
* 예를 들어 2 -> 162의 경우를 보면
* 2에서 시작할 경우 2를 곱하는 경우와 1을 붙이는 경우, 각각 4와 21이 나오고,
* 이 수들에 또 2를 곱하는 경우와 1을 붙이는 경우를 따지다보면 2^N의 경우가 나온다.
*
* 반대로 162 -> 2를 만들면
* 162는 짝수이므로, 2로 나눈다.
* (1로 끝나는 경우와 2의 배수인 경우는 완전히 상충되는 경우라 한가지를 만족하면 다른 조건을 비교하지 않아도 된다.)
* 81은 1로 끝나므로 1을 뺀 후 10으로 나눈다.
* 8은 짝수이므로 2로 나눈다.
* 4는 짝수이므로 2로 나눈다.
* 2가 되었다
*
* 후자의 방법이 훨씬 경우의 수가 적고, 한가지 2의 배수 혹은 1로 끝나는지 여부 중
* 한 가지만 맞으면 되므로 반복문을 사용할 수도 있다!
*
* 시간/메모리 효율상 큰 차이는 없다
* 1. 반복문 사용 -> 128ms, 14296KB
* 2. BFS 사용 -> 104ms, 14188KB
* */

public class BOJ_16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int goal = Integer.parseInt(st.nextToken()); // 작은 수가 목표
        int start = Integer.parseInt(st.nextToken()); // 큰 수에서 시작
        System.out.println(bfs(start, goal));
    }

    private static int bfs(int start, int goal) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int val = node.val;
            if (val < goal) return -1; // 2로 나누거나 1을 뺐을 때 goal보다 작아질 수 있다. -> 이 경우는 불가능이므로 -1 출력
            if (val == goal) {
                return node.cnt;
            } else if (val % 2 == 0) {
                queue.add(new Node(val / 2, node.cnt + 1));
            } else if (val % 10 == 1) {
                queue.add(new Node((val - 1) / 10, node.cnt + 1));
            } else {
                return -1;
            }
        }
        return -1;
    }
}

class Node {
    int val;
    int cnt;
    public Node(int v, int c) {
        this.val = v;
        this.cnt = c;
    }
}

/*
## 반복문을 사용한 코드

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int init = parseInt(st.nextToken());
        String goal = st.nextToken();
        int cnt = 0;
        int a = 0;
        while (true) {
            if (parseInt(goal) == init) break;
            else if (parseInt(goal) < init || (goal.charAt(goal.length() - 1) != '1' && parseInt(goal) % 2 == 1) ) {
                cnt = -1;
                break;
            }
            if (goal.charAt(goal.length() - 1) == '1') {
                goal = goal.substring(0, goal.length() - 1);
                cnt++;
            }
            else if (parseInt(goal) % 2 == 0) {
                a = parseInt(goal) / 2;
                goal = String.valueOf(a);
                cnt++;
            }
        }
        if (init == Integer.parseInt(goal)) cnt++;
        System.out.println(cnt);
    }
}

* */
