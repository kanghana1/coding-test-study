// BOJ 2206
#include <bits/stdc++.h>
using namespace std;

int maps[1004][1004];
int dist[1004][1004];
int visited[1004][1004];
vector<pair<int, int>> walls;
vector<int> v;
queue<pair<int, int>> q;
bool flag = false;
int N, M;
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int answer = INT_MAX;

void bfs(int y, int x) {
  q.push({y, x});
  visited[y][x] = 1;
  dist[y][x] = 0;

  while (!q.empty()) {
    int cur_y = q.front().first;
    int cur_x = q.front().second;
    q.pop();

    for (int i = 0; i < 4; i++) {
      int ny = cur_y + dy[i];
      int nx = cur_x + dx[i];

      if (nx < 1 || ny < 1 || nx > M || ny > N || visited[ny][nx]) continue;
      if (maps[ny][nx]) continue;
      if (nx == M && ny == N) flag = true;
      q.push({ny, nx});
      visited[ny][nx] = 1;
      dist[ny][nx] = dist[cur_y][cur_x] + 1;
    }
  }
}

int main() {
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    string tmp;
    cin >> tmp;
    for (int j = 1; j <= M; j++) {
      maps[i][j] = tmp[j - 1] - '0';
      if (maps[i][j]) {
        walls.push_back({i, j});
      }
    }
  }
  for (auto wall : walls) {
    memset(dist, 0, sizeof(dist));
    memset(visited, 0, sizeof(visited));

    maps[wall.first][wall.second] = 0;
    bfs(1, 1);

    if (dist[N][M]) v.push_back(dist[N][M]);
    maps[wall.first][wall.second] = 1;
  }

  memset(dist, 0, sizeof(dist));
  memset(visited, 0, sizeof(visited));
  bfs(1, 1);
  if (dist[N][M]) v.push_back(dist[N][M]);

  for (int ans : v) {
    answer = min(ans, answer);
  }
  if (!flag) answer = -2;
  cout << answer + 1 << "\n";
}
