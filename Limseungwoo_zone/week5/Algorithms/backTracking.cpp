/*
백트래킹의 대표적인 예제인 N-Queen 문제이다.

N * N 체스판에 N개를 서로 공격하지 않게 놓은 모든 경우의 수를 구하는 것이
목표이다.
*/
#include <bits/stdc++.h>
using namespace std;

int N;
int result = 0;
vector<int> board;

bool isSafe(int row, int col) {
  for (int i = 0; i < row; i++) {
    if (board[i] == col || abs(board[i] - col) == abs(i - row)) {
      return false;
    }
  }
  return true;
}

void backtrack(int row) {
  if (row == N) {
    result++;
    return;
  }

  for (int col = 0; col < N; col++) {
    if (isSafe(row, col)) {
      board[row] = col;
      backtrack(row + 1);
    }
  }
}

int main() {
  cin >> N;
  board.resize(N);
  backtrack(0);
  cout << result << "\n";
}