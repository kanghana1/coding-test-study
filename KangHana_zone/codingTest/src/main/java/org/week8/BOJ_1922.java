package org.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 전형적인 그래프 문제였다. 그 중에서도 MST 문제라서 나는 다익스트라를 이용했다.
* 우선순위 큐를 node의 price 기준으로 오름차순 정렬하도록 생성한 후, 연결된 노드를 큐에 넣으면 된다.
* 최소신장트리의 특징은 edge의 개수가 node의 개수보다 1개 적기 때문에, 그 부분을 end 포인트로 잡아 구현했다.
* */

public class BOJ_1922 {
    static List<List<Node>> graph = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        for (int i = 0 ; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pc1 = Integer.parseInt(st.nextToken());
            int pc2 = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            graph.get(pc1).add(new Node(pc2, price)); // 양방향 그래프
            graph.get(pc2).add(new Node(pc1, price));
        }
        int ans = explore(); // 다익스트라 탐색 시작
        System.out.println(ans);
    }

    private static int explore() {
        Queue<Node> queue = new PriorityQueue<>(); // 우선순위 큐
        boolean[] isVisit = new boolean[N + 1];
        int sum = 0;
        int edge = -1;
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (edge == N - 1) break;
            if (isVisit[node.dest]) continue;

            isVisit[node.dest] = true; // 방문한 적 없는 노드일 경우 방문 설정
            sum += node.price; // 가중치 추가
            edge++; // edge 추가

            for (Node next : graph.get(node.dest)) {
                if (!isVisit[next.dest]) {
                    queue.add(next);
                }
            }
        }
        return sum;
    }
}

class Node implements Comparable<Node>{
    int dest;
    int price;
    public Node(int d, int p) {
        this.dest = d;
        this.price = p;
    }

    @Override
    public int compareTo(Node o) {
        return this.price - o.price;
    }
}