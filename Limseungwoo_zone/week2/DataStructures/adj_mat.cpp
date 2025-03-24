#include <bits/stdc++.h>
using namespace std;

const int MAX = 1001;
int graph[MAX][MAX];

int main() {
  int n, m;
  cin >> n >> m;

  for (int i = 0; i < m; i++) {
    int u, v;
    cin >> u >> v;
    graph[u][v] = 1;
    graph[v][u] = 1;
  }

  for (int i = 1; i <= n; i++) {
    if (graph[1][i]) {
      cout << i << " ";
    }
  }
}
