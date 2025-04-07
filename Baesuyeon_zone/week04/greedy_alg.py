# 동전 거슬러 주기 예제 그리디 알고리즘 구현
import sys
input = sys.stdin.readline

N = int(input())
count = 0

coin_types=[500,100,50,10]

for coin in coin_types:
	count += N //coin
	N %= coin
	
# 아래의 코드처럼 함수화 가능
# def coin_changes(n, coins):
# 	count = 0
# 	for coin in coins :
# 		count += n //coin
# 		n %= coin
# 	return count

print(count)