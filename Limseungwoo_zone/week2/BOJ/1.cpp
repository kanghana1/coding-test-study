#include <bits/stdc++.h>
using namespace std;

int maps[1004][1004];
// 차원을 하나 더 추가해줌으로써 0: 아직 벽을 안부숨 1: 부숨
int visited[1004][1004][2];

struct State {
  int y, x;
  int broken;  // 벽을 부쉈는지 여부
};

int N, M;
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};

int bfs() {
  queue<State> q;
  q.push({1, 1, 0});
  visited[1][1][0] = 1;

  while (!q.empty()) {
    State cur = q.front();
    q.pop();
    int y = cur.y, x = cur.x, b = cur.broken;

    if (y == N && x == M) return visited[y][x][b];

    for (int i = 0; i < 4; i++) {
      int ny = y + dy[i];
      int nx = x + dx[i];
      if (nx < 1 || ny < 1 || nx > M || ny > N) continue;

      // 벽이 아니면서 아직 방문하지 않았다면
      if (!maps[ny][nx] && !visited[ny][nx][b]) {
        visited[ny][nx][b] = visited[y][x][b] + 1;
        q.push({ny, nx, b});
        // 벽이면서 아직 부순적이 없다면
      } else if (maps[ny][nx] && b == 0 && visited[ny][nx][1] == 0) {
        visited[ny][nx][1] = visited[y][x][b] + 1;
        q.push({ny, nx, 1});
      }
    }
  }
  return -1;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    string tmp;
    cin >> tmp;
    for (int j = 1; j <= M; j++) {
      maps[i][j] = tmp[j - 1] - '0';
    }
  }
  cout << bfs() << "\n";
}