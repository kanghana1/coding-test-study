package org.week4.impl_algorithm;

import java.io.IOException;
import java.util.*;

public class Greedy {
    /*
     * Greedy 문제 중 가장 유명한 knapsack problem을 이용해 알고리즘을 구현하려 한다.
     * knapsack problem은 다음과 같은 조건을 갖는다.
     * - 배낭에 담을 수 있는 물건의 무게는 정해져있다.
     * - 제한된 무게 안쪽으로 가장 높은 가격의 물건들을 담으면 된다.
     *
     * */
    public static void main(String[] args) throws IOException {
        int limitWeight = 20; // 최대 무게를 20으로 가정한다.
        Stuff[] stuffs = putStuff();
        double ans = greedy(limitWeight, stuffs);
        System.out.printf("%.2f", ans);
    }

    private static double greedy(int maximum, Stuff[] stuffs) {
        Arrays.sort(stuffs, (a, b) -> Double.compare(b.price / b.weight, a.price / a.weight));

        double tWeight = 0;
        double tPrice = 0;

        for (Stuff stuff : stuffs) {
            if (maximum >= tWeight + stuff.weight) {
                tWeight += stuff.weight;
                tPrice += stuff.price;
            } else {
                // 일부분만 담기
                tPrice += (((maximum - tWeight) / stuff.weight) * stuff.price);
                tWeight = maximum;
                break;
            }
        }

        return tPrice;
    }

    private static Stuff[] putStuff() {
        Stuff[] stuffs = new Stuff[10]; // 물건의 개수는 10개로 가정한다.
        stuffs[0] = new Stuff(3, 10);
        stuffs[1] = new Stuff(6, 8);
        stuffs[2] = new Stuff(2, 5);
        stuffs[3] = new Stuff(10, 12);
        stuffs[4] = new Stuff(5, 10);
        stuffs[5] = new Stuff(4, 4);
        stuffs[6] = new Stuff(8, 11);
        stuffs[7] = new Stuff(3, 9);
        stuffs[8] = new Stuff(9, 15);
        stuffs[9] = new Stuff(7, 10);
        return stuffs;
    }

    static class Stuff {
        double weight;
        double price;
        public Stuff(int w, int p) {
            this.weight = w;
            this.price = p;
        }
    }


}
