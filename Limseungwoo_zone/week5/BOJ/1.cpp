// BOJ 15654
#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> v, picked;
int visited[8];

void go(int depth) {
  if (depth == m) {
    for (int a : picked) {
      cout << a << " ";
    }
    cout << "\n";
    return;
  }

  for (int i = 0; i < n; i++) {
    if (visited[i]) continue;

    visited[i] = 1;
    picked.push_back(v[i]);
    go(depth + 1);
    picked.pop_back();
    visited[i] = 0;
  }
}

int main() {
  cin >> n >> m;

  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    v.push_back(tmp);
  }
  sort(v.begin(), v.end());

  go(0);
}