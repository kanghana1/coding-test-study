package org.week4.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;

/*
* 자료형의 범위때문에 시간을 끌어버렸던 문제 ㅜㅡㅜ
* 이 문제에서 해야할 일은 크게 두 가지 였다.
* 1. 필요한 면 개수 파악하기
*   - 면이 3개 보여지는 경우의 수 세기
*   - 면이 2개 보여지는 경우의 수 세기
*   - 면이 1개 보여지는 경우의 수 세기
* 2. 면 개수 당 최소 합 구하기
*   주사위의 면 값이 주어지기 때문에, 개수별 조합 중 최소합이 되는 경우를 찾아야 함
*   다행히 A : 0, B : 1, C : 2 ... 인데, A, F가 마주보고 B와 E, C와 D가 마주보게 그림이 그려져 있음
*   -> 인덱스의 합이 5인 경우 마주보게 되도록 설계되어 있어 수월하게 코드를 짤 수 있었음
*
* 필요한 면 개수 파악은 N을 이용한 함수식을 작성했음
* - 면이 3개 보여지는 경우는, 맨 위 모서리들 -> 총 4개
* - 면이 2개 보여지는 경우의 수는, 4(N - 2) + 4(N - 1) = 8N - 12
* - 면이 1개 보여지는 경우의 수는, 4((N - 2) ^ 2 + (N - 2)) + (N - 2) ^ 2
*                              = 5(N - 2)^2 + 4(N - 2)
*
* 면 개수 구하는 걸 함수로 빼서 가독성을 올리려했는데, 리턴을 int형으로 잡아서 자꾸 엉뚱한 답이 나왔다.
* 여기서 시간을 너무 많이 소비해서 슬퍼짐 ㅠ
*
* */
public class BOJ_1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];
        for (int i = 0 ; i < 6 ; i++) {
            dice[i] = Integer.parseInt(st.nextToken()); // 배열값 받기
        }

        if (N == 1) { // N = 1의 경우에는 가장 큰 수 한 개를 제외한 모든 면의 합이 답이 됨
            long sum = 0;
            long max = 0;
            for (int i = 0 ; i < 6 ; i++) {
                sum += dice[i];
                max = max(max, dice[i]);
            }
            System.out.println(sum - max);
            return;
        }
        long ans = minSum(N, dice); // 로직
        System.out.println(ans);
    }

    public static long minSum(int N, int[] dice) {
        long ans = 0;
        long sumOne = Long.MAX_VALUE; // 한 개를 선택할 때 최소 -> 그냥 6개의 수 중 최소값
        long sumTwo = Long.MAX_VALUE; // 마주보는 면을 제외한 면의 2개 조합 중 최소
        long sumThree = 0; // A,F / B,E / C,D 중 작은 값 한 개씩 고른 합 -> 모서리를 만드는 최소 조합

        for (int i = 0 ; i < 6 ; i++) {
            sumOne = min(sumOne, dice[i]);
            for (int j = i + 1 ; j < 6 ; j++) {
                if (i + j != 5) { // 마주보는 경우 제외
                    sumTwo = min(sumTwo, dice[i] + dice[j]);
                }
            }
            if (i <= 2) { // 0, 1, 2의 경우만 더해주면 됨, 그 이상이면 중복값 더해짐
                sumThree += min(dice[i], dice[5 - i]);
            }
        }

        // 각 합 * 개수를 누적
        ans += (useOneBlock(N) * sumOne);
        ans += useTwoBlock(N) * sumTwo;
        ans += useThreeBlock(N) * sumThree;
        return ans;
    }
    // 개수 구하는 함수들
    public static int useThreeBlock(int N) {
        return 4;
    }

    public static long useTwoBlock(long N) {
        return 8 * (N - 2) + 4;
    }

    public static long useOneBlock(long N) {
        return 5 * (N - 2) * (N - 2) + 4 * (N - 2);
    }
}
