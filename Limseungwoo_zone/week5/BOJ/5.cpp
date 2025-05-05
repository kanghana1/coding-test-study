// BOJ 30804
#include <bits/stdc++.h>
using namespace std;

int tanghuru[200004];

int cnt[10];  // 포인터 사이의 과일 개수 관리
int lp = 0, rp = 0, max_len, n, kind;

int main() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> tanghuru[i];
  }

  while (rp < n) {
    if (cnt[tanghuru[rp]] == 0) {
      kind++;
    }
    cnt[tanghuru[rp]]++;

    while (kind > 2) {
      if (cnt[tanghuru[lp]] == 1) {
        kind--;
      }
      cnt[tanghuru[lp]]--;
      lp++;
    }
    max_len = max(max_len, rp - lp + 1);
    rp++;
  }

  cout << max_len << "\n";
}
