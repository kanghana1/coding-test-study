import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 다른 모든 지원자와 비교했을 때, 어느 것 하나라도 높다면 선발한다.
// 다른 모든 지원자와 비교했을 때, 어느 것도 높지 않다면 탈락이다.
// 이해가 안되었었는데, 5, 5 를 5점 5점으로 봐서 그랬던거였다.

// 로직 : 서류 순대로 뽑는데, 먼저 서류가 높아서 뽑힌 사람보다 인터뷰 점수가 낮으면은 탈락.
// 즉, 먼저 서류가 높아서 뽑힌 사람 중에서 제일 인터뷰 점수가 낮은 사람보다만 높으면 된다.

class Score implements Comparable<Score>{
    int interview;
    int paper;

    public Score(int paper, int interview){
        this.interview = interview;
        this.paper = paper;
    }

    @Override
    public int compareTo(Score s){
        return this.paper- s.paper;
    }
}

public class BOJ_1946{
    public static int T;
    public static int n;
    public static ArrayList<Score> scores;
    public static BufferedReader br;
    public static StringTokenizer st;
    public static int cnt;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int i = 0 ; i < T; i++){

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            scores = new ArrayList<>();

            Input(n);
            Collections.sort(scores);
            Print(n);
        }
    }

    public static void Input(int n) throws IOException {
        for (int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int paper = Integer.parseInt(st.nextToken());
            int interview = Integer.parseInt(st.nextToken());
            scores.add(new Score(paper, interview));
        }
    }

    public static void Print(int n){
        int firstPaper = scores.get(0).paper;
        int firstInterview = scores.get(0).interview;
        int minimumInterview = firstInterview;
        cnt = 1;

        for (int i = 1 ; i < n; i++){
            if (scores.get(i).interview <= minimumInterview) {
                minimumInterview = Math.min(minimumInterview, scores.get(i).interview);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
