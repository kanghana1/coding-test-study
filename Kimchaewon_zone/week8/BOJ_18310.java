import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 집이 최대한 많은 곳에 안테나를 설치하는 게 최적의 답일 것이다.
// -> 발전시켜서 안테나 기준 (왼쪽 집의 개수) (오른쪽 집의 개수) -> 0에 근사할 때
// (오른 쪽 집의 개수) - (왼쪽 집의 개수) > 0 이다가 <= 0 되는 그 지점

public class BOJ_18310 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] countHome = new int[100000 + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int house = Integer.parseInt(st.nextToken());
            countHome[house]++;
        }

        int left = 0;
        int right = N;
        for (int i = 1; i <= 100000; i++){
            // i는 왼쪽에서부터 오른쪽으로 간다.
            while (countHome[i]-- > 0){
                left++;
                right--;
            }

            if (right - left <= 0){
                System.out.println(i);
                break;
            }
        }

    }
}
