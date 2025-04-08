package org.week4.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Lect> lst = new ArrayList<>();
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            Lect l = new Lect(s,e);
            lst.add(l);
        }
        Collections.sort(lst, new Comparator<Lect>() {
            @Override
            public int compare(Lect o1, Lect o2) {
                if (o1.start != o2.start) return o1.start - o2.start;
                return o1.end - o2.end;
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(lst.get(0).end);
        for (int i = 1 ; i < n ; i++) {
            if (queue.peek() <= lst.get(i).start) queue.poll();
            queue.add(lst.get(i).end);
        }
        System.out.println(queue.size());
    }
}
class Lect {
    int start;
    int end;
    public Lect(int s, int e) {
        this.start = s;
        this.end = e;
    }
}