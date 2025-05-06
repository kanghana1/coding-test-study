import sys
import copy # map 객체 복사를 위해 copy 모듈 import
input = sys.stdin.readline

n, m = map(int, input().split()) # N, M 입력받아 2차원 배열 생성
office = [] # 사무실 map 정보 입력 및 CCTV 좌표 수집
cctvs = []  # (y, x, cctv번호) 저장
directions = [(-1, 0), (0, 1), (1, 0), (0, -1)] # 방향 벡터: ↑, →, ↓, ← (순서 고정)

for i in range(n):
    row = list(map(int, input().split()))
    office.append(row)
    for j in range(m):
        if 1 <= row[j] <= 5:  # 1~5번 CCTV라면
            cctvs.append((i, j, row[j]))

# CCTV 종류별로 가능한 감시 방향 조합 미리 정의
cctv_dirs = {
    1: [[0], [1], [2], [3]],                     # 한 방향씩
    2: [[0, 2], [1, 3]],                         # 상하 or 좌우
    3: [[0, 1], [1, 2], [2, 3], [3, 0]],         # ㄴ자 형태 4방향
    4: [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]], # 세 방향
    5: [[0, 1, 2, 3]]                            # 전방위
}

# 특정 방향 세트에 대해 CCTV가 감시할 수 있는 영역을 '#'로 표시하는 함수
def watch(y, x, dirs, board):
    for d in dirs:
        ny, nx = y, x
        while True:
            ny += directions[d][0]
            nx += directions[d][1]

            # 범위 벗어나면 중단
            if not (0 <= ny < n and 0 <= nx < m):
                break

            # 벽(6)이 나오면 감시 중단
            if board[ny][nx] == 6:
                break

            # 빈 칸(0)이라면 감시 표시
            if board[ny][nx] == 0:
                board[ny][nx] = '#'

            # CCTV나 감시 중인 칸이면 통과는 가능 (계속 감시)
            # '#'이 이미 있거나 1~5번 CCTV가 있으면 그냥 통과

# 전체 탐색 중 찾은 최소 사각지대 수 저장용
min_blind = int(1e9)

# DFS를 통해 모든 CCTV 방향 조합 시도
def dfs(depth, office_map):
    global min_blind

    # 모든 CCTV 방향을 정했으면 사각지대 수 계산
    if depth == len(cctvs):
        count = sum(row.count(0) for row in office_map)  # 0인 칸의 개수 세기
        min_blind = min(min_blind, count)  # 최소값 갱신
        return
    
    y, x, cctv_type = cctvs[depth] # 현재 탐색 중인 CCTV

    # 해당 CCTV의 가능한 방향 조합 반복
    for dirs in cctv_dirs[cctv_type]:
        # 현재 map을 깊은 복사하여 상태 보존
        tmp_map = copy.deepcopy(office_map)

        # 선택한 방향으로 감시 영역 표시
        watch(y, x, dirs, tmp_map)

        # 다음 CCTV로 넘어감
        dfs(depth + 1, tmp_map)

# DFS 시작
dfs(0, office)

# 최소 사각지대 개수 출력
print(min_blind)
