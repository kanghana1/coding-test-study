// BOJ 11053
#include <bits/stdc++.h>
using namespace std;

int n, answer = 1;
vector<int> v;

int main() {
  cin >> n;
  vector<int> dp(n, 1);
  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    v.push_back(tmp);
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < i; j++) {
      if (v[j] < v[i]) {
        dp[i] = max(dp[i], dp[j] + 1);
      }
    }
  }

  cout << *max_element(dp.begin(), dp.end()) << "\n";
}