/*
자료구조: 힙 구현하기
힙이란: 주어진 데이터들 중에서 특정 기준에 부합하는 최댓갑 혹은 최솟값을 빠르게
찾아낼 수 있는 자료구조
부모 노드의 값이 자식 노드의 값보다 큰 관계일 때를 최대힙
부모 노드의 값이 자식 노드의 값보다 작은 관계일 때를 최소힙
*/

#include <bits/stdc++.h>
using namespace std;

template <typename T>
class MinHeap {
 private:
  vector<T> heap;

  void upHeap(int idx) {
    while (idx > 1 && heap[idx] < heap[idx / 2]) {
      swap(heap[idx], heap[idx / 2]);
      idx /= 2;
    }
  }

  void downHeap(int idx) {
    int size = heap.size() - 1;
    while (2 * idx <= size) {
      int left = idx * 2;
      int right = idx * 2 + 1;
      int smaller = left;

      if (right <= size && heap[left] > heap[right]) smaller = right;

      if (heap[smaller] >= heap[idx]) break;

      swap(heap[idx], heap[smaller]);
      idx = smaller;
    }
  }

 public:
  MinHeap() { heap.push_back(T()); }

  void push(T value) {
    heap.push_back(value);
    upHeap(heap.size() - 1);
  }

  void pop() {
    if (heap.size() <= 1) return;

    heap[1] = heap.back();
    heap.pop_back();
    downHeap(1);
  }

  T top() {
    if (heap.size() <= 1) throw out_of_range("Heap is empty");
    return heap[1];
  }

  bool empty() { return heap.size() == 1; }

  int size() { return heap.size() - 1; }
};

int main() {
  MinHeap<int> heap;
  heap.push(5);
  heap.push(3);
  heap.push(7);
  heap.push(1);

  cout << "Top: " << heap.top() << "\n";  // 1
  heap.pop();

  cout << "Top after pop: " << heap.top() << "\n";  // 3

  heap.push(2);
  cout << "Top after pushing 2: " << heap.top() << "\n";  // 2

  cout << "All elements in order: ";
  while (!heap.empty()) {
    cout << heap.top() << " ";
    heap.pop();
  }
  cout << "\n";

  return 0;
}