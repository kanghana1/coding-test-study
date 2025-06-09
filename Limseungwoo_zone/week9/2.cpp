// BOJ 14500
#include <bits/stdc++.h>
using namespace std;

int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int n, m, answer = INT_MIN;
int maps[504][504];
int visited[504][504];

void solve(int y, int x, int sum, int cnt) {
  if (cnt == 4) {
    answer = max(sum, answer);
    return;
  }
  visited[y][x] = 1;

  for (int i = 0; i < 4; i++) {
    int ny = y + dy[i];
    int nx = x + dx[i];

    if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

    if (!visited[ny][nx]) {
      solve(ny, nx, sum + maps[y][x], cnt + 1);
    }
  }
  visited[y][x] = 0;
}

int T[4][4][2] = {{{0, 0}, {0, -1}, {0, 1}, {-1, 0}},
                  {{0, 0}, {0, -1}, {0, 1}, {1, 0}},
                  {{0, 0}, {-1, 0}, {1, 0}, {0, 1}},
                  {{0, 0}, {-1, 0}, {1, 0}, {0, -1}}};

void checkT(int y, int x) {
  for (int i = 0; i < 4; i++) {
    int sum = 0;
    bool flag = true;
    for (int j = 0; j < 4; j++) {
      int ny = y + T[i][j][0];
      int nx = x + T[i][j][1];

      if (ny < 0 || nx < 0 || ny >= n || nx >= m) break;

      sum += maps[ny][nx];
    }
    if (flag) answer = max(answer, sum);
  }
}

int main() {
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> maps[i][j];
    }
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      solve(i, j, 0, 0);
      checkT(i, j);
    }
  }

  cout << answer << "\n";
}