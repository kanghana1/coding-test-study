// BOJ 1946
/*
우선순위 큐를 사용하여 두 점수 중 하나의 점수만이라도 정렬하여 최대한 계산을
줄이고자 하였다. 그 후에, 정렬되지 않은 순위에 대하여 정렬된 순위가 높은 것부터
내려가면서 여태까지 나왔던 순위보다 높으면 더해주는 방식으로 해결
*/
#include <bits/stdc++.h>
using namespace std;
int t, n;
int ans[24];

bool compare(pair<int, int> x, pair<int, int> y) { return x.first < y.first; }
int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> t;
  for (int i = 0; i < t; i++) {
    cin >> n;
    vector<pair<int, int>> v;
    int cnt = 1;
    for (int j = 0; j < n; j++) {
      int x, y;
      cin >> x >> y;
      v.push_back({x, y});
    }
    sort(v.begin(), v.end(), compare);
    int m = v[0].second;
    for (int k = 0; k < v.size() - 1; k++) {
      if (v[k + 1].second < m) {
        cnt++;
        m = v[k + 1].second;
      }
    }
    ans[i] = cnt;
  }
  for (int i = 0; i < t; i++) {
    cout << ans[i] << "\n";
  }
}