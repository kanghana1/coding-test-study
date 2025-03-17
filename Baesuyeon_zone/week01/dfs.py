# 인접 리스트를 사용해서 그래프 예시 생성
graph = {
    'A': ['B', 'C'],
    'B': ['A', 'D', 'E'],
    'C': ['A', 'F'],
    'D': ['B'],
    'E': ['B', 'F'],
    'F': ['C', 'E']
}

# DFS 함수 정의
def dfs(graph, start, visited=None):
    if visited is None:
        visited = set() #방문한 노드에 대해 집합을 사용하여 추적
    visited.add(start)
    print(start, end=' ')
    for next_node in graph[start]:
        if next_node not in visited:
            dfs(graph, next_node, visited)

# 시작할 노드(A)를 선택하여 DFS를 호출
dfs(graph, 'A')