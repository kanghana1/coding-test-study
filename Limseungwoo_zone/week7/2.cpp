// BOJ 2573
#include <bits/stdc++.h>
using namespace std;

int maps[304][304];
int visited[304][304];

int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int piece, n, m, day;

void go(int y, int x, int tmp[][304]) {
  visited[y][x] = 1;
  for (int i = 0; i < 4; i++) {
    int ny = y + dy[i];
    int nx = x + dx[i];

    if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
    if (maps[y][x] && maps[ny][nx] == 0 && tmp[y][x]) tmp[y][x] -= 1;
    if (visited[ny][nx]) continue;
    if (!maps[ny][nx]) continue;

    go(ny, nx, tmp);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> maps[i][j];
    }
  }

  while (true) {
    memset(visited, 0, sizeof(visited));
    int tmp[304][304];                // maps에 바로 수정을 가하면 다음에 조회할
    memcpy(tmp, maps, sizeof(maps));  // 값에 영향을 주므로
    piece = 0;                        // tmp에 기존값 복사 후 tmp로 연산
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!visited[i][j] && maps[i][j]) {
          go(i, j, tmp);
          piece++;  // 한 무리에서 dfs가 끝나면 piece 증가
        }
      }
    }
    memcpy(maps, tmp, sizeof(maps));
    day++;
    if (piece > 1) break;
    if (piece == 0) {
      cout << 0 << endl;
      exit(0);
    }
  }
  cout << day - 1 << "\n";
}
