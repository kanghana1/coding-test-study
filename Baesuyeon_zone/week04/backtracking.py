# 백트래킹 알고리즘 구조

# 종료 조건 확인
# def backtrack(현재_상태):
#     if 정답인지(현재_상태):  # 종료 조건
#         해답_저장(현재_상태)
#         return

#종료 조건이 아니라면, 현재 상태에서 선택할 수 있는 모든 경우를 살펴보도록 for문으로 탐색
# for 가능한_선택 in 현재_상태의_모든_선택:
#     if 유망한_선택(가능한_선택):  # 가지치기 조건
#         선택(가능한_선택)
#         backtrack(새로운_상태)
#         선택_취소(가능한_선택)  # 원래 상태로 복구

#전체조건
# def backtrack(현재_상태):
#     if 정답인지(현재_상태):  # 종료 조건
#         해답_저장(현재_상태)
#         return

#     for 가능한_선택 in 현재_상태의_모든_선택:
#         if 유망한_선택(가능한_선택):  # 가지치기 조건
#             선택(가능한_선택)
#             backtrack(새로운_상태)
#             선택_취소(가능한_선택)  # 원래 상태로 복구

# 조합 생성 예제
def generate_combinations(nums, k):
    def backtrack(start, combination):
        if len(combination) == k:  # 종료 조건
            result.append(combination[:])
            return

        for i in range(start, len(nums)):
            combination.append(nums[i])  # 선택
            backtrack(i + 1, combination)  # 다음 선택으로 이동
            combination.pop()  # 선택 취소

    result = []
    backtrack(0, [])
    return result

# 실행 예시
nums = [1, 2, 3]
k = 2
print(generate_combinations(nums, k))  # 출력: [[1, 2], [1, 3], [2, 3]]
