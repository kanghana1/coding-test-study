// BOK 1976
#include <bits/stdc++.h>
using namespace std;
int n, m;
vector<vector<int>> route;
int visited[204];
vector<int> answer;
queue<int> q;

int main() {
  cin >> n;
  cin >> m;

  route.resize(n + 1);
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      int tmp;
      cin >> tmp;
      if (!tmp) continue;
      route[i].push_back(j);
    }
  }
  answer.resize(m);
  for (int i = 0; i < m; i++) {
    cin >> answer[i];
  }

  q.push(answer[0]);
  visited[answer[0]] = 1;

  while (!q.empty()) {
    int cur = q.front();
    q.pop();

    for (int r : route[cur]) {
      if (!visited[r]) {
        visited[r] = 1;
        q.push(r);
      }
    }
  }

  bool flag = true;
  for (int ans : answer) {
    if (!visited[ans]) {
      flag = false;
    }
  }

  cout << (flag ? "YES" : "NO") << "\n";
}