// 자바에서 그래프를 구현할 때는 두 가지 방법으로 구현할 수 있습니다.
// 첫 번째 방식은 2차원 행렬로 표시하는 방식이고,
// 두 번째 방식은 연결 리스트로 표시하는 방식입니다.
// 간단하게 그래프만을 구현해보았습니다. 

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static void main(String[] args) {
        int n = 4;
        int[][] graph1 = twoDimensionalArray(n);
        for (int i = 0; i < graph1.length; i++) {
            for (int j = 0; j < graph1[i].length; j++) {
                System.out.print(graph1[i][j] + " ");
            }
            System.out.println();
        }

        List<List<Integer>> graph2 = new ArrayList<>();
        linkedListImpl(graph2, n);
        for (int i = 1; i < graph2.size(); i++) {
            System.out.print(i + "와 연결되어 있는 노드들 : ");
            for (int j = 0; j < graph2.get(i).size(); j++) {
                System.out.print(graph2.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    static void linkedListImpl(List<List<Integer>> graph, int n) {
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        putNodetoLinkedList(graph, 1, 2);
        putNodetoLinkedList(graph, 4, 1);
        putNodetoLinkedList(graph, 3, 1);
        putNodetoLinkedList(graph, 2, 4);
        putNodetoLinkedList(graph, 3, 4);
        putNodetoLinkedList(graph, 2, 3);

    }

    static void putNodetoLinkedList(List<List<Integer>> graph, int x, int y) {
        graph.get(x).add(y);
        graph.get(y).add(x);
    }


    static int[][] twoDimensionalArray(int n) {
        int[][] graph = new int[n + 1][n + 1];
        putNodeToArray(graph, 1, 2);
        putNodeToArray(graph, 4, 1);
        putNodeToArray(graph, 3, 1);
        putNodeToArray(graph, 2, 4);
        putNodeToArray(graph, 3, 4);
        putNodeToArray(graph, 2, 3);

        return graph;
    }

    static void putNodeToArray(int[][] graph, int x, int y) {
        graph[x][y] = 1;
        graph[y][x] = 1;
    }
}
