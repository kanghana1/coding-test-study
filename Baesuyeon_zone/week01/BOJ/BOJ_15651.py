import sys

def backtrack(N, M, sequence):
    if len(sequence) == M:  # M개의 숫자를 선택한 경우 출력 후 종료
        print(" ".join(map(str, sequence)))
        return
    
    for num in range(1, N + 1):  # 1부터 N까지 숫자 선택
        sequence.append(num)  # 현재 숫자 추가
        backtrack(N, M, sequence)  # 재귀 호출
        sequence.pop()  # pop으로 현재 상태 빼내고 원본으로 복귀 (백트래킹)

# 입력 받기
N, M = map(int, sys.stdin.readline().split())

# 백트래킹 시작
backtrack(N, M, [])