import sys
input= sys.stdin.readline

N =int(input())
lope = [int(input()) for _ in range(N)] #list형식으로 저장

def max_weight(lopes):
    lopes.sort() #큰 순서대로 정렬
    max_w = 0 #최댓값 갱신
    for i in range(N):
        weight = lopes[i]*(N-i) # i번째부터 N개 사용했을 때
        max_w = max(max_w, weight)
    return max_w

print(max_weight(lope))