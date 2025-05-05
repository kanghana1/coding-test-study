// BOJ 1038
#include <bits/stdc++.h>
using namespace std;

int n, cur_depth, cnt = -1;
vector<int> picked, answer, numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

void go(int depth) {
  if (depth == cur_depth) {
    cnt++;
    if (cnt == n) {
      answer = picked;
      for (int a : answer) {
        cout << a;
      }
      cout << "\n";
      exit(0);
    }
    return;
  }
  for (int n : numbers) {
    if (n < picked[picked.size() - 1]) {
      picked.push_back(n);
      go(depth + 1);
      picked.pop_back();
    } else
      break;
  }
}
int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> n;
  // if (n > 1022) {  // 9876543210의 count
  //   cout << -1 << "\n";
  //   exit(0);
  // }
  for (int i = 1; i <= 10; i++) {
    cur_depth = i;
    for (int j = 0; j <= 9; j++) {
      picked = {j};
      go(1);
      if (cnt == n) break;
    }
    if (cnt == n) break;
  }
  if (cnt != n) {
    cout << -1 << "\n";
    exit(0);
  }
}