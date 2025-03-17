#include <bits/stdc++.h>
using namespace std;

int n, maps[504][504], visited[504][504], answer;
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};

int dfs(int y, int x) {
  if (visited[y][x] != -1) return visited[y][x];
  visited[y][x] = 1;

  for (int i = 0; i < 4; i++) {
    int ny = y + dy[i];
    int nx = x + dx[i];

    if (ny >= n || nx >= n || ny < 0 || nx < 0) continue;

    if (maps[y][x] < maps[ny][nx]) {
      visited[y][x] = max(visited[y][x], dfs(ny, nx) + 1);
    }
  }
  return visited[y][x];
}

int main() {
  cin >> n;
  memset(visited, -1, sizeof(visited));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> maps[i][j];
    }
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      answer = max(answer, dfs(i, j));
    }
  }

  cout << answer << endl;
}