import sys
input = sys.stdin.readline

N = int(input()) # N 입력 받고
classes = [tuple(map(int,input().split()))for _ in range(N)] # N의 수만큼 강의 시간 입력받기

classes.sort() # 시작 시간 기준 정렬

rooms = []  # 각 강의실 상황 파악

for start, end in classes:
    allocated = False
    for i in range(len(rooms)):
        if rooms[i] <= start: # 현재 강의의 시작시간이 이제 시작할 강의보다 작으면, 
            rooms[i] = end # 현재 강의의 종료시간이 종료시간이면 할당
            allocated = True
            break
    if not allocated: # 할당받지 못했다면,
        rooms.append(end)  # 새로운 강의실 추가

print(len(rooms))