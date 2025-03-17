package org.week1.impl_algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dfs {
    /*
    * # 구현 방법
    * - 순환호출 (재귀)
    * - 명시적 스택 사용 (스택)
    * */

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
    static boolean[] isVisit;
    static StringBuilder sbRecursive = new StringBuilder();
    static StringBuilder sbStack = new StringBuilder();

    public static void main(String[] args) {
        makeGraph();

        isVisit = new boolean[7];
        dfsForRecursive(2);

        isVisit = new boolean[7];
        dfsForStack(2);

        System.out.println(sbRecursive); // result : 2 1 3 4 5 6
        System.out.println(sbStack); // result : 2 3 6 4 5 1
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

    // 구현방법 1. 재귀 이용
    public static void dfsForRecursive(int start) {
        isVisit[start] = true;
        sbRecursive.append(start).append(" ");
        List<Integer> child = graph.get(start);

        if (child.isEmpty()) return;
        for (int c : child) {
            if (!isVisit[c]) dfsForRecursive(c);
        }
    }

    public static void dfsForStack(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            Integer now = stack.pop();
            isVisit[now] = true;
            sbStack.append(now).append(" ");
            List<Integer> child = graph.get(now);

            if (child.isEmpty()) return;
            for (int c : child) {
                if (!isVisit[c]) {
                    stack.push(c);
                }
            }
        }
    }

}
