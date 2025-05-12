import sys
input = sys.stdin.readline

n, k = map(int,input().split())
temps = list(map(int, input().split()))

def max_temperature_sum(n, k, temps):
    left = 0
    right = k - 1
    current_sum = sum(temps[left:right + 1])
    max_sum = current_sum

    while right < n - 1:
        right += 1
        current_sum += temps[right]
        current_sum -= temps[left]
        left += 1
        max_sum = max(max_sum, current_sum)

    return max_sum

print(max_temperature_sum(n, k, temps))