#최대힙
import heapq
import sys
input = sys.stdin.readline

N = int(input())
heap = []

for _ in range(N):
    x = int(input())
    if x == 0:
        if heap:
            print(-heapq.heappop(heap)) # - 을 이용하여 가장 큰 값 출력 (파이썬은 최대힙이 없음)
        else:
            print(0)
    else:
        heapq.heappush(heap, -x) # 마찬가지로 최대 힙처럼 쓰기 위해 - 사용해서 출력