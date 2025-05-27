# 문제 조건 요약
# N개의 멀티탭 구멍, K개의 전기용품 사용 순서

# 플러그를 뽑는 최소 횟수 구하기

# 적용 알고리즘
# 그리디 알고리즘
# 가장 나중에 다시 사용되거나 다시 사용되지 않는 전기용품을 우선적으로 뽑는다.

n, k = map(int, input().split())
schedule = list(map(int, input().split()))
multitap = []
result = 0

for i in range(k):
    device = schedule[i]

    if device in multitap:
        continue  # 이미 꽂혀있으면 패스

    if len(multitap) < n:
        multitap.append(device)  # 빈 자리가 있으면 꽂기
        continue

    # 플러그를 하나 빼야 함
    future = schedule[i+1:]
    idxs = []

    for m in multitap:
        if m not in future:
            idxs.append((1001, m))  # 다시 안 쓰는 애는 최우선 제거 대상
        else:
            idxs.append((future.index(m), m))  # 가장 나중에 쓰이는 애 기록

    idxs.sort(reverse=True)  # 가장 나중에 쓰이는 애가 앞으로 오게
    multitap.remove(idxs[0][1])
    multitap.append(device)
    result += 1

print(result)
