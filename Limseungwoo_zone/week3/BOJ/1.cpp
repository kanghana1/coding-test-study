// BOJ 18352
#include <bits/stdc++.h>
using namespace std;
int MAX = 1e9;

vector<pair<int, int>> graph[300004];
int dist[300004];

// 다익스트라 알고리즘
void dijkstra(int start) {
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;
  dist[start] = 0;
  pq.push({0, start});
  while (!pq.empty()) {
    int cost = pq.top().first;
    int cur = pq.top().second;

    pq.pop();

    if (dist[cur] < cost) continue;

    for (auto next : graph[cur]) {
      int next_cost = next.first;
      int next_node = next.second;

      if (dist[next_node] > cost + next_cost) {
        dist[next_node] = cost + next_cost;
        pq.push({dist[next_node], next_node});
      }
    }
  }
}
int n, m, k, x;
int main() {
  fill(dist, dist + 300004, INT_MAX);
  cin >> n >> m >> k >> x;

  for (int i = 0; i < m; i++) {
    int u, v;
    cin >> u >> v;
    // 가중치가 전부 1임
    graph[u].push_back({1, v});
  }

  dijkstra(x);

  bool found = false;
  for (int i = 1; i <= n; i++) {
    if (dist[i] == k) {
      cout << i << '\n';
      found = true;
    }
  }
  if (!found) cout << -1 << '\n';
}
