// BOJ 1202
#include <bits/stdc++.h>
using namespace std;

vector<int> bags;
vector<pair<int, int>> jewels;
int n, k, m, v, c;
int answer = 0;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> n >> k;
  for (int i = 0; i < n; i++) {
    cin >> m >> v;
    jewels.push_back({m, v});
  }
  for (int i = 0; i < k; i++) {
    cin >> c;
    bags.push_back(c);
  }
  int idx = 0;
  sort(jewels.begin(), jewels.end());
  sort(bags.begin(), bags.end());
  priority_queue<int, vector<int>, less<int>> pq;
  for (int bag : bags) {
    for (int j = idx; j < jewels.size(); j++) {
      if (bag >= jewels[j].first) {
        pq.push(jewels[j].second);
        idx++;
      } else {
        break;
      }
    }
    if (!pq.empty()) {
      answer += pq.top();
      pq.pop();
    }
  }
  cout << answer << "\n";
}