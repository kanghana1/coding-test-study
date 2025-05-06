import sys
input = sys.stdin.readline

N = int(input()) # N 입력받기
result = []

# depth: 자릿수 깊이
# last_digit: 다음에 붙일 숫자 범위 (항상 이전보다 작아야 하므로 last_digit보다 작은 수만 가능)
# num: 현재까지 만든 감소하는 수
def dfs(depth, last_digit, num): # 조합을 만들기 위한 dfs 함수
    if depth > 10: # 0부터 9까지의 수만 선택 가능하므로 depth가 10이상이되면 return
        return
    result.append(num) # 아직 depth가 10이하라면 현재까지 만든 감소하는 수를 result에 추가
    for i in range(last_digit): # 다음에 붙일 숫자 범위만큼 재귀호출
        dfs(depth + 1, i, num * 10 + i)

# 0~9에서 시작하는 감소하는 수 만들기
for i in range(10):
    dfs(1, i, i)

# 만들어진 조합들을 오름차순 정렬
result.sort()

# N의 범위가 예외라면 -1 출력
if N >= len(result):
    print(-1)
else:
    print(result[N])
