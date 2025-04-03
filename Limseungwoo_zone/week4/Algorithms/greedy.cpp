/*
당신은 동전 교환기 앞에 서 있습니다.
교환기는 오직 500원, 100원, 50원, 10원짜리 동전만 가지고 있습니다.
당신이 내야 할 금액과, 교환기에 있는 동전의 개수가 주어질 때,
가장 적은 개수의 동전으로 금액을 맞출 수 있는지 판단하고,
가능하다면 사용한 동전의 개수를 출력하세요.
불가능하다면 -1을 출력하세요.
*/
#include <bits/stdc++.h>
using namespace std;
int x;
int coins[] = {500, 100, 50, 10};
int cnt[4];
int main() {
  cin >> x;
  for (int i = 0; i < 4; i++) {
    cin >> cnt[i];
  }

  int totalUsed = 0;

  for (int i = 0; i < 4; i++) {
    int use = min(x / coins[i], cnt[i]);
    x -= coins[i] * use;
    totalUsed += use;
  }

  if (x == 0)
    cout << totalUsed << "\n";
  else
    cout << -1 << "\n";
}