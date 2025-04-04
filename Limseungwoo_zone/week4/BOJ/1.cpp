// BOJ 2217

#include <bits/stdc++.h>
using namespace std;

int n, w;
int best = 0;
vector<int> rope;
// sort를 위한 함수
bool compare(int a, int b) { return a > b; }

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  cin >> n;
  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    rope.push_back(tmp);
  }
  // 어차피 나보다 잘 버티는 rope만 고려하면 됨
  sort(rope.begin(), rope.end(), compare);

  for (int i = 0; i < rope.size(); i++) {
        best = max(best, rope[i] * (i + 1));
  }
  cout << best << endl;
}