## 우선순위 큐 : 큐인데, 그 안에 들어있는 원소들이 우선순위에 따라서 정렬되어 있다. 힙으로 주로 만든다. 
## 힙 : 완전 이진 트리로, 최대힙, 최소힙으로 나누어져있다. 
## 완전 이진 트리 : 마지막 레벨을 제외한 모든 레벨이 채워져있고, 마지막 레벨은 왼쪽부터 채워진 트리이다. 

## 파이썬에서는 heapq와 queue.PriorityQueue를 이용해서 구현할 수 있다.
## heapq는 리스트 자료형이고, PriorityQueue는 클래스 자료형이다.

## 우선순위 방향을 사용자가 직접 지정해주면 된다. 

## 최소힙 클래스 구현
    # 배열을 이용해서 구현하도록 함. 
class MinHeap:
    def __init__(self):
        self.heap = [] # 힙을 위한 배열 선언

    def __repr__(self):
        return f"Heap: {self.heap}"

    def push(self, value):
        self.heap.append(value) # 배열의 마지막 자리에 삽입한 후
        self._heapify_up() # 규칙에 맞게 배열 재조정 (O(log N))

    def pop(self):
        if not self.heap:
            return None # 아무것도 없다면

        self._swap(0, -1) # 맨 마지막 인덱스와 첫 번째 인덱스의 원소를 뒤바꿈(최소 원소가 마지막 인덱스로 이동함)
        min_val = self.heap.pop() # 마지막 인덱스에 저장된 최솟값을 제거한다.
        self._heapify_down() # 규칙에 맞게 배열 재조정
        return min_val
    
    def _heapify_up(self):
        idx = len(self.heap) - 1 # 마지막 원소가 원래의 자리에 맞게 올라가도록 합니다.
        while idx > 0:
            parent = (idx - 1) // 2 # 해당 원소의 부모 원소
            if self.heap[idx] < self.heap[parent]: # 부모 원소가 자식 원소보다 값이 크다면(최소힙이므로 자리를 바꾸어주어야 한다.)
                self._swap(idx, parent)
                idx = parent
            else:
                break
    
    def _heapify_down(self):
        idx = 0 # 첫 번째 원소가 원래의 자리에 맞게 내려가도록 합니다.
        length = len(self.heap)
        while 2 * idx + 1 < length:
            left = 2 * idx + 1
            right = 2 * idx + 2
            smallest = left

            if right < length and self.heap[right] < self.heap[left]:
                smallest = right
            if self.heap[smallest] < self.heap[idx]:
                self._swap(smallest, idx)
                idx = smallest
            else:
                break
    
    def _swap(self, i, j):
        self.heap[i], self.heap[j] = self.heap[j], self.heap[i]

## 최대힙 클래스 구현
    # PriorityQueue를 이용해서 구현하도록 함.
from queue import PriorityQueue
class MaxHeap:
    def __init__(self):
        self.q = PriorityQueue()

    def push(self, value):
        # 기본이 최소힙이므로 최대힙을 구하기 위해서는
        # 우선순위를 음수로 적용하면 된다.
        self.q.put(-value)
    
    def pop(self):
        if self.q.empty():
            return None
        return -self.q.get() # 음수로 저장되어 있었기 때문에 나올 때 -를 붙여 양수로 바꾸어준다. 
    
    def is_empty(self):
        return self.q.empty()

## 우선순위큐 클래스 구현(우선순위 3개를 임의로)
    # heapq를 이용해서 구현하도록 함.
    # 만약에 클래스를 안쓰고, 바로 넣고 싶다면, heapq.heappush(heap, ((a, -b, c)) 이런 식으로 넣어도 된다. 
class PriorityItem: 
    def __init__(self, a, b, c, value):
        self.a = a
        self.b = b
        self.c = c
        self.value = value
        
    def __lt__(self, other):
        return (self.a, -self.b, self.c) < (other.a, -other.b, other.c) # a는 오름차순, b는 내림차순, c는 오름차순으로 정렬하고 싶다.
    
    def __repr__(self):
        return f"{self.value}(a = {self.a}, b = {self.b}, c = {self.c})"
    
import heapq
class CustomPriorityQueue:
    def __init__(self):
        self.heap = []
    
    def push(self, item):
        heapq.heappush(self.heap, item)
    
    def pop(self):
        if self.heap:
            return heapq.heappop(self.heap)
        return None

    def is_empty(self):
        return len(self.heap) == 0

def test_heap_min(classes):
    print(f"Testing {classes.__name__}")
    h = classes()
    for v in [5, 3, 8, 1, 2]:
        h.push(v)
    
    while True:
        val = h.pop()
        if val is None:
            break
        print(h, val, end = ' ')
    print()

def test_heap_max(classes):
    print(f"Testing {classes.__name__}")
    h = classes()
    for v in [5, 3, 8, 1, 2]:
        h.push(v)
    
    while not h.is_empty():
        val = h.pop()
        print(val, end = ' ')
    print()

def test_heap_custom(classes):
    print(f"Testing {classes.__name__}")
    h = classes()
    h.push(PriorityItem(1, 3, 2, "1st"))
    h.push(PriorityItem(1, 5, 1, "2nd"))
    h.push(PriorityItem(0, 4, 3, "3rd"))
    h.push(PriorityItem(1, 3, 1, "4th"))

    while not h.is_empty():
        print(h.pop())


test_heap_min(MinHeap)
test_heap_max(MaxHeap)
test_heap_custom(CustomPriorityQueue)
