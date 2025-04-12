import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_18290{
    public static int n, m, k;
    public static int[][] used;
    public static int[][] map;
    
    public static ArrayList<int[]> temp = new ArrayList<>();
    public static int ans = -40000; // 문제 제대로 안 읽어서 최솟값을 0으로 햇었음.
    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        used = new int[n][m];

        for (int row = 0; row < n; row++){
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // for (int i = 0; i < n; i++){
        //     for (int j = 0; j < m; j++){
        //         choose(i, j, 0); 
        //         used[i][j] += 1;
        //     }
            
        // }
        choose(0, 0, 0);
        System.out.println(ans);
        
    }

    public static void choose(int currNum, int row, int col){
        if (currNum == k){
            // print(); 
            ans = Math.max(ans, calc());
            
            return;
        }

        // 고른 좌표에 대해서 인접한 칸들을 used로 처리해서 추가적으로 탐지 X
        // used가 아닌 모든 좌표들 중에서 <- 선택할 수 있는 것들
        // 선택
        
        // 0, 0을 무조건 선택하는 그런 환경이...
        for (int i = row; i < n; i++){
            for (int j = (i == row ? col : 0); j < m; j++){
                // System.out.print(used[i][j]);
                if (used[i][j] == 0){
                    temp.add(new int[]{i, j});
                    color(i, j, 1);
                    choose(currNum + 1, i, j);
                    color(i, j, -1); // 선택하고 나서, 그 다음에 없애는 작업이 필요했음. 첫번째 오류
                    temp.remove(temp.size() - 1);
                }
            }
        }
        
    }

    // 인접한 좌표들을 used 처리하는 함수.
    public static void color(int row, int col, int mode){
        int[] drow = {0, 1, 0, -1, 0};
        int[] dcol = {0, 0, 1, 0, -1};

        for (int i = 0; i < 4; i++){
            int nrow = drow[i] + row;
            int ncol = dcol[i] + col;

            if (!inRange(nrow, ncol))
                continue;

            used[nrow][ncol] += mode;
        }
    }
    public static void print(){
        for (int i = 0; i < temp.size(); i++){
            System.out.print(temp.get(i)[0] +""+ temp.get(i)[1] + " ");
        }
        System.out.print(calc());
        System.out.println();
    }

    public static boolean inRange(int row, int col){
        if (0 <= row && row < n && 0 <= col && col < m)
            return true;
        return false;
    }

    public static int calc(){
        int total  = 0;
        for (int i = 0; i < temp.size(); i++){
            int[] indexes = temp.get(i);
            int x = indexes[0], y = indexes[1];
            total += map[x][y];
        }

        return total;
    }
}
