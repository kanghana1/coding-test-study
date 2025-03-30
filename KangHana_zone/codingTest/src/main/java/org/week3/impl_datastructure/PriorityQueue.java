package org.week3.impl_datastructure;
// 힙을 이용한 우선순위 큐 구현
// 삽입은 comparator 유무에 따라 나눴는데 remove는.... 생략햇어요


import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> {

    private final Comparator<? super E> comparator;
    private Object[] arr;
    private int heapSize = 10;
    private int cnt = 0;

    public PriorityQueue(Comparator<? super E> comparator, int size) {
        this.comparator = comparator;
        this.arr = new Object[heapSize];
        this.heapSize = size;
    }
    public PriorityQueue() {
        this.comparator = null;
        this.arr = new Object[heapSize];
    }

    public boolean add(Object o) {
        if (cnt + 1 == arr.length) {
            resize(heapSize * 2);
        }

        siftUp(cnt + 1, o);
        cnt++;
        return true;
    }

    public E poll() {
        if (arr[1] == null) {
            return null;
        }

        E result = (E) arr[1];
        E target; // 배열 마지막 요소
        if (cnt == 1) target = null;
        else target = (E) arr[cnt];

        arr[cnt] = null;
        cnt--;
        siftDown(1, target);

        return result;
    }

    public int size() {
        return this.cnt;
    }

    public E peek() {
        if (arr[1] == null) {
            throw new NoSuchElementException();
        }
        return (E) arr[1];
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public void clear() {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }

        cnt = 0;
    }


    private void siftDown(int idx, Object o) {
        downUseComparator(idx, (E) o, comparator);
    }

    private void downUseComparator(int idx, E o, Comparator<? super E> comp) {
        // 타겟노드 삭제
        arr[idx] = null;

        int parentIdx = idx;
        int childIdx;

        while ((childIdx = getLeftChildIdx(parentIdx)) <= cnt) {
            int rightIdx = getRightChildIdx(parentIdx);
            Object child = arr[childIdx];

            // 오른쪽 자식이 cnt 넘지 말아야함
            // 오 < 왼 -> 작은 노드랑 비교해야하니까 오른쪽을 child로
            if (rightIdx <= cnt && comp.compare((E) child, (E) arr[rightIdx]) > 0) {
                childIdx = rightIdx;
                child = arr[rightIdx];
            }
            // 재배치 할 노드가 자식 노드보다 작을 경우 반복문을 종료
            if(comp.compare(o ,(E) child) <= 0){
                break;
            }

            arr[parentIdx] = child;
            parentIdx = childIdx;
        }

        // 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어준다.
        arr[parentIdx] = o;

        /*
         * 용적 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4 미만일 경우
         * 용적을 반으로 줄임 (단, 최소용적보단 커야 함)
         */
        if(arr.length > heapSize && cnt < arr.length / 4) {
            resize(Math.max(heapSize, arr.length / 2));
        }
    }


    private void siftUp(int idx, Object o) {
        if (comparator == null) { // 별도의 우선순위 기준을 넘기지 않은경우 -> Comparable로 비교
            upUseComparable(idx, (E) o);
        } else {
            upUseComparator(idx, (E) o, comparator);
        }
    }

    private void upUseComparator(int idx, E o, Comparator<? super E> comp) {
        while (idx > 1) {
            int parentIdx = getParentIdx(idx);
            Object parent = arr[parentIdx];

            if (comp.compare(o, (E) parent) >= 0) break; // 부모보다 우선순위 크면 종료

            arr[idx] = parent;
            idx = parentIdx;
        }
        arr[idx] = o;
    }

    private void upUseComparable(int idx, E o) {

        // 타겟노드 역할
        Comparable<? super E> comp = (Comparable<? super E>) o;

        while (idx > 1) {
            int parentIdx = getParentIdx(idx);
            Object parent = arr[parentIdx];

            if (comp.compareTo((E) parent) >= 0) break;

            arr[idx] = parent;
            idx = parentIdx;
        }
        arr[idx] = comp;
    }


    // 크기 초과되면 배열 재생성
    private void resize(int newSize) {
        Object[] newArr = new Object[newSize];

        for (int i = 0 ; i < arr.length ; i++) {
            newArr[i] = arr[i];
        }

        this.arr = null;
        this.arr = newArr;
        this.heapSize = newSize;
    }
    private int getParentIdx(int now) {
        return now / 2;
    }

    private int getRightChildIdx(int now) {
        return now * 2 + 1;
    }

    private int getLeftChildIdx(int now) {
        return now * 2;
    }
}
