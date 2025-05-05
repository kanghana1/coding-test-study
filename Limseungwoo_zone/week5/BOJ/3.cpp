/*
BOJ 1062
a, n, t, i, c는 필수, 그 외에 알파벳의 조합 중에서 단어를 만들 수 있는 최대
경우를 구하면 됨
*/
#include <bits/stdc++.h>
using namespace std;

int n, k, answer = 0, antic = 0;

vector<int> words;

void go(int depth, int start) {
  if (depth == k - 5) {
    int cnt = 0;
    for (int mask : words) {
      if ((mask & antic) == mask) cnt++;
    }
    answer = max(answer, cnt);
    return;
  }

  for (int i = start; i < 26; i++) {
    if (antic & (1 << i)) continue;

    antic |= (1 << i);
    go(depth + 1, i + 1);
    antic &= ~(1 << i);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> n >> k;

  for (int i = 0; i < n; i++) {
    int mask = 0;
    string tmp;
    cin >> tmp;
    // 비트마스크 형식으로 저장
    for (char c : tmp) {
      mask |= (1 << (c - 'a'));
    }
    words.push_back(mask);
  }

  if (k < 5) {
    cout << 0 << "\n";
    return 0;
  }

  antic |= 1 << ('a' - 'a');  // .... 0000 0000
  antic |= 1 << ('c' - 'a');  // .... 0000 0100
  antic |= 1 << ('i' - 'a');
  antic |= 1 << ('n' - 'a');
  antic |= 1 << ('t' - 'a');

  go(0, 0);

  cout << answer << "\n";
}