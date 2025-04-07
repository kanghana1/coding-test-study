// 최소의 강의실을 사용해서 모든 수업을 가능하게 해야한다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture implements Comparable<Lecture>{
    int start;
    int end;

    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Lecture l){
        return this.start - l.start;
    }
}

// 정렬을 한번만 할 수도 있는 거 아닐까? nlogn,,?
// (1, 5) (2, 6), (3,7) 이런 식으로, 선택한 거랑 개수가 다른 것들의 개수를 단순히 세면?
// 어쨌든 prevEnd 보다 start가 작으면은 안되는 거 아닌가? 그냥 다른 거를 무작정 세줘도 되려나?
// 무작정 세기만 하면은, 최소 강의실 개수가 아니게됨.

// -> 지금까지 센 거는 final 기준으로 해서 결국에는 최대한 많은 강의를 진행하도록 하는 게 우선.
// -> 근데 문제에서는 **모든 강의**를 **최소**로 진행해야함.
// -> 결국에 모든 강의를 어차피 진행해야 한다면, 먼저 시작하는 게 우선이겠다. -> 기준이 start.
// 근데 그럼 정렬 어케함 ? 새로운 회의실이 생길 때마다 리스트를 추가해야하는 부분?

// 모두 사용 중이면 추가해야하고,
// 끝난 곳 있으면은 거기다가 바로 넣으면 되고,

// 따로따로 관리할 생각이 아니라, 수업 중인 것만 pq 에 넣으면 되는건가?
// 근데 그렇게 되면 pq에서 안 끝나고 추가되기만 하면? <- 결국에는 pq에는 끝나는 시간을 기준으로 해서 빨리 끝나는 순대로 넣을 수 있도록 한다.

public class BOJ_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(start, end));
        }

        Collections.sort(lectures);

        pq.add(lectures.get(0));

        for (int i = 1; i < n; i++){
            Lecture prev = pq.peek();
            Lecture curr = lectures.get(i);

            if (prev.end <= curr.start) {
                pq.poll();
            }
            pq.add(curr);
        }
        System.out.println(pq.size());
    }
}
