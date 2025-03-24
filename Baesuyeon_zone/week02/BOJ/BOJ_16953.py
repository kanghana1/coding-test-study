import sys
input = sys.stdin.readline

def reverse_operation(A, B):
    count = 1  # 도달하는데 걸린 연산 횟수 + 1

    while B > A:
        if B % 10 == 1:
            B //= 10  # 오른쪽 1 제거
        elif B % 2 == 0:
            B //= 2   # 2로 나눔
        else:
            return -1  # 되돌릴 수 없음
        count += 1

    return count if B == A else -1

# A, B 입력받기
A, B = map(int, input().split())

print(reverse_operation(A, B))