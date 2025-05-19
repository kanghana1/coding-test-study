// BOJ 16234
#include <bits/stdc++.h>
using namespace std;

int ttang[54][54], visited[54][54];
int l, r, n, day;
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
bool moved = false;
vector<pair<int, int>> cord_ttang;

void go(int y, int x) {
  visited[y][x] = 1;
  cord_ttang.push_back({y, x});

  for (int i = 0; i < 4; i++) {
    int ny = y + dy[i];
    int nx = x + dx[i];

    if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx]) continue;
    if (abs(ttang[ny][nx] - ttang[y][x]) > r ||
        abs(ttang[ny][nx] - ttang[y][x]) < l)
      continue;

    go(ny, nx);
  }
}

int main() {
  cin >> n >> l >> r;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> ttang[i][j];
    }
  }
  while (true) {
    moved = false;
    memset(visited, 0, sizeof(visited));
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!visited[i][j]) {
          cord_ttang.clear();
          go(i, j);

          int sum = 0;
          for (auto cord : cord_ttang) {
            sum += ttang[cord.first][cord.second];
          }
          int avg = sum / cord_ttang.size();

          for (auto cord : cord_ttang) ttang[cord.first][cord.second] = avg;

          if (cord_ttang.size() > 1) moved = true;
        }
      }
    }
    if (!moved) {
      cout << day << "\n";
      break;
    } else
      day++;
  }
}