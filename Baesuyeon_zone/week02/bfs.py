#먼저 deque 라이브러리를 import 해준다
from collections import deque 

# 2차원 배열을 사용해서 그래프 예시 생성
# 리스트 내 인덱스는 노드 번호를 의미함 
# 각 인덱스에 해당하는 원소에는 해당 노드에 인접한 노드 번호가 담겨 있다.
graph = [
    [],
    [2, 3],
    [1, 8],
    [1, 4, 5],
    [3, 5],
    [3, 4],
    [7, 8],
    [6, 8],
    [2, 6, 7]
]

# 노드별로 방문 정보를 리스트로 표현
visited = [False] * 9

# BFS 메서드 정의
def bfs (graph, node, visited):
    # 큐 구현을 위한 deque 라이브러리 활용
    queue = deque([node])
    # 현재 노드를 방문 처리
    visited[node] = True
    
    # 큐가 완전히 빌 때까지 반복
    while queue:
        # 큐에 삽입된 순서대로 노드 하나 꺼내기 (선입선출)
        v = queue.popleft()
        # 탐색 순서 출력
        print(v, end = ' ')
        # 현재 처리 중인 노드에서 방문하지 않은 인접 노드를 모두 큐에 삽입
        for i in graph[v]:
            if not (visited[i]):
                queue.append(i)
                visited[i] = True
                
# 시작할 노드(1)을 선택하여 BFS 메서드 호출
bfs(graph, 1, visited)