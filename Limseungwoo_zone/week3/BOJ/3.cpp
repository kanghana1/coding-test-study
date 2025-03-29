// BOJ 11279
#include <bits/stdc++.h>
using namespace std;

priority_queue<int> pq;
long long x;
int main() {
  ios::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> x;
  for (int i = 0; i < x; i++) {
    int num;
    cin >> num;
    if (num == 0) {
      if (pq.empty()) {
        cout << 0 << "\n";
      } else {
        cout << pq.top() << "\n";
        pq.pop();
      }

    } else {
      pq.push(num);
    }
  }
}