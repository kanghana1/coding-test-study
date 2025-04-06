import java.util.*;

class Activity{
    int start;
    int finish;

    Activity(int start, int finish){
        this.start = start;
        this.finish = finish;
    }
}

public class GreedyImpl {
    public static void main(String[] args) {
        // 1. 활동의 개수를 최대한 많이 배정하고 싶다.
        // 2. 종료시간이 빠른 순으로 정렬한다. (Collections 로 정렬을 해도 되지만, PriorityQueue 사용)
        // -> 어떻게 최적이라고 보장할 수 있는가?
        //    -> 가장 빨리 끝나는 활동을 선택한다면, 그 이후에 배정할 수 있는 시간이 최대한 많이 남는다.
        //    -> 만약 더 늦게 끝나는 활동을 먼저 선택했다고 해도, 빨리 끝나는 활동으로 그것을 교체한다면,
        //    -> 뒤이어 선택할 수 있는 활동의 개수가 당연히 줄어들지 않는다.
        //    -> 따라서, 언제나 최대 개수의 활동을 배정할 수 있다.

        // -> 시작 시간을 고려하지 않아도 되는가?
        // -> 종료 시간이 빠른 순으로 정렬되었다면,
        // -> 모두 종료시간이 같으므로 그 이후로 선택할 수 있는 활동 시간의 범위가 같다.
        // -> 즉, '최대 개수'를 구하는 데에는 같은 종료 시간을 가진 활동중에서 어떤 것을 골라도 차이가 없으므로 시작 시간을 고려할 필요가 없다.

        PriorityQueue<Activity> activities = new PriorityQueue<>(new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o1.finish - o2.finish;
            }
        });

        activities.add(new Activity(1, 4));
        activities.add(new Activity(8, 9));
        activities.add(new Activity(5, 7));

        // 예외 케이스(잘못된 입력 형식은 고려하지 않도록 한다)
        activities.add(new Activity(5, 5)); // 활동시간 == 종료시간
        // 종료 시간이 같을 때
        activities.add(new Activity(3, 5));
        activities.add(new Activity(2, 5));
        activities.add(new Activity(4, 5));

        Activity prev = activities.poll();
        int cnt = 1;
        print(cnt, prev);

        // 3. 현재 활동 시작 시간이 이전 활동의 종료시간보다 같거나 크면 선택.
        while (!activities.isEmpty()) {
            Activity current = activities.poll();
            if (current.start >= prev.finish){
                cnt += 1;
                print(cnt, current);
                prev = current;
            }
        }
    }

    static void print(int cnt, Activity curr) {
        System.out.println("활동 시작 시간 : " + curr.start + " 활동 마감 시간 : " + curr.finish + "\n현재 선택된 활동 개수 : " + cnt);
    }
}
