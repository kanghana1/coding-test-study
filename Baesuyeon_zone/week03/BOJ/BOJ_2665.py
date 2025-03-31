import heapq
import sys
input = sys.stdin.readline

# 방의 수 입력받기
n = int(input())

# n^2의 보드 만들기
board = [list(map(int, input().strip())) for _ in range(n)]

# 각 위치까지 변경한 방 수 기록
distance = [[float('inf')] * n for _ in range(n)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 다익스트라 알고리즘 사용해서 문제 해결
def dijkstra():
    heap = []
    heapq.heappush(heap, (0, 0, 0))  # (비용, x, y)
    distance[0][0] = 0

    while heap:
        cost, x, y = heapq.heappop(heap)

        if (x, y) == (n-1, n-1):
            return cost  # 목적지 도달 시 종료

        if cost > distance[x][y]:
            continue

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < n:
                # 흰 방이면 비용 0, 검은 방이면 비용 1
                new_cost = cost if board[nx][ny] == 1 else cost + 1

                if new_cost < distance[nx][ny]:
                    distance[nx][ny] = new_cost
                    heapq.heappush(heap, (new_cost, nx, ny))

# 실행
print(dijkstra())