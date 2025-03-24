'''
다른 사람의 풀이를 보고 먼저 자바로 이해한 후에,
이해한대로 파이썬으로 옮겨서 구현하였습니다.
한 시간에서 두 시간 정도 문제를 잡고 생각해봤지만, BFS에서 queue에 좌표를 넣을 때 어떻게 좌표를 넣어야할지 감이 잡히지 않았습니다.
함부로 queue에 좌표를 추가할 수 없었습니다.
if문으로 일일이 비교하자니 시간이 오래걸릴 것 같았고,
좌표의 위치를 일일이 정하자니 상어의 위치가 계속해서 변하는 게 변수였습니다.


가장 중요한 것은 똑같이 bfs를 실시하는데, 방문하는 노드가 우선순위가 정해져있다는 것입니다.
queue를 우선순위큐를 이용해서 꺼낼 때 (가장 거리가 가까운 순, y좌표가 작은 순, x좌표가 작은 순)으로 꺼낼 수 있도록 하였습니다.
파이썬에서는 heapq에 넣을 때 우선순위를 지정해서 넣으면 됩니다. 특히나 heapq는 기본이 최소힙이기 때문에 이 문제에 맞는 자료구조였습니다.
heapq.push(q, (distance, y, x)) 순으로 넣으면 작은 순대로 정렬됩니다.

일주일 정도 뒤에 다시 한 번 풀어볼 예정입니다. 
'''
import sys
import heapq

input = sys.stdin.readline
dx = [0, -1, 1, 0]
dy = [-1, 0, 0, 1]
n = int(input()) # 공간의 크기
tank = [[0] * n for _ in range(n)]

# 맵을 만들고 상어의 현재 위치를 찾습니다. 
for i in range(n):
    inputs = list(map(int, input().split()))
    for j in range(n):
        tank[i][j] = inputs[j]

        if inputs[j] == 9:
            tank[i][j] = 0
            cur = (j, i)

size = 2 # 상어의 최초 크기
eat = 0 # 상어가 먹은 물고기 수(최초 선언)
move = 0 # 상어가 움직인 최종 거리(이자 시간)

while True:
    ck = False # 먹이를 먹었는지 여부
    q = [] # 우선순위 큐
    heapq.heappush(q, (0, cur[1], cur[0])) # 현재 위치를 큐에 넣는데, 우선순위를 (거리, y좌표, x좌표 순으로 합니다. 
    visit = [[False] * n for _ in range(n)] # 방문 여부를 체크합니다.
    visit[cur[1]][cur[0]] = True # 현재 위치는 방문했다고 체크합니다.

    while q:
        dist, y, x = heapq.heappop(q)

        # 현재 위치가 먹을 수 있는 경우의 수
            # 물고기가 있고, 상어의 현재 크기보다 작은 경우
        if (tank[y][x] != 0 and tank[y][x] < size):
            ck = True
            eat += 1
            move += dist # 이동한 거리를 더해줍니다. 
            cur = (x, y) # 현재 위치를 물고기를 먹은 위치로 바꿔줍니다. (갱신해주지 않아서 무한 루프에 빠졌었습니다.
            tank[y][x] = 0
            break

        # 먹이를 먹기 위해서 움직여야 한다면
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]

            # 맵을 벗어나지 않고, 방문하지 않았으며, 상어의 크기가 본인보다 크지 않을 때만 queue에 넣도록 합니다.
            if (0 <= nx < n and 0 <= ny < n and visit[ny][nx] == False and tank[ny][nx] <= size):
                visit[ny][nx] = True
                heapq.heappush(q, (dist + 1, ny, nx))
            


    # 가능한 모든 위치를 탐색했거나, 먹이를 찾아서 먹었을 때
    if (ck == False): # 더 이상 먹을 물고기가 없다는 의미이므로
        break

    if eat == size: # 먹이를 찾아서 먹었는데, 크기만큼 먹었다면
        size += 1   # 크기를 키워줍니다. 
        eat = 0

print(move)
