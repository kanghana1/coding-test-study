// BOJ 1484

#include <bits/stdc++.h>
using namespace std;

long long g, lp = 1, rp = 1, lnum, rnum;
bool flag = false;

int main() {
  cin >> g;

  do {
    lnum = lp * lp;
    rnum = rp * rp;
    while (rnum - lnum < g) {
      rp++;
      rnum = rp * rp;
    }
    if (rnum - lnum > g)
      lp++;
    else if (rnum - lnum == g) {
      flag = true;
      cout << rp << "\n";
      rp++;
    }

  } while (lp < rp);
  if (!flag) cout << -1;
}