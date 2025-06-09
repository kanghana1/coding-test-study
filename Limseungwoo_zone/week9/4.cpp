// BOJ 2133
#include <bits/stdc++.h>
using namespace std;

int n;
int dp[34];

int main() {
  cin >> n;
  if (n % 2 == 1) {
    cout << 0 << endl;
    return 0;
  }

  dp[0] = 1;
  dp[2] = 3;

  for (int i = 4; i <= n; i += 2) {
    for (int j = 2; j <= i; j += 2) {
      if (j == 2) {
        dp[i] = 3 * dp[i - 2];
      } else {
        dp[i] += dp[i - j] * 2;
      }
    }
  }

  cout << dp[n] << "\n";
}
