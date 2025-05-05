// BOJ 15683
#include <bits/stdc++.h>
using namespace std;

int n, m, cnt = 0, answer = INT_MAX;
int maps[10][10];
int delta[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};  // 상, 우, 하, 좌

vector<pair<int, int>> cctvs;
vector<vector<vector<int>>> cctv_dir = {
    {},
    {{0}, {1}, {2}, {3}},                          // type 1
    {{0, 2}, {1, 3}},                              // type 2
    {{0, 1}, {1, 2}, {2, 3}, {3, 0}},              // type 3
    {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},  // type 4
    {{0, 1, 2, 3}}};                               // type 5

void watch(int y, int x, int dir, int (*tmp)[10]) {
  int ny = y + delta[dir][0];
  int nx = x + delta[dir][1];

  while (ny >= 0 && nx >= 0 && ny < n && nx < m && tmp[ny][nx] != 6) {
    if (tmp[ny][nx] == 0) tmp[ny][nx] = 7;
    ny += delta[dir][0];
    nx += delta[dir][1];
  }
}

void go(int depth, int office[10][10]) {
  if (cctvs.size() == depth) {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (office[i][j] == 0) cnt++;
      }
    }
    answer = min(answer, cnt);
    return;
  }

  int y = cctvs[depth].first;
  int x = cctvs[depth].second;

  int cam_type = maps[y][x];

  for (auto d : cctv_dir[cam_type]) {
    int tmp[10][10];
    memcpy(tmp, office, sizeof(tmp));  // 원상 복구를 위한 임의의 map 추가

    for (int dir : d) {
      watch(y, x, dir, tmp);
    }
    go(depth + 1, tmp);
  }
}

int main() {
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int tmp;
      cin >> tmp;
      maps[i][j] = tmp;

      if (tmp != 0 && tmp != 6) {
        cctvs.push_back({i, j});
        cnt++;
      }
    }
  }

  go(0, maps);

  cout << answer << "\n";
}