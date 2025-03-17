def dfs(x, y):
    if x < 0 or y < 0 or x >= N or y >= N:
        return False
    if board[x][y] == -1:  # (-1) 도착 시 종료 : 승리
        return True
    if visited[x][y]:  # 이미 방문한 곳이면 종료 : 패배
        return False

    visited[x][y] = True
    jump = board[x][y]

    # 오른쪽 이동 또는 아래쪽 이동 중 하나라도 도착하면 True 반환
    return dfs(x, y + jump) or dfs(x + jump, y)

# 입력 받기
N = int(input())
board = [list(map(int, input().split())) for _ in range(N)] # 입력 받은 수만큼 정사각형 보드생성
visited = [[False] * N for _ in range(N)] # 방문하지 않은 노드 체크

# 결과 출력
print("HaruHaru" if dfs(0, 0) else "Hing")