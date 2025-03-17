import sys
input = sys.stdin.readline

# 방향 좌표 설정 (상, 하, 좌, 우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y, count):
    global max_steps
    max_steps = max(max_steps, count)  # 최댓값 갱신

    for i in range(4):  # 네 방향으로 탐색 반복
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < n and forest[nx][ny] > forest[x][y]:  # 이동 가능 조건
            dfs(nx, ny, count + 1)  # 다음 위치로 dfs 탐색 (재귀)

# 입력 받기
n = int(input())
forest = [list(map(int, input().split())) for _ in range(n)]
max_steps = 0  # 최장 경로 길이 저장

# 모든 칸을 시작점으로 dfs 탐색 실행
for i in range(n):
    for j in range(n):
        dfs(i, j, 1)  # 초기 이동 횟수 1

# 결과 출력
print(max_steps)
