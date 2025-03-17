#include <bits/stdc++.h>
using namespace std;

int n, m;
string str;
int answer[10];

void dfs(int level) {
  if (level == m + 1) {
    for (int i = 1; i <= m; i++) {
      cout << answer[i] << " ";
    }
    cout << "\n";
  } else {
    for (int i = 1; i <= n; i++) {
      answer[level] = i;
      dfs(level + 1);
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> n >> m;

  dfs(1);
}