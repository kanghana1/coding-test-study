import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

// 이렇게 풀어도 과연 시간초과가 안날까 싶었습니다.
// 풀고 나서 다른 사람들이 어떻게 풀었는지 확인해보니, for문으로 확인하지 않고, 배열을 통해서 이미 썼는지 true, false로 확인하는 것을 보았습니다.
// 그렇게 배열을 사용한다면은, O(1)만으로도 중복을 확인할 수 있으니, 더 좋은 코드라는 생각이 들었습니다.

public class BOJ_15654{
    public static int n, m;
    public static ArrayList<Integer> input = new ArrayList<>(); // size = n
    public static ArrayList<Integer> answer = new ArrayList<>(); // size = m
    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            input.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(input); // 오름차순으로 정렬

        choose(0);
    }

    public static void choose(int currNum){
        if (currNum == m){
            print();
            return;
        }

        for (int idx = 0; idx < n; idx++){
            int select = input.get(idx);

            if (currNum == 0 || !include(select)){
                answer.add(select);
                choose(currNum + 1);
                answer.remove(answer.size() - 1);
            }
        }
    }

    public static boolean include(int num){
        for (int i = 0; i < answer.size(); i++){
            if (answer.get(i) == num)
                return true;
        }
        return false;
    }

    public static void print(){
        for (int i = 0; i < answer.size(); i++)
            System.out.print(answer.get(i) + " ");
        System.out.println();
    }
}
