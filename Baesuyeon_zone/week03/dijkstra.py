# for문을 사용해서 다익스트라 알고리즘 구현 -1
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

# heapq 사용해서 다익스트라 알고리즘 구현 -2
import heapq
import sys
input = sys.stdin.readline
INF = int(1e9) #무한을 의미하는 값으로 10억
 
#노드의 개수, 간선의 개수를 입력받기
n,m = map(int, input().split())
#시작 노드 번호를 입력받기
start = int(input())
#각 노드에 연결되어 있는 노드에 대한 정보를 담는 리스트 만들기
graph = [[] for i in range(n+1)]
#최단 거리 테이블을 무한으로 초기화
distance = [INF]*(n+1)
 
#모든 간선 정보를 입력받기
for _ in range(m):
    a,b,c = map(int, input().split())
    #a번 노드에서 b번 노드로 가는 비용이 c
    graph[a].append((b,c))
 
def dijkstra(start):
    q=[]
    #시작 노드로 가기 위한 최단 경로는 0으로 설정하여 큐에 삽입
    heapq.heappush(q, (0, start))
    distance[start]=0
    #q가 비어있지 않다면
    while q:
        #가장 최단 거리인 노드에 대한 정보 꺼내기
        dist, now = heapq.heappop(q)
        #현재 노드가 이미 처리됐다면 skip
        if distance[now] < dist:
            continue
        #현재 노드와 연결된 다른 인접한 노드 확인
        for i in graph[now]:
            cost = dist + i[1]
            #현재 노드를 거치면 이동 거리가 더 짧은 경우
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))
 
#다익스트라 알고리즘 실행
dijkstra(start)
 
#모든 노드로 가기 위한 최단 거리 출력
for i in range(1, n+1):
    #도달할 수 없는 경우, 무한 출력
    if distance[i]==INF:
        print("INF")
    else:
        print(distance[i])