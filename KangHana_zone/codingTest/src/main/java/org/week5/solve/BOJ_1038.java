package org.week5.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* 처음에는 모든 숫자를 작은 수 (10) 부터 탐색하면서 해당 수가 감소하는 수인지 판단하는 방식으로
* 알고리즘을 짰는데, 시간초과 문제가 생겼다.
*
* 그래서 이것저것 찾아봤는데, 감소하는 수 개수가 1500개도 안 된다는 걸 알게 되었고, 그럼 그냥
* 리스트에 감소하는 수를 다 담고 정렬을 해서 원하는 인덱스의 감소하는 수를 뽑아와도 되겠다 싶었다.
* (기존의 모든 수를 다 탐색하는 방법을 쓰면 엄청 커진다. 가장 큰 감소하는 수인 9876543210은 int의 범위를 넘는다)
*
* 그래서 그냥 재귀를 이용해서 모든 감소하는 수를 리스트에 담아버렸다!
*
* */
public class BOJ_1038 {
    static List<Long> dec = new ArrayList<>(); // 감소하는 수를 담을 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0 ; i <= 9 ; i++) {
            decNum((long) i); // 감소하는 수 탐색할 함수. 파라미터의 수보다 작은 수만 뒤에 붙을 수 있게 처리
        }
        if (N >= dec.size()) { // 리스트 길이보다 N이 크다는 건 감소하는 수의 개수 범위를 넘는다는거 -> -1
            System.out.println(-1);
            return;
        }
        Collections.sort(dec);
        System.out.println(dec.get(N));
    }

    private static void decNum(long n) {
        dec.add(n);
        long now = n % 10; // 숫자의 일의자리 수
        for (int i = 0 ; i < now ; i++) { // now보다 작은 수만 일의자리로 갱신 -> 나머지는 10을 곱해서 자리 이동
            decNum(n * 10 + i);
        }
    }
}
