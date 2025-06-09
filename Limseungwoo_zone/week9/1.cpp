// BOJ 14499
#include <bits/stdc++.h>
using namespace std;

int n, m, x, y, k, cnt = 0, btm = 6;
int e_idx, w_idx, n_idx, s_idx;
int maps[24][24];
int dice[7];

vector<int> inst;  // 명령을 담는 용도
vector<vector<int>> delta_dir = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};

void roll_dice(int dir) {
  int tmp;
  if (dir == 1) {  // East
    tmp = dice[1];
    dice[1] = dice[4];
    dice[4] = dice[6];
    dice[6] = dice[3];
    dice[3] = tmp;
  } else if (dir == 2) {  // West
    tmp = dice[1];
    dice[1] = dice[3];
    dice[3] = dice[6];
    dice[6] = dice[4];
    dice[4] = tmp;
  } else if (dir == 3) {  // North
    tmp = dice[1];
    dice[1] = dice[5];
    dice[5] = dice[6];
    dice[6] = dice[2];
    dice[2] = tmp;
  }
  // 남쪽
  else if (dir == 4) {
    tmp = dice[1];
    dice[1] = dice[2];
    dice[2] = dice[6];
    dice[6] = dice[5];
    dice[5] = tmp;
  }
}

void execute(int btm_noon, int direction, int cur_y, int cur_x) {
  int dice_y = cur_y + delta_dir[direction][0];
  int dice_x = cur_x + delta_dir[direction][1];

  if (dice_y < 0 || dice_x < 0 || dice_y >= n || dice_x >= m) {
    return;
  } else {
    y = dice_y;
    x = dice_x;
  }

  roll_dice(direction);

  if (maps[y][x] == 0) {
    maps[y][x] = dice[6];
  } else {
    dice[6] = maps[y][x];
    maps[y][x] = 0;
  }

  cout << dice[1] << "\n";
}

int main() {
  cin >> n >> m >> y >> x >> k;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> maps[i][j];
    }
  }

  for (int i = 0; i < k; i++) {
    int tmp;
    cin >> tmp;
    inst.push_back(tmp);
  }

  for (int is : inst) {
    execute(btm, is, y, x);
  }
}