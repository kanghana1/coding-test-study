// BOJ 18310
#include <bits/stdc++.h>
using namespace std;

vector<int> house;
int n;

int main() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    house.push_back(tmp);
  }
  sort(house.begin(), house.end());

  int mid = n > 1 ? (house.size() - 1) / 2 : 0;
  cout << house[mid] << "\n";
}
