# 문제 조건 요약
# 수많은 집 좌표가 주어지고, 안테나 하나 설치

# 모든 집까지의 거리 총합이 최소가 되는 지점에 설치

# 적용 알고리즘
# 그리디 알고리즘
# 거리합 최소화 조건 → 중앙값에 설치

n = int(input())
houses = sorted(list(map(int, input().split())))
print(houses[(n - 1) // 2])  # 중앙값   