#파이썬에서는 우선순위 큐의 활용을 위해 모듈을 제공하고 있다.
from queue import PriorityQueue

#우선순위 큐 생성
q = PriorityQueue()
q1 = PriorityQueue(maxsize=10) #maxsize를 활용하여 크기 제한

# 원소 추가
q.put(3)
q.put(4)
q.put(1)

q1.put((1, 'apple'))
q1.put((2, 'banana'))
q1.put((0, 'cherry'))

# 원소 삭제 및 반환
print("q에서 꺼낸 값:", q.get())  # 가장 작은 값부터 꺼냄 (1)
print("q에서 꺼낸 값:", q.get())  # 다음 작은 값 (3)
print("q에서 꺼낸 값:", q.get())  # 마지막 값 (4)

# q1에서는 (우선순위, 값) 형태로 저장했기 때문에 값만 가져오려면 [1]
print("q1에서 우선순위 높은 값:", q1.get()[1])  # cherry (우선순위 0)
print("q1에서 다음 값:", q1.get()[1])         # apple (우선순위 1)
print("q1에서 마지막 값:", q1.get()[1])        # banana (우선순위 2)