// BOJ 2531
#include <bits/stdc++.h>
using namespace std;

int sushi[30004], cnt[30004], kind;
int N, d, k, c, lp, rp, answer;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> N >> d >> k >> c;
  for (int i = 0; i < N; i++) {
    cin >> sushi[i];
  }
  lp = 0;
  rp = k - 1;
  for (int i = lp; i <= rp; i++) {
    if (cnt[sushi[i]] == 0) kind++;
    cnt[sushi[i]]++;
  }
  answer = cnt[c] == 0 ? kind + 1 : kind;

  while (lp < N) {
    cnt[sushi[lp]]--;
    if (cnt[sushi[lp]] == 0) kind--;
    lp++;
    rp++;
    if (rp == N) rp = 0;
    if (cnt[sushi[rp]] == 0) kind++;
    cnt[sushi[rp]]++;
    answer = cnt[c] == 0 ? max(answer, kind + 1) : max(answer, kind);
  }

  cout << answer << "\n";
}