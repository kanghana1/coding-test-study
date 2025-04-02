package org.week4.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_2437 {
    /*
     * 내 머리통에서는 도저히 코드가 나오지 않았다
     * 1차시도 - dp ? -> max값으로 생각할 때 1000 * 100만 짜리 배열을 생성해야함 -> 불가능
     * 2차시도 - 모든 경우 탐색 -> 시간초과 100000%
     *
     * 그래서 게시판을 찾았는데 아래와 같은 명제를 이용하더라, 전제는 다음과 같다.
     * 1. 배열이 오름차순 정렬되어있음 **
     *
     * 현재 인덱스 전 누적합을 sum 이라고 할 때, sum + 1 == 현재값이라면
     * 기존 범위 (1 ~ sum + 현재값) 까지의 모든 수를 만들 수 있다.
     *
     * 만약 sum + 1 < arr[i]라면, sum + 1을 만들 수 없다.
     *
     * 예 | 1 2 4 10의 경우를 보면,
     * 1 -> 1만들 수 있음
     * 1 + 2 = 3 -> 1~3 만들 수 있음
     * 3 + 1 == 4 -> sum + 4인 1~7까지 수 만들 수 있음
     * sum(7) + 1 < 10 이기 때문에, 8을 만들 수 없음
     *
     * 해당 명제를 이용하여 문제를 풀었다
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> lst = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(lst);

        long sum = 0;
        long ans = 0;
        for (int i = 0 ; i < N ; i++) {
            if (sum + 1 < lst.get(i)) {
                ans = sum + 1;
                break;
            }
            sum += lst.get(i);
        }
        if (ans == 0) ans = sum + 1;
        System.out.println(ans);
    }
}
