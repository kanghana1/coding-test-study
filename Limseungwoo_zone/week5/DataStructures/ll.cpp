#include <bits/stdc++.h>
using namespace std;

struct Node {
  int data;
  Node* next;
  Node(int val) : data(val), next(nullptr) {}
};

class LinkedList {
 private:
  Node* frontNode;
  Node* rearNode;

 public:
  LinkedList() { frontNode = rearNode = nullptr; }
  ~LinkedList() {
    while (!empty()) pop();
  }

  void push(int val) {
    Node* newNode = new Node(val);

    if (empty()) {
      frontNode = rearNode = newNode;
    } else {
      rearNode->next = newNode;
      rearNode = newNode;
    }
  }

  void pop() {
    if (empty()) {
      cout << "List is Empty\n";
      return;
    }
    Node* tmp = frontNode;
    frontNode = frontNode->next;
    delete tmp;
    if (frontNode == nullptr) rearNode = nullptr;
  }

  int front() {
    if (empty()) {
      cout << "List is Empty\n";
      return -1;
    }
    return frontNode->data;
  }

  bool empty() { return frontNode == nullptr; }
};

int main() {
  LinkedList ll;
  ll.push(10);
  ll.push(20);
  ll.push(40);
  ll.push(30);

  while (!ll.empty()) {
    cout << ll.front() << " ";
    ll.pop();
  }

  return 0;
}