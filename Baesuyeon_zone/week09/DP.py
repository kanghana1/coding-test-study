# 기존 방식으로 피보나치 구현
def fibonacci(num):
    if num == 1:
        return 1
    if num == 0:
        return 0
    return fibonacci(num-1) + fibonacci(num-2)

# DP를 사용한 피보나치 구현 (1) : Bottom-up 방식
def fibonacci_dp(num):
    f = [0, 1]
    for i in range(2, num+1):
        f.append(f[i-1] + f[i-2])
    return f

# DP를 사용한 피보나치 구현 (2) : Top-down 방식 (memoization)
def fibonacci_memoi(num):
    global memo
    if num >= 2 and len(memo) <= num:
        memo.append(fibonacci_memoi(num-1) + fibonacci_memoi(num-2))
    return memo[num]
memo = [0, 1]