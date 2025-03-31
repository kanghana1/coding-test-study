// BOJ 2665
/*
처음 문제를 봤을 때, 최단 경로가 아닌 방의 개수를 최소로 바꾸는 문제라 조금
당황스러웠지만 어찌되었든 다시 생각해보면 다익스트라 알고리즘에 가중치를 1로
고정하고 break_cnt 배열에 방을 바꾼 횟수를 할당하는 식으로 문제를 해결하면 될
것이라고 생각했디.
*/
#include <bits/stdc++.h>
using namespace std;

int n;
int maps[54][54];
int break_cnt[54][54];
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};

void dijkstra(int start_y, int start_x) {
  // 가중치(또는 방을 바꾼 횟수)
  priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>,
                 greater<pair<int, pair<int, int>>>>
      pq;
  // 시작점에서의 방을 바꾼 횟수는 0
  break_cnt[start_y][start_x] = 0;
  pq.push({0, {start_y, start_x}});

  while (!pq.empty()) {
    int cur_y = pq.top().second.first;
    int cur_x = pq.top().second.second;
    int cost = pq.top().first;

    pq.pop();

    if (break_cnt[cur_y][cur_x] < cost) continue;
    for (int i = 0; i < 4; i++) {
      int next_y = cur_y + dy[i];
      int next_x = cur_x + dx[i];

      if (next_y < 1 || next_x < 1 || next_y > n || next_x > n) continue;

      // 방을 바꾸는 횟수를 다음 방의 상태를 보고 결정
      int next_cost = maps[next_y][next_x] ? 0 : 1;

      if (break_cnt[next_y][next_x] > next_cost + cost) {
        break_cnt[next_y][next_x] = next_cost + cost;
        pq.push({break_cnt[next_y][next_x], {next_y, next_x}});
      }
    }
  }
}

int main() {
  cin >> n;
  for (int i = 1; i <= n; i++) {
    fill(break_cnt[i], break_cnt[i] + n + 4, INT_MAX);
  }
  for (int i = 1; i <= n; i++) {
    string tmp;
    cin >> tmp;
    for (int j = 0; j < n; j++) {
      maps[i][j + 1] = tmp[j] - '0';
    }
  }

  dijkstra(1, 1);

  cout << break_cnt[n][n] << "\n";
}