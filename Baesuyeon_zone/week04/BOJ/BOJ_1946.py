import sys
input = sys.stdin.readline

T = int(input())  # 테스트 케이스 개수

def final_member(applicants):
    applicants.sort() # 서류 기준으로 오름차순 정렬
    count = 1  # 첫 번째 지원자는 무조건 통과(서류 1등일테니까..)
    min_interview = applicants[0][1]

    for i in range(1, len(applicants)): #모든 신청자 체킹
        # 현재 면접 순위가 지금까지 최소보다 더 좋으면 통과 (+1)
        if applicants[i][1] < min_interview:
            count += 1
            min_interview = applicants[i][1]

    return count

# 테스트 케이스만큼 반복
for _ in range(T):
    N = int(input())
    applicants = [tuple(map(int, input().split())) for _ in range(N)]
    print(final_member(applicants))
