package org.week4.impl_datastructure;

import java.util.NoSuchElementException;

public class MaxHeap {
    final int h; // 편의를 위한 최대 높이 지정
    final int size;
    static int nowIdx = 0;
    static int[] heapArr;
    public MaxHeap(int h) {
        this.h = h;
        this.size = (int) Math.pow(2, h);
        heapArr = new int[size];
    }

    public void push(int value) {
        if (nowIdx >= size) {
            throw new IndexOutOfBoundsException("배열 크기를 초과했습니다");
        }
        nowIdx++;
        heapArr[nowIdx] = value;
        upHeap();
    }

    public int pop() {
        if (nowIdx < 1) {
            throw new NoSuchElementException("힙이 이미 비었습니다");
        }
        swap(1, nowIdx);
        int ans = heapArr[nowIdx];
        heapArr[nowIdx] = 0;
        nowIdx--;
        downHeap();
        return ans;
    }

    private void downHeap() {
        int idx = 1;
        while (2 * idx <= nowIdx) {
            int leftIdx = idx * 2;
            int rightIdx = idx * 2 + 1;
            int changeIdx = rightIdx;
            if (nowIdx < rightIdx) changeIdx = leftIdx;
            else if (heapArr[leftIdx] > heapArr[rightIdx]) changeIdx = leftIdx;

            if (heapArr[idx] > heapArr[changeIdx]) break;
            swap(idx, changeIdx);
            idx = changeIdx;
        }
    }

    private void upHeap() {
        int idx = nowIdx;
        while (idx > 1 && heapArr[idx] > heapArr[idx / 2]) {
            swap(idx, idx / 2);
            idx /= 2;
        }
    }

    private void swap(int a, int b) {
        int c = heapArr[a];
        heapArr[a] = heapArr[b];
        heapArr[b] = c;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(3);
        maxHeap.push(3);
        maxHeap.push(8);
        maxHeap.push(2);
        maxHeap.push(10);

        // 큰 순서대로 출력되는 거 확인
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
    }

}
