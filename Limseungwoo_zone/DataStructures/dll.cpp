// 양방향 링크드리스트 구현
#include <bits/stdc++.h>
using namespace std;

class Node {
 public:
  int data;
  Node* prev;
  Node* next;

  Node(int value) : data(value), prev(nullptr), next(nullptr) {}
};

class DoublyLinkedList {
 private:
  Node* first;  // 첫번쩨 노드
  Node* last;   // 마지막 노드

 public:
  DoublyLinkedList() : first(nullptr), last(nullptr) {}

  // 리스트 출력
  void print() {
    Node* current = first;
    while (current) {
      cout << current->data << " ";
      current = current->next;
    }
    cout << endl;
  }
  // 리스트 맨 뒤에 노드 추가
  void append(int value) {
    Node* newNode = new Node(value);
    if (!first) {
      first = last = newNode;
      return;
    }
    last->next = newNode;
    newNode->prev = last;
    last = newNode;
  }

  // 리스트 맨 앞에 노드 추가
  void prepend(int value) {
    Node* newNode = new Node(value);
    if (!first) {
      first = last = newNode;
      return;
    }
    newNode->next = first;
    first->prev = newNode;
    first = newNode;
  }

  void appendAt(int index, int value) {
    if (index <= 0) {
      prepend(value);
      return;
    }

    Node* current = first;
    int idx = 0;
    while (current && idx < index - 1) {
      current = current->next;
      idx++;
    }

    Node* newNode = new Node(value);

    newNode->next = current->next;
    newNode->prev = current;
    if (current->next) {
      current->next->prev = newNode;
    }
    current->next = newNode;
    if (current == last) {
      newNode = last;
    }
  }

  int find(int value) {
    Node* current = first;
    int idx = 0;
    while (current) {
      if (current->data == value) {
        return idx;
      }
      current = current->next;
      idx++;
    }
    return -1;
  }

  // 특정 값이 들어있는 노드를 찾아서 삭제
  void remove(int value) {
    Node* current = first;
    while (current) {
      if (current->data == value) {
        if (current == first) {
          first = first->next;
          // 노드가 남아있지 않을 때의 segmentation fault 방지
          if (first) first->prev = nullptr;

        } else if (current == last) {
          last = current->prev;
          // 노드가 남아있지 않을 때의 segmentation fault 방지
          if (last) last->next = nullptr;
        } else {
          // current의 이전 노드의 nextptr을 curent의 다음 노드와 연결
          current->prev->next = current->next;
          // current의 다음 노드의 prevptr을 current의 이전 노드와 연결
          current->next->prev = current->prev;
        }
        delete (current);  // 동적으로 할당된 메모리 해제(누수 방지)
        return;
      }
      current = current->next;
    }
  }

  void removeAt(int index) {
    if (index < 0) return;

    Node* current = first;
    int idx = 0;

    while (current && idx < index) {
      current = current->next;
      idx++;
    }

    if (!current) return;

    if (current == first) {
      first = first->next;
      if (first) first->prev = nullptr;
    } else if (current == last) {
      last = last->prev;
      if (last) last->next = nullptr;
    } else {
      current->next->prev = current->prev;
      current->prev->next = current->next;
    }
    delete current;
  }

  int size() {
    int cnt = 0;
    Node* current = first;
    while (current) {
      current = current->next;
      cnt++;
    }
    return cnt;
  }
};

int main() {
  DoublyLinkedList dll;
  dll.append(10);
  dll.prepend(0);
  dll.append(20);
  dll.append(30);
  dll.append(50);
  dll.print();
  dll.removeAt(4);
  dll.print();
  cout << dll.size();
}