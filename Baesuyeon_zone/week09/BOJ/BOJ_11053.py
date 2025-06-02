import sys
input = sys.stdin.readline

# 수열 크기와 수열 입력받기
N = int(input())
A = list(map(int, input().split()))

# DP 배열 정의
dp = [1] * N # 이어붙일 수 있는게 하나도 없는 경우에도 1이니까

for i in range (N):
    for j in range (i): # 현재 index(i)가 이전까지의 수(j)들과 이어질 수 있는지 판단
        if A[j] < A[i]: # 현재 배열 상에서 더 큰 수가 왔다면 이어붙이기 (이전까지 가장 길었던 수열 중에, 나랑 이어질 수 있는 수열만 본다)
            dp[i] = max(dp[i], dp[j] + 1) # 해당하는 index의 dp 수열에 max값 갱신 

print(max(dp))