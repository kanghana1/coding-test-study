#include <bits/stdc++.h>
using namespace std;

int n, m, cnt, mx, visit_id;
vector<int> trust[100004], answer;
int visited[100004];

void dfs(int num) {
  visited[num] = visit_id;
  cnt++;

  for (int a : trust[num]) {
    if (visited[a] != visit_id) {
      dfs(a);
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> n >> m;
  vector<int> root(n + 1, 1);
  for (int i = 0; i < m; i++) {
    int a, b;
    cin >> a >> b;
    trust[b].push_back(a);
  }

  for (int i = 1; i <= n; i++) {
    cnt = 0;
    visit_id++;
    // 0으로 초기화하는 연산의 시간복잡도 때문에 visit_id 도입
    // memset(visited, 0, sizeof(visited));
    dfs(i);

    if (cnt > mx) {
      mx = cnt;
      answer.clear();
      answer.push_back(i);
    } else if (cnt == mx) {
      answer.push_back(i);
    }
  }

  sort(answer.begin(), answer.end());

  for (int a : answer) {
    cout << a << " ";
  }
}