import sys
input = sys.stdin.readline

from collections import deque

# 상하좌우 방향
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(shark_x, shark_y, size, board, N):
    visited = [[False]*N for _ in range(N)]
    queue = deque()
    queue.append((shark_x, shark_y, 0))  # x, y, 거리
    visited[shark_x][shark_y] = True

    fishes = []

    while queue:
        x, y, dist = queue.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny]:
                # 지나갈 수 있는 칸: 크기가 작거나 같음
                if board[nx][ny] <= size:
                    visited[nx][ny] = True
                    queue.append((nx, ny, dist + 1))
                    # 먹을 수 있는 물고기: 크기가 작은 경우
                    if 0 < board[nx][ny] < size:
                        fishes.append((dist + 1, nx, ny))

    # 물고기 리스트 정렬: 거리 → x → y
    fishes.sort()
    return fishes

# 입력 받기
N = int(input())

# 입력 받은 수만큼 정사각형 보드 생성
board = [list(map(int, input().split())) for _ in range(N)]

print()