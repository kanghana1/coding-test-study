import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

def max_fruit_length(arr):
    n = len(arr)
    fruit_count = [0] * 10  # 과일 종류는 0부터 9까지이기 때문에
    left = 0
    kind = 0
    max_length = 0

    for right in range(n):
        if fruit_count[arr[right]] == 0:
            kind += 1
        fruit_count[arr[right]] += 1

        while kind > 2:
            fruit_count[arr[left]] -= 1
            if fruit_count[arr[left]] == 0:
                kind -= 1
            left += 1

        max_length = max(max_length, right - left + 1)

    return max_length

print(max_fruit_length(arr))