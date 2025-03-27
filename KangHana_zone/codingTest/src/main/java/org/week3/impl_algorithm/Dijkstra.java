package org.week3.impl_algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// resources/img/weighted-graph.png 참고
public class Dijkstra {
    static List<List<int[]>> lst = new ArrayList<>();
    final static int N = 5;
    public static void main(String[] args) {
        for (int i = 0 ; i < N ; i++) {
            lst.add(new ArrayList<>());
        }

        fillGraph();
        int ans = dijkstra(1, 3); // a에서 b로가는 최단경로 (min-weight)
        System.out.println(ans);
    }

    private static int dijkstra(int a, int b) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        queue.add(new int[]{a, 0});
        boolean[] isVisit = new boolean[N];
        isVisit[a] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[0] == b) return node[1];

            if (lst.get(node[0]).isEmpty()) continue;
            for (int[] ch : lst.get(node[0])) {
                if (!isVisit[ch[0]]) {
                    queue.add(new int[]{ch[0], node[1] + ch[1]});
                    isVisit[ch[0]] = true;
                }
            }
        }
        return -1;
    }

    private static void fillGraph() {
        lst.get(0).add(new int[]{1, 3});
        lst.get(0).add(new int[]{2, 10});
        lst.get(0).add(new int[]{3, 11});
        lst.get(1).add(new int[]{0, 3});
        lst.get(1).add(new int[]{4, 13});
        lst.get(2).add(new int[]{0, 10});
        lst.get(2).add(new int[]{3, 23});
        lst.get(2).add(new int[]{4, 4});
        lst.get(3).add(new int[]{0, 11});
        lst.get(3).add(new int[]{2, 23});
        lst.get(3).add(new int[]{4, 2});
        lst.get(4).add(new int[]{1, 13});
        lst.get(4).add(new int[]{2, 4});
        lst.get(4).add(new int[]{3, 2});
    }
}
