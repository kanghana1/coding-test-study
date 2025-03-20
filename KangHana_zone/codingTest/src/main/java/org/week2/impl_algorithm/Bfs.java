package org.week2.impl_algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    /*
     * 1. 예를 들어 방문할 그래프 만들기
     *
     * 2 - 1
     * |
     * 3 - 4 - 5
     * |
     * 6
     *
     * 이라 가정
     * */

    static List<List<Integer>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        makeGraph();
        bfs(2);
        System.out.println(sb); // 2 1 3 4 6 5
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isVisit = new boolean[graph.size()];
        queue.add(start);
        isVisit[start] = true;

        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            sb.append(now).append(" ");
            List<Integer> child = graph.get(now);

            if (child.isEmpty()) {
                continue;
            }
            for (int ch : child) {
                if (!isVisit[ch]) {
                    queue.add(ch);
                    isVisit[ch] = true;
                }
            }
        }
    }

    // 예시 그래프 채우기
    public static void makeGraph() {
        for (int i = 0 ; i <= 6 ; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(3).add(6);
        graph.get(4).add(3);
        graph.get(4).add(5);
        graph.get(5).add(4);
        graph.get(6).add(3);
    }

}
