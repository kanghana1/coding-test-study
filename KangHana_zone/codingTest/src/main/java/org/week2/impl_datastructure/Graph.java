package org.week2.impl_datastructure;

import java.util.ArrayList;
import java.util.List;

// 무방향그래프를 기준
// 노드는 1~6까지 존재
public class Graph {
    static List<List<Integer>> lst = new ArrayList<>();
    static int maxNode = 6;

    public static void makeGraph() {
        for (int i = 0 ; i <= maxNode ; i++) {
            lst.add(new ArrayList<>());
        }
    }

    // a와 b를 연결
    public static void link(int a, int b) {
        if (a > 6 || b > 6 || a < 1 || b < 1) {
            throw new IndexOutOfBoundsException();
        }
        // 무방향이므로 양쪽 다 넣기
        lst.get(a).add(b);
        lst.get(b).add(a);
    }

    public static void main(String[] args) {
        makeGraph();
        link(1, 3);
        link(1, 5);
        link(2, 4);
        link(2, 5);
        link(3, 4);
        link(4, 6);
        link(5, 6);
    }




}
