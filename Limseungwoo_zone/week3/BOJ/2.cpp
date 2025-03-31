#include <bits/stdc++.h>
using namespace std;

int dir_num = 0;
char maps[104][104];
int mirror[104][104][5];

int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int w, h;
queue<pair<int, int>> cord;

void dijkstra(int start_y, int start_x) {
  priority_queue<pair<int, pair<int, pair<int, int>>>,
                 vector<pair<int, pair<int, pair<int, int>>>>,
                 greater<pair<int, pair<int, pair<int, int>>>>>
      pq;
  pq.push({0, {4, {start_y, start_x}}});
  mirror[start_y][start_x][4] = 0;

  while (!pq.empty()) {
    int cur_cost = pq.top().first;
    int cur_dir = pq.top().second.first;
    auto [cur_y, cur_x] = pq.top().second.second;

    pq.pop();

    for (int i = 0; i < 4; i++) {
      if ((cur_dir + 2) % 4 == i && cur_dir != 4) continue;

      int next_y = cur_y + dy[i];
      int next_x = cur_x + dx[i];

      if (next_y < 0 || next_x < 0 || next_y >= h || next_x >= w) continue;
      if (maps[next_y][next_x] == '*') continue;

      int next_cost = cur_dir != i ? 1 : 0;
      if (cur_dir == 4) next_cost = 0;

      if (mirror[next_y][next_x][i] > cur_cost + next_cost) {
        mirror[next_y][next_x][i] = cur_cost + next_cost;
        pq.push({mirror[next_y][next_x][i], {i, {next_y, next_x}}});
      }
    }
  }
}

int main() {
  cin >> w >> h;

  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      fill(mirror[i][j], mirror[i][j] + 10, INT_MAX);
    }
  }

  for (int i = 0; i < h; i++) {
    string tmp;
    cin >> tmp;
    for (int j = 0; j < w; j++) {
      maps[i][j] = tmp[j];
      if (tmp[j] == 'C') cord.push({i, j});
    }
  }
  auto [sy, sx] = cord.front();
  cord.pop();
  auto [ey, ex] = cord.front();

  dijkstra(sy, sx);

  // for (int i = 0; i < h; i++) {
  //   for (int j = 0; j < w; j++) {
  //     cout << mirror[i][j] << " ";
  //   }
  //   cout << endl;
  // }
  int ans = INT_MAX;
  for (int i = 0; i < 5; i++) {
    ans = min(mirror[ey][ex][i], ans);
  }
  cout << ans << endl;
}