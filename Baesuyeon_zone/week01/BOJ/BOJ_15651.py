import sys

# DFS를 활용하여 재귀적으로 문제 해결
def dfs(N, M, sequence, depth):
    if depth == M:  # M개의 숫자를 선택한 경우 출력 후 종료
        print(" ".join(map(str, sequence)))
        return
    
    for num in range(1, N + 1):  # 1부터 N까지 숫자 선택
        dfs(N, M, sequence + [num], depth + 1)  # 리스트를 새롭게 생성하여 재귀 호출

# N, M 입력받기
N, M = map(int, sys.stdin.readline().split())

# DFS 시작
dfs(N, M, [], 0)