#include <bits/stdc++.h>
using namespace std;

int n, k, lp, rp, sum, answer;
int temp[100004];
int main() {
  cin >> n >> k;
  for (int i = 0; i < n; i++) {
    cin >> temp[i];
  }
  lp = 0;
  rp = lp + k - 1;
  for (int i = lp; i <= rp; i++) {
    sum += temp[i];
  }
  answer = sum;

  while (rp < n - 1) {
    sum -= temp[lp];
    lp++;
    rp++;
    sum += temp[rp];

    answer = max(answer, sum);
  }

  cout << answer << "\n";
}