#include <bits/stdc++.h>
using namespace std;

const int MAX = 1001;
vector<int> graph[MAX];

int main() {
  int n, m;  // n: 정점 수, m: 간선 수
  cin >> n >> m;

  for (int i = 0; i < m; i++) {
    int u, v;
    cin >> u >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);  // 무방향 그래프
  }

  // 각 정점에 연결된 정점들 출력
  for (int i = 1; i <= n; i++) {
    cout << i << ": ";
    for (int next : graph[i]) {
      cout << next << " ";
    }
    cout << endl;
  }
}
