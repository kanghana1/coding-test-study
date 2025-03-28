#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;
const int MAX = 1001;

vector<pair<int, int>> graph[MAX];
int dist[MAX];
int n, m, start;
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
      int next_node = next.first;
      int next_cost = next.second;

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
