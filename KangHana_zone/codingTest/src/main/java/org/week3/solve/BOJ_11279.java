package org.week3.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 0이 입력되면 -> 리스트에 들어있는 값 중 최대값을 출력 / 비어있으면 0 출력
* 자연수가 입력되면 -> 해당 값 리스트에 추가
*
* 최대값을 출력해야 하므로, 우선순위 큐를 내림차순으로 정렬하도록 생성하면 끝난다.
* 우선순위 큐를 쓰게 되니 힙으로 구현한 자료구조를 이용해 힙을 푸는 특이한 모양새가 되어버렸지만
* 출제 의도도 우선순위큐를 이용하는 거 같으니 넘어가기로 햇다 ^^7
*
* */
public class BOJ_11279 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // 내림차순으로 설정
            }
        });

        for (int i = 0 ; i < N ; i++) {
            int a = Integer.parseInt(br.readLine()); // 입력값
            if (a == 0) {
                if (queue.isEmpty()) {
                    sb.append(0);
                } else sb.append(queue.poll());
                sb.append("\n");
            } else {
                queue.add(a);
            }
        }
        System.out.println(sb);
    }
}
