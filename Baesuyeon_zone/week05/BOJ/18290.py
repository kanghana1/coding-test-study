import sys
input = sys.stdin.readline

N, M, K = map(int, input().split()) # N, M, K 입력받기
board = [list(map(int, input().split())) for _ in range(N)] # N X M 사이즈의 2차원 배열 만들기
visited = [[False]*M for _ in range(N)]

max_total = -float('inf') # 격자판에 들어갈 수의 범위

def is_valid(r, c): # 인접한지에 대해 판단하는 함수
    for dr, dc in [(-1,0), (1,0), (0,-1), (0,1)]: # 상하좌우 검사
        nr, nc = r + dr, c + dc
        if 0 <= nr < N and 0 <= nc < M: # 인접한 위치가 격자판 안에 있고, 이미 방문한 칸이면 인접하다 판단 → 선택 불가
            if visited[nr][nc]:
                return False
    return True

# count: 현재까지 선택한 칸 개수
# total_sum: 지금까지 선택한 칸들의 합
# start: 다음 탐색을 시작할 인덱스 (1차원 기준)
def backtrack(count, total_sum, start): # 백트래킹 사용해서 K칸의 합의 최댓값을 찾는 함수
    global max_total
    if count == K: # K개를 선택했다면 최댓값 갱신
        max_total = max(max_total, total_sum)
        return

    for idx in range(start, N * M): # 1차원 인덱스를 (r, c) 좌표로 변환하여 반복
        r, c = divmod(idx, M) # 1차원 idx → 2차원 (r, c)
        if not visited[r][c] and is_valid(r, c): # 아직 방문하지 않았고, 인접하지 않다면 선택 가능
            backtrack(count + 1, total_sum + board[r][c], idx + 1) # 다음 재귀 호출
            visited[r][c] = False # 재귀를 모두 마쳤다면 원상복구 해주기

backtrack(0, 0, 0)
print(max_total)
