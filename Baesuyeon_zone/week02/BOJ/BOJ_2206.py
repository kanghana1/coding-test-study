import sys
input = sys.stdin.readline

from collections import deque

# 상하좌우 방향
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# BFS 구현
def bfs(N, M, board):
    # 벽을 부쉈는지에 대한 상태까지 함께 관리하기 위해 3차원 배열 사용
    # visited[x][y][0]: 벽 안 부쉈을 때 방문
    # visited[x][y][1]: 벽 부쉈을 때 방문
    visited = [[[0]*2 for _ in range(M)] for _ in range(N)]
    queue = deque()
    
    # 시작 노드 : (0, 0)에서 벽 안 부순 상태로 시작
    queue.append((0, 0, 0))  
    visited[0][0][0] = 1     # 시작 위치 방문 처리

    while queue:
        # x, y, 벽 부쉈는지
        x, y, broken = queue.popleft()

        # 도착지 도달 시 걸린 거리 return
        if x == N-1 and y == M-1:
            return visited[x][y][broken]

        # 상하좌우 네 방향으로 탐색 반복
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            # 범위를 벗어나지 않게 체크
            if 0 <= nx < N and 0 <= ny < M:
                # 다음 칸이 벽(1)이고, 아직 벽을 부수지 않았을 때(0)
                if board[nx][ny] == 1 and broken == 0 and visited[nx][ny][1] == 0:
                    visited[nx][ny][1] = visited[x][y][0] + 1
                    queue.append((nx, ny, 1))

                # 다음 칸이 벽이 아니고(0), 아직 방문 안 한 경우
                if board[nx][ny] == 0 and visited[nx][ny][broken] == 0:
                    visited[nx][ny][broken] = visited[x][y][broken] + 1
                    queue.append((nx, ny, broken))

    # 도달 불가 시
    return -1


# N, M 입력받기
N, M = map(int, input().split())

# N X M 사이즈 보드 제작
board = [list(map(int, input().strip())) for _ in range(N)]
    
print(bfs(N, M, board))