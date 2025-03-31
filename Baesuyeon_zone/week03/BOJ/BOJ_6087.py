import sys
import heapq
input = sys.stdin.readline

W, H = map(int, input().split())
board = [list(input().strip()) for _ in range(H)]

# 시작/도착 위치 구하기
points = [(i, j) for i in range(H) for j in range(W) if board[i][j] == 'C']
(start_y, start_x), (end_y, end_x) = points

# 상하좌우 (이전 문제들에서 적용했던 좌표 차원 차용)
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

# 방문 체크: [H][W][4방향]
visited = [[[float('inf')] * 4 for _ in range(W)] for _ in range(H)]

def dijkstra():
    heap = []
    # 시작점에서 4방향으로 출발 (거울 설치 수, y, x, 방향)
    for d in range(4):
        heapq.heappush(heap, (0, start_y, start_x, d))
        visited[start_y][start_x][d] = 0

    while heap:
        mirror, y, x, dir = heapq.heappop(heap)

        # 도착지에 도달했으면 종료 조건 가능 (최솟값만 필요하면 바로 종료 가능)
        if (y, x) == (end_y, end_x):
            return mirror

        for nd in range(4):
            ny = y + dy[nd]
            nx = x + dx[nd]
            # 범위 안, 벽이 아니어야 함
            if 0 <= ny < H and 0 <= nx < W and board[ny][nx] != '*':
                # 같은 방향 → 거울 추가 X / 다른 방향 → 거울 +1
                new_mirror = mirror if dir == nd else mirror + 1
                if visited[ny][nx][nd] > new_mirror:
                    visited[ny][nx][nd] = new_mirror
                    heapq.heappush(heap, (new_mirror, ny, nx, nd))

print(dijkstra())
