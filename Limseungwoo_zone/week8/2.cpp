// BOJ 1922
#include <bits/stdc++.h>
using namespace std;

vector<vector<pair<int, int>>> com;
int visited[1004];
int n, m;

int main() {
  cin >> n;
  cin >> m;

  com.resize(n + 1);
  for (int i = 0; i < m; i++) {
    int a, b, c;
    cin >> a >> b >> c;
    com[a].push_back({b, c});
    com[b].push_back({a, c});
  }

  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      q;
  q.push({0, 1});
  int ans = 0;
  while (!q.empty()) {
    auto [cost, cur] = q.top();
    if (visited[cur]) {
      q.pop();
      continue;
    }
    visited[cur] = 1;

    // cout << cur << "\n";

    q.pop();
    ans += cost;

    for (auto [next, nCost] : com[cur]) {
      if (!visited[next]) {
        q.push({nCost, next});
      }
    }
  }

  cout << ans << "\n";
}