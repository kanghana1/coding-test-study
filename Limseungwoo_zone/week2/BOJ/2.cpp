// BOJ 16236
#include <bits/stdc++.h>
using namespace std;
int N, x, y;
int maps[24][24];
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int dist[24][24];

struct Shark {
  int y, x;
  int size = 2;
  int eat = 0;
  int time = 0;
};
Shark shark;

bool bfs() {
  memset(dist, -1, sizeof(dist));
  queue<pair<int, int>> q;
  q.push({shark.y, shark.x});
  dist[shark.y][shark.x] = 0;

  vector<tuple<int, int, int>> fishList;

  while (!q.empty()) {
    auto [y, x] = q.front();
    q.pop();

    for (int i = 0; i < 4; i++) {
      int ny = y + dy[i];
      int nx = x + dx[i];

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

      if (dist[ny][nx] != -1) continue;

      if (maps[ny][nx] > shark.size) continue;

      // 이동 가능
      dist[ny][nx] = dist[y][x] + 1;

      // 먹을 수 있는 물고기라면 후보에 추가
      if (maps[ny][nx] != 0 && maps[ny][nx] < shark.size) {
        fishList.push_back({dist[ny][nx], ny, nx});
      }

      // 큐에 넣기
      q.push({ny, nx});
    }
  }

  if (fishList.empty()) return false;

  sort(fishList.begin(), fishList.end());

  auto [d, ny, nx] = fishList[0];

  shark.time += d;
  shark.y = ny;
  shark.x = nx;
  shark.eat += 1;

  if (shark.eat == shark.size) {
    shark.size++;
    shark.eat = 0;
  }

  maps[ny][nx] = 0;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> N;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cin >> maps[i][j];
      if (maps[i][j] == 9) {
        shark.y = i;
        shark.x = j;
        maps[shark.y][shark.x] = 0;
      }
    }
  }

  while (true) {
    bool found = bfs();
    if (!found) break;
  }
  cout << shark.time << "\n";
}