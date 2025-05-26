// BOJ 1700
#include <bits/stdc++.h>
using namespace std;
vector<queue<int>> ele_idx;
vector<int> ele_order;
set<int> s;
int n, k, cnt;

int main() {
  cin >> n >> k;
  ele_idx.resize(104);
  for (int i = 0; i < k; i++) {
    int e;
    cin >> e;
    ele_idx[e].push(i);
    ele_order.push_back(e);
  }

  for (int ele : ele_order) {
    if (s.count(ele)) {    // 이미 꽂혀있으면 아무것도 안해도 됨
      ele_idx[ele].pop();  // 인덱스만 최신화
      continue;
    }
    if (s.size() >= n) {
      priority_queue<pair<int, int>, vector<pair<int, int>>> pq;
      for (int i : s) {
        if (ele_idx[i].size() > 0)
          pq.push({ele_idx[i].front(), i});
        else
          pq.push({INT_MAX, i});
      }
      int latest = pq.top().second;
      s.erase(latest);
      cnt++;
    }

    s.insert(ele);
    ele_idx[ele].pop();
  }

  cout << cnt << "\n";
}