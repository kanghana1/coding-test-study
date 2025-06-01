import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1700{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 멀티탭의 개수
        int k = Integer.parseInt(st.nextToken()); // 전자기기의 개수
        
        List<List<Integer>> order = new ArrayList<>();
        for (int i = 0; i <= k; i++){
            order.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        int[] plugOrder = new int[k + 1];
        for (int i = 1; i <= k; i++){
            plugOrder[i] = Integer.parseInt(st.nextToken());
            order.get(plugOrder[i]).add(i);
        }
        
        Set<Integer> plugged = new HashSet<>();
        
        int ans = 0;
        for (int i = 1; i <= k; i++){
            int curr = plugOrder[i];
            order.get(curr).remove(0); // 현재 순서 지우기
            
            if (plugged.contains(curr))
                continue;
            
            // 아직 플러그가 꽉 차지 않았다면,
            if (plugged.size() < n){
                plugged.add(curr);
            } else{
                // 플러그가 꽉 차서 가장 나중에 쓸 전자기기를 빼야하는 상황이라면,
                int removedDevice = -1;
                int later = -1;
                
                for (int device : plugged){
                    int next = order.get(device).isEmpty() ? Integer.MAX_VALUE : order.get(device).get(0); // 현재 device의 다음에 사용할 순서
                    if (next > later){ // 가장 나중에 사용할 순서라면,
                        later = next;
                        removedDevice = device;
                    }
                }
                plugged.remove(removedDevice); // 제거하고,
                plugged.add(curr); // 현재를 넣고,
                ans++;
            }
            
        }
        System.out.print(ans);
    }
}
