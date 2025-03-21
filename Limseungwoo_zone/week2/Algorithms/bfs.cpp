#include <bits/stdc++.h>
using namespace std;

int visited[10];
vector<int> v[10];

void bfs(int num) {
  queue<int> q;
  q.push(num);
  visited[num] = 1;

  while (!q.empty()) {
    // 큐에 값이 있을 경우, 계속 반복 실행
    int now = q.front();
    q.pop();
    cout << now << " ";
    for (int i = 0; i < v[now].size(); i++) {
      int next = v[now][i];  // 현재 노드와 연결된 노드 탐색
      if (!visited[next]) {
        q.push(next);
        visited[next] = true;
      }
    }
  }
}

int main(void) {
  v[1].push_back(2);
  v[2].push_back(1);

  v[1].push_back(3);
  v[3].push_back(1);

  v[2].push_back(4);
  v[4].push_back(2);

  v[2].push_back(5);
  v[5].push_back(2);

  v[4].push_back(8);
  v[8].push_back(4);

  v[5].push_back(9);
  v[9].push_back(5);

  v[3].push_back(6);
  v[6].push_back(3);

  v[3].push_back(7);
  v[7].push_back(3);

  bfs(1);

  return 0;
}
