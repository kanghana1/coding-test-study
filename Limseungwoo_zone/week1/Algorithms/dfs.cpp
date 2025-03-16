#include <bits/stdc++.h>
using namespace std;

vector<int> graph[1001];
int visited[1001];

/*
재귀기법 사용
함수 호출 스택을 사용하기 때문에 작은 번호부터 방문
*/
void dfs_recursive(int node) {
  visited[node] = 1;
  cout << node << " ";

  for (int next : graph[node]) {
    if (!visited[next]) {
      dfs_recursive(next);
    }
  }
}

/*
반복기법 사용
스택에 데이터가 들어가는 순서에 따라 결과값이 달라짐
*/
void dfs_iterative(int root) {
  stack<int> stk;
  stk.push(root);
  visited[root] = 1;

  while (!stk.empty()) {
    int node = stk.top();
    stk.pop();
    cout << node << " ";

    for (int next : graph[node]) {
      if (!visited[next]) {
        visited[next] = 1;
        stk.push(next);
      }
    }
  }
}

int main() {
  graph[0].push_back(1);
  graph[1].push_back(0);
  graph[0].push_back(2);
  graph[2].push_back(0);
  graph[1].push_back(4);
  graph[4].push_back(1);
  graph[2].push_back(3);
  graph[3].push_back(2);

  // dfs_recursive(0);

  dfs_iterative(0);
}