import sys
import heapq
input = sys.stdin.readline

N = int(input())  # 수업의 수 N 입력받기
classes = [tuple(map(int, input().split())) for _ in range(N)]  # 각 수업의 시작/종료 시간 입력받아 튜플로 저장

classes.sort()  # 수업을 시작 시간 기준으로 정렬 (가장 빠른 시간 순서대로 배정하기 위함)

rooms = []
heapq.heappush(rooms, classes[0][1])  # 첫 수업의 종료시간 push

for i in range(1, N):
    start, end = classes[i]
    
    # 가장 빨리 끝나는 수업이 끝났으면 → 해당 강의실 재사용 가능
    if rooms[0] <= start:
        heapq.heappop(rooms)  # 강의실 비우고
    heapq.heappush(rooms, end)  # 새 수업 종료시간 추가

# rooms 크기 = 필요한 최소 강의실 수
print(len(rooms))

# rooms = []  # 현재 사용 중인 강의실들의 '종료 시간'을 저장하는 리스트

# for start, end in classes:
#     allocated = False  # 현재 수업이 기존 강의실에 배정됐는지 여부
    
#     # 현재 수업의 시작 시간과 비교해서, 기존 강의실 중 사용 가능한 곳이 있는지 확인
#     # 최악의 경우에 시간복잡도 O(N^2)....
#     for i in range(len(rooms)):
#         # 조건: 현재 강의실이 비는 시간(rooms[i])이 새 수업의 시작 시간 이전이거나 같은 경우
#         # 즉, 이전 수업이 끝난 후 바로 이어서 이 수업을 들을 수 있다면 같은 강의실 재사용 가능
#         if rooms[i] <= start:
#             rooms[i] = end  # 그 강의실을 이 수업에 배정하고, 종료 시간 갱신
#             allocated = True  # 수업이 강의실에 배정되었음을 표시
#             break  # 더 이상 강의실 찾을 필요 없으므로 반복 종료

#     # 기존 강의실 중 사용할 수 있는 곳이 없었다면, 새 강의실을 하나 더 추가해야 함
#     if not allocated:
#         rooms.append(end)  # 이 수업을 위해 새로운 강의실을 추가하고, 종료 시간 저장

# # 최종적으로 필요한 강의실의 개수는 rooms 리스트의 길이
# print(len(rooms))