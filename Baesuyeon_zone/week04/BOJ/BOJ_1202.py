import sys
import heapq
input = sys.stdin.readline

N, K = int(input().split()) # 보석 개수와 가방 개수
jew_info = [tuple(map(int,input())) for _ in range(N)] # 보석의 무게와 가격
max_weight = [int(input()) for _ in range(K)] # 각 가방에 담을 수 있는 최대 무게

# 상덕이가 훔칠 수 있는 보석의 최대 가격
def max_jewerly():
    jew_info.sort()      # 보석을 무게 기준 오름차순 정렬
    max_weight.sort()          # 가방도 무게 기준 정렬

    total = 0
    heap = []            # 최대 힙 (가격 기준)
    j = 0                # 보석 인덱스

    for bag_weight in max_weight:
        # 현재 가방이 담을 수 있는 보석을 전부 heap에 push
        while j < N and jew_info[j][0] <= bag_weight:
            heapq.heappush(heap, -jew_info[j][1])  # 최대 힙을 위해 음수 push
            j += 1
        # heap에 있는 보석 중 가장 비싼 것 하나 선택
        if heap:
            total += -heapq.heappop(heap)  # 다시 양수로 바꿔서 더함

    return total

print(max_jewerly())