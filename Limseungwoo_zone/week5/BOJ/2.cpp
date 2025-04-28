/*
BOJ 18290
격자판으로 입력받지만 본질적으로는 조합으로 접근해야 한다.
*/

#include <bits/stdc++.h>
using namespace std;

vector<pair<int, int>> v, picked;
priority_queue<int> sums;
int maps[12][12];
int visited[12][12];
int n, m, k, sum, answer = INT_MIN;

bool isNear(pair<int, int> cur) {
  int dy[] = {1, 0, -1, 0};
  int dx[] = {0, 1, 0, -1};

  for (int i = 0; i < 4; i++) {
    int ny = cur.first + dy[i];
    int nx = cur.second + dx[i];

    if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
    if (visited[ny][nx]) return true;
  }
  return false;
}

void go(pair<int, int> p, int depth, int sum) {
  if (depth == k) {
    answer = max(answer, sum);
    return;
  }
  for (int i = p.first; i < n; i++) {
    // 조건을 붙여주지 않으면 행이 넘어가도 p.second부터 탐색
    for (int j = (i == p.first ? p.second : 0); j < m; j++) {
      if (isNear({i, j})) continue;
      visited[i][j] = 1;

      int next_i = i;
      int next_j = j + 1;

      if (next_j == m) {
        next_i++;
        next_j = 0;
      }
      go({next_i, next_j}, depth + 1, sum + maps[i][j]);

      visited[i][j] = 0;
    }
  }
}

int main() {
  cin >> n >> m >> k;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> maps[i][j];
    }
  }

  go({0, 0}, 0, 0);

  cout << answer << "\n";
}