from collections import deque

# BFS 구현
def bfs(start, graph, N):
    # 방문정보 리스트
    visited = [False] * (N + 1)

    # 도달까지 걸린 횟수 정보 리스트
    distance = [0] * (N + 1)

    queue = deque([start])

    # 시작 노드 방문 처리
    visited[start] = True

    while queue:
        current = queue.popleft()

        for neighbor in graph[current]:
            if not visited[neighbor]:
                visited[neighbor] = True

                # 이웃 방문 카운트
                distance[neighbor] = distance[current] + 1

                queue.append(neighbor)

    return sum(distance)  # 모든 거리의 합 == 케빈 베이컨 수


def find_kevin_bacon(N, edges):
    # 그래프 생성 (1-indexed)
    graph = [[] for _ in range(N + 1)]

    for A, B in edges:
        graph[A].append(B)
        graph[B].append(A)

    min_score = float('inf')
    answer = 0

    for i in range(1, N + 1):

        # 각 노드마다 케빈 베이컨 수를 score에 저장
        score = bfs(i, graph, N)

        # 케빈 베이컨 최솟값 찾기
        if score < min_score:
            min_score = score
            answer = i
            
        elif score == min_score and i < answer:
            answer = i

    return answer
    
# N, M 입력받기
N, M = map(int, input().split())
edges = [tuple(map(int, input().split())) for _ in range(M)]
    
print(find_kevin_bacon(N, edges))