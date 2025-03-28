#include <bits/stdc++.h>
using namespace std;

template <typename T>
class PriorityQueue {
 private:
  vector<T> heap;

  void upHeap(int idx);    // 삽입 후 정렬
  void downHeap(int idx);  // 삭제 후 정렬

 public:
  PriorityQueue() { heap.push_back(T()); }
  void push(T value);
  void pop();
  T top();
  bool empty();
  int size();
};

/*
 * 힙에 새로운 요소가 들어오면, 일단 새로운 노드를
 * 힙의 마지막 노드에 이어서 삽입
 */
template <typename T>
void PriorityQueue<T>::upHeap(int idx) {
  while (idx > 1 && heap[idx] < heap[idx / 2]) {
    swap(heap[idx], heap[idx / 2]);
    idx = idx / 2;
  }
}
// 힙의 요소를 삭제하면 맨 끝의 노드를 heap[1]로 올려
// 아래로 내리면서 힙 구조 제정렬
template <typename T>
void PriorityQueue<T>::downHeap(int idx) {
  int size = heap.size() - 1;
  while (2 * idx <= size) {
    int left = 2 * idx;
    int right = 2 * idx + 1;
    // while문의 조건이 left의 존재 보장
    int smaller = left;
    if (right <= size && heap[right] < heap[left]) {
      smaller = right;
    }

    if (heap[idx] < heap[smaller]) break;

    swap(heap[idx], heap[smaller]);
    idx = smaller;
  }
}

// 힙 맨 끝에 새로운 노드 삽입
template <typename T>
void PriorityQueue<T>::push(T value) {
  heap.push_back(value);
  upHeap(heap.size() - 1);
}

// 힙의 루트 노드 제거
template <typename T>
void PriorityQueue<T>::pop() {
  if (heap.size() == 0) return;

  heap[1] = heap.back();
  heap.pop_back();
  downHeap(1);
}

// 힙에서 가장 높은 우선순위의 값을 반환
template <typename T>
T PriorityQueue<T>::top() {
  if (heap.size() > 1) return heap[1];
  throw out_of_range("Queue is empty");
}

template <typename T>
bool PriorityQueue<T>::empty() {
  return heap.size() == 1;  // dummy만 존재
}

template <typename T>
int PriorityQueue<T>::size() {
  return heap.size() - 1;  // dummy 제외한 실제 데이터 개수
}

int main() {
  PriorityQueue<int> pq;

  pq.push(5);
  pq.push(3);
  pq.push(7);
  pq.push(1);

  cout << "Top: " << pq.top() << "\n";  // 1

  pq.pop();
  cout << "Top after pop: " << pq.top() << "\n";  // 3

  pq.push(2);
  cout << "Top after pushing 2: " << pq.top() << "\n";  // 2

  cout << "All elements (in order of removal): ";
  while (!pq.empty()) {
    cout << pq.top() << " ";
    pq.pop();
  }
  cout << "\n";

  return 0;
}
