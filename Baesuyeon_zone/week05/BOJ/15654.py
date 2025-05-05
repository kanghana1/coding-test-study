# N개의 자연수 중에서 가장 작은 수를 제일 앞에 세우고 뒷자리를 작은 순서대로 채워나간다. 중복없이
# 모든 수를 뒷자리에 넣었다면 다음으로 작은 수를 제일 앞자리에 세우고 같은 방식으로 뒷자리를 채워나간다.
# 해당 과정을 for 문을 사용해서 구현해보자

import sys
input = sys.stdin.readline

n,m = map(int, input().split()) # 자연수 N와 자연수 M을 받음
numbers = list(map(int, input().split())) #list형식으로 저장
numbers.sort() # 일단 n을 sort해서 오름차순으로 저장

visited =[False]*n # 숫자의 중복방문 경우는 없어야 하니까 방문했는지 아닌지 표시 필요
result = []
depth = 0
def backtrack(depth):
    if depth == m : # M을 depth로 생각하면 .. 이를 기준으로 DFS와 같이 문제 해결 가능
        print(*result) # print(numbers) -> [4, 5, 2]로 출력, print(*numbers) -> 기본 구분 문자를 space로 4 5 2를 출력
        return
    
    for i in range(n): # n만큼 반복하기 위해
        if not visited[i]: # 아직 방문하지 않은 수라면
            visited[i] = True # 방문 표시
            result.append(numbers[i]) # 결과리스트에 추가
            backtrack(depth+1) # 뒷자리를 채우기 위해 재귀 실행
            visited[i]=False # 모든 재귀를 마친 뒤 방문 표시 원상복구
            result.pop() # 마찬가지로 결과리스트도 원상복구

backtrack(depth)