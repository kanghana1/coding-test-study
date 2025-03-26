package org.week3.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 이 문제는 BFS를 이용해 풀었다.
* 다익스트라로 풀려 했으나, edge에 대한 가중치가 있는 경우 그 가중치의 최소로 이동해야
* 하는 경우일 때 (최단거리) 다익스트라를 이용하게 된다.
* 그러나 이 문제의 경우 간선에 대한 가중치가 존재하지 않기 때문에 다익스트라를 이용하지 않았다.
* (아무래도 최단거리의 의미가 다르지 않았나 ..)
*
* 어짜피 모든 노드를 모두 방문해야 하기 때문에 BFS를 이용했고, 그리 어렵지 않았다.
* 다만, 나는 거리를 담는 배열을 만들었고, 초기화는 0으로 그냥 둔 후, (어짜피 거리는 1 이상이니)
* 0이면 (방문하지 않았으면) 거리를 갱신해서 최단거리로만 갱신되도록 했다.
* 근데,
* 1 2
* 2 1
* 처럼 양방향이 되는 경우 1에서 시작하면 1 -> 1 이 거리 2가 담기는 경우의 수가 생긴다.
* 그래서 탐색 후, 자기자신의 거리는 0으로 다시 초기화 해주었다.
* (처음부터 -1로 초기화 하는 게 깔끔할 거 같으나 편의상 이렇게 해결했다)
* */

public class BOJ_18352 {
    static List<List<Integer>> lst = new ArrayList<>(); // 단방향관계 담기
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int dis = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0 ; i < edge ; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lst.get(s).add(e); // 단방향
        }

        if (lst.get(start).isEmpty()) {
            System.out.println(-1);
            return;
        }
        findShort(start, dis);
        System.out.println(sb);
    }

    private static void findShort(int start, int dis) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int[] dist = new int[N + 1]; // 거리 담는 배열 : 초기화 0인 상태

        while (!queue.isEmpty()) { // bfs 로직
            int node = queue.poll();
            List<Integer> child = lst.get(node);
            if (child.isEmpty()) {
                continue;
            }
            for (int ch : child) {
                if (dist[ch] == 0) {
                    dist[ch] = dist[node] + 1;
                    queue.add(ch);
                }
            }
        }

        boolean exist = false;
        dist[start] = 0; // 로직 종료 후 자기자신 0으로 초기화
        for (int i = 1 ; i <= N ; i++) {
            if (dist[i] == dis) { // 원하는 거리일 때 builder에 저장
                sb.append(i).append("\n");
                exist = true;
            }
        }
        if (!exist) sb.append(-1); // 원하는 거리인 노드가 없으면 -1
    }
}
