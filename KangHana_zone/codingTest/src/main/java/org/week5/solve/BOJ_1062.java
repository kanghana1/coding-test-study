package org.week5.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
* 조합과 백트래킹으로 풀었다.
* 처음에는 그냥 단어에서 필요한 글자를 뽑아서 조합하려했는데, for문에 재귀까지 쓰니 시간초과가 났다.
* 무엇보다 직관적으로 시간을 계산할 수가 없었다.
*
* 그래서 처음부터 알파벳의 조합을 정한 후, 그 조합으로 만들 수 있는 단어 개수를 세는 방식으로 풀었다.
* 이렇게하면 읽을 수 있는 알파벳을 그때그떄 수정하기도 편하고, 시간 계산이 수월했다!
*
* */
public class BOJ_1062 {
    static List<String> lst = new ArrayList<>(); // input 받은 문장을 앞 뒤 anta, tica를 자르고 넣음
    static boolean[] alphabet; // 읽을 수 있는 (방문한) 알파벳
    static int ans = 0; // 최대값이므로 갱신해 넣기위한 변수
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        alphabet = new boolean[26];
        if (M < 5) { // 꼭 들어가는 알파벳이 5개이므로, 그 이하라면 아무것도 읽을 수 없음
            System.out.println(0);
            return;
        }
        // 필수 단어 true처리
        alphabet['a' - 'a'] = true;
        alphabet['c' - 'a'] = true;
        alphabet['t' - 'a'] = true;
        alphabet['n' - 'a'] = true;
        alphabet['i' - 'a'] = true;

        // 필수 단어 제외하고 배울 수 있는 단어 개수
        M -= 5;
        for (int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            lst.add(s.substring(4, s.length() - 4)); // 필수인 부분 자르고 넣기
        }

        backtracking(0, 0);
        System.out.println(ans);
    }

    private static void backtracking(int idx, int cnt) {
        if (cnt == M) { // 새로 배울 수 있는 단어가 없고, 가진 단어로 조합해야 하는 경우
            readableCnt();
            return;
        }

        // 아직 더 배울 수 있는 경우
        for (int i = idx ; i < 26 ; i++) {
            if (!alphabet[i]) {
                alphabet[i] = true;
                backtracking(i + 1, cnt + 1);
                alphabet[i] = false;
            }
        }
    }

    private static void readableCnt() {
        int cnt = 0; // 읽을 수 있는 단어 개수
        for (String str : lst) {
            boolean canRead = true;
            for (int i = 0 ; i < str.length() ; i++) {
                if (!alphabet[str.charAt(i) - 'a']) {
                    canRead = false;
                    break;
                }
            }
            if (canRead) cnt++;
        }
        ans = Math.max(ans, cnt); // 갱신
    }
}
