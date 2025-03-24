// BOJ 16953
// bfs를 사용하여 2를 곱하는 경우와 뒤에 1을 추가하는 경우 탐색
#include <bits/stdc++.h>
using namespace std;

string a, b;
queue<pair<string, long long>> q;
map<string, int> visited;
long long cnt;
int main() {
  cin >> a >> b;
  visited.insert({a, 1});
  q.push({a, 1});
  while (!q.empty()) {
    string now = q.front().first;
    long long depth = q.front().second;

    q.pop();
    string next1 = to_string(stoll(now) * 2);
    string next2 = now + "1";

    depth += 1;
    // 얻고자 하는 값보다 수가 크면 큐에 집어넣지 않게끔 조건문 작성
    // 원하는 수에 도달하면 바로 exit()으로 종료
    if (stoll(next1) < stoll(b)) {
      if (!visited[next1]) {
        q.push({next1, depth});
        visited.insert({next1, 1});
      }
    } else if (stoll(next1) == stoll(b)) {
      cout << depth;
      exit(0);
    }
    if (stoll(next2) < stoll(b)) {
      if (!visited[next2]) {
        q.push({next2, depth});
        visited.insert({next2, 1});
      }
    } else if (stoll(next2) == stoll(b)) {
      cout << depth;
      exit(0);
    }
  }
  cout << -1 << endl;
}