#include <bits/stdc++.h>
using namespace std;

int dy[] = {0, 1};  // 오른쪽, 아래
int dx[] = {1, 0};

int visited[5][5];
int maps[5][5];

int n;

void go(int y, int x) {
  visited[y][x] = 1;
  if (maps[y][x] == -1) {
    cout << "HaruHaru" << endl;
    exit(0);
  }
  for (int i = 0; i < 2; i++) {
    int ny = y + dy[i] * maps[y][x];
    int nx = x + dx[i] * maps[y][x];

    if (ny >= n || nx >= n || y < 0 || x < 0 || visited[ny][nx]) continue;

    go(ny, nx);
  }
}
int main() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> maps[i][j];
    }
  }
  go(0, 0);
  cout << "Hing" << endl;
}