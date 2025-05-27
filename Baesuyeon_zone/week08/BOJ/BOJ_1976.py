# 문제 조건 요약
# 도시 간 연결 정보 + 여행 경로

# 모든 도시가 연결되어 있으면 YES, 아니면 NO

# 적용 알고리즘
# 그래프 알고리즘 (유니온 파인드 사용)
# 연결된 도시를 같은 집합으로 묶고, 여행 경로의 도시들이 모두 같은 집합인지 확인

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]

def union(parent, a, b):
    a = find(parent, a)
    b = find(parent, b)
    if a != b:
        parent[b] = a

n = int(input())
m = int(input())
parent = [i for i in range(n+1)]

# 도시 연결 정보
for i in range(n):
    data = list(map(int, input().split()))
    for j in range(n):
        if data[j] == 1:
            union(parent, i+1, j+1)

plan = list(map(int, input().split()))
root = find(parent, plan[0])

if all(find(parent, city) == root for city in plan):
    print("YES")
else:
    print("NO")
