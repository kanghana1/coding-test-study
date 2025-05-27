# 문제 조건 요약
# 최소 비용으로 모든 컴퓨터 연결 (최소 신장 트리 문제로 풀이)

# 적용 알고리즘
# 그래프 알고리즘 (크루스칼 사용)
# 비용 기준으로 간선을 정렬하고, Union-Find를 이용해 사이클 없이 연결

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]

def union(parent, a, b):
    a = find(parent, a)
    b = find(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

n = int(input())
m = int(input())
edges = [tuple(map(int, input().split())) for _ in range(m)]
edges.sort(key=lambda x: x[2])  # 비용 기준 정렬

parent = [i for i in range(n+1)]
result = 0

for a, b, cost in edges:
    if find(parent, a) != find(parent, b):
        union(parent, a, b)
        result += cost

print(result)
