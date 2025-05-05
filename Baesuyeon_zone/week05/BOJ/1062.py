import sys
from itertools import combinations
input = sys.stdin.readline

N, K = map(int, input().split()) # N, K 입력받기

# 예외 처리 1) K < 5이면 필수 알파벳도 못 가르침
if K < 5:
    print(0)
    sys.exit()

# 예외 처리 2) 알파벳 모두 가능 → 모든 단어 읽기 가능
if K == 26:
    print(N)
    sys.exit()

# 필수로 가르쳐야 할 알파벳 5개 고정
essential = {'a', 'n', 't', 'i', 'c'}

# 입력 단어 리스트에서 앞뒤 anta, tica 제거하고 필요한 알파벳만 추출
words = []
for _ in range(N):
    word = input().strip()
    trimmed = set(word[4:-4])  # set을 이용하여 "anta"와 "tica" 제거
    words.append(trimmed)

# 전체 알파벳 26개 중에서 필수 5개를 제외한 나머지 후보
all_letters = set('abcdefghijklmnopqrstuvwxyz')
candidates = list(all_letters - essential)

max_readable = 0  # 정답 후보

# 후보 알파벳 중 K-5개를 고르는 모든 조합을 탐색
for comb in combinations(candidates, K - 5):
    learned = set(comb) | essential  # 현재 조합에 필수 알파벳을 합친 가르친 알파벳 집합
    count = 0
    for word in words:
        if word.issubset(learned):  # 단어가 모두 가르친 알파벳으로 이루어졌는지
            count += 1
    max_readable = max(max_readable, count)  # 최대 단어 수 갱신

print(max_readable)
