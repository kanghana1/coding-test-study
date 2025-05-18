// 약수를 구한 다음에,
// 약수의 합과 차의 평균이 자연수일 때,
// y의 값이 답
// G = y^2 - x^2 = (y - x)(y + x)
// y + x = a, y - x = b, y = (a + b) / 2, x = (a - b) / 2

// 8% -> -1인지 아닌지 안넣어서
// 9% -> Math.sqrt 일 거 같더라니,,, 아마도 수 커질 때 오류난듯. -> a * a < G로 변경함.
// 근데 왜 아직도 이게 투포인터인지는 잘 모르겠음.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1484 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();
        ArrayList<Integer> ans = new ArrayList<>();
        // 약수 구하기
        for (int a = 1; a * a < G; a++){
            if (G % a == 0){
                int b = G / a;
                if (b >= a && (b + a) % 2 == 0 && (b - a) % 2 == 0){
                    ans.add((b + a) / 2);
                }
            }
        }

        Collections.sort(ans);
        if (ans.size() == 0)
            System.out.println("-1");
        else{
            for (int elem : ans){
                System.out.println(elem);
            }
        }
    }
}
