import java.util.*;

class Node{
    String name;
    int distance;

    public Node(String name, int distance){
        this.name = name;
        this.distance = distance;
    }
}
public class DijkstraImpl {
    public static void main(String[] args) {
        Map<String, List<Node>> graph = new HashMap<>();
        
        // 그래프 생성
        graph.put("A", new ArrayList<>(Arrays.asList(
                new Node("B", 2), new Node("C", 5)
        )));
        graph.put("B", new ArrayList<>(Arrays.asList(
                new Node("A", 2), new Node("C", 1), new Node("D", 2)
        )));
        graph.put("C", new ArrayList<>(Arrays.asList(
                new Node("A", 5), new Node("B", 1), new Node("D", 3)
        )));
        graph.put("D", new ArrayList<>(Arrays.asList(
                new Node("B", 2), new Node("C", 3), new Node("E", 1)
        )));
        graph.put("E", new ArrayList<>(Arrays.asList(
                new Node("D", 1)
        )));

        // 다익스트라 실행
        String start = "A";
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> prev = new HashMap<>(); // 경로 추적
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance)); // 클래스로 만들었기 때문에 n -> n.distance를 추가해서 최소힙으로 만듭니다.

        for (String key : graph.keySet())
            distance.put(key, Integer.MAX_VALUE); // 각각의 거리를 최대로

        distance.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String cur_name = current.name;
            int cur_dist = current.distance;

            if (cur_dist > distance.get(cur_name)) continue;

            for (Node next : graph.get(cur_name)) {
                int next_dist = cur_dist + next.distance;
                if (next_dist < distance.get(next.name)) {
                    distance.put(next.name, next_dist);
                    prev.put(next.name, current.name);
                    pq.add(new Node(next.name, next_dist));
                }
            }
        }

        System.out.println("최단 거리 및 경로 :");
        for (String key : graph.keySet()){
            if (distance.get(key) == Integer.MAX_VALUE)
                System.out.println(start + " -> " + key + " : 도달 불가");
            else{
                System.out.println(start + " -> " + key + " : 거리 " + distance.get(key));
                System.out.print("경로 : ");
                printPath(prev, start, key);
                System.out.println();
            }
        }
    }
    private static void printPath(Map<String, String> prev, String start, String key) {
        LinkedList<String> path = new LinkedList<>();
        String curr = key;

        while (curr != null && !curr.equals(start)){
            path.addFirst(curr);
            curr = prev.get(curr);
        }

        if (curr == null){
            System.out.print("경로 없음");
            return;
        }

        path.addFirst(start);
        for (int i = 0; i < path.size(); i++){
            System.out.print(path.get(i) + " ");
            if (i != path.size() - 1) System.out.print(" -> ");
        }
    }
}
