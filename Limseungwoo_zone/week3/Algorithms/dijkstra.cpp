#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
const int MAX = 1001;

vector<pair<int, int>> graph[MAX];  // 그래프 선언
int dist[MAX];
int n, m, start;
void dijkstra(int start) {
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;           // 우선순위 큐를 사용하여 구현
  dist[start] = 0;  // 시작지점은 가중치 0으로 세팅
  pq.push({0, start});

  while (!pq.empty()) {
    int cost = pq.top().first;
    int cur = pq.top().second;

    pq.pop();
    if (dist[cur] < cost) continue;

    for (auto next : graph[cur]) {
      int next_node = next.first;
      int next_cost = next.second;
      // 현재 가중치보다 다음 노드를 거치는 경우의 가중치가 더 작은 경우
      if (dist[next_node] > cost + next_cost) {
        dist[next_node] = cost + next_cost;
        pq.push({dist[next_node], next_node});
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);

  cin >> n >> m >> start;

  for (int i = 0; i < m; i++) {
    int u, v, w;
    cin >> u >> v >> w;
    // w는 가중치 값
    graph[u].push_back({v, w});
  }

  fill(dist, dist + MAX, INF);
  dijkstra(start);

  for (int i = 1; i <= n; i++) {
    if (dist[i] == INF)
      cout << "INF\n";
    else
      cout << dist[i] << "\n";
  }
  return 0;
}
