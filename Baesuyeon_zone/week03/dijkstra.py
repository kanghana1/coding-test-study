n, m = map(int, input().split()) # 노드의 개수, 간선 개수 입력받기
k = int(input())                 # 시작할 노드

INF = 1e8 # 초기에는 모든 거리를 무한으로 설정

graph = [[] for _ in range(n+1)] # 1번 노드부터 시작하므로 하나더 추가

visited = [False] * (n+1)
distance = [INF] * (n+1)

for _ in range(m):
  u, v, w = map(int, input().split()) # u: 출발노드, v: 도착노드, w: 연결된 간선의 가중치 
  graph[u].append((v, w))             # 거리 정보와 도착노드를 같이 입력

def get_smallest_node():
  min_val = INF
  index = 0
  for i in range(1, n+1):
    if distance[i] < min_val and not visited[i]: 
      min_val = distance[i]
      index = i
  return index

def dijkstra(start):
  distance[start] = 0 # 시작 노드는 0으로 초기화
  visited[start] = True

  for i in graph[start]:
    distance[i[0]] = i[1] # 시작 노드와 연결된 노도들의 거리 입력
  
  for _ in range(n-1): 
    now = get_smallest_node() # 거리가 구해진 노드 중 가장 짧은 거리인 것을 선택
    visited[now] = True       # 방문 처리

    for j in graph[now]:
      if distance[now] + j[1] < distance[j[0]]: # 기존에 입력된 값보다 더 작은 거리가 나온다면,
        distance[j[0]]= distance[now] + j[1]    # 최솟값을 갱신

dijkstra(k)
print(distance)

# 입력 예시
# 5 6
# 1
# 5 1 1
# 1 2 1
# 1 3 3
# 2 3 1
# 2 4 5
# 3 4 2

# 0번 인덱스는 편의상 만든 것이기에 무시하면 되고, 예상대로 0 1 2 4 INF 가 나오는것을 확인할 수 있다.

# 출력 예시
# [100000000.0, 0, 1, 2, 4, 100000000.0]