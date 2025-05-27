# Singly Linked List

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class LinkedListQueue:
    def __init__(self):
        self.front = None
        self.rear = None

    def isEmpty(self):
        if self.front is None:
            return True
        else:
            return False

    def enqueue(self, data):
        new_node = Node(data)

        if self.isEmpty():
            self.front = new_node
            self.rear = new_node
        else:
            self.rear.next = new_node
            self.rear = new_node

    def dequeue(self):
        if self.isEmpty():
            return "Queue is Empty"
        else:
            dequeued = self.front
            self.front = self.front.next

        # front가 None이 되면 큐가 비었다는 뜻이므로 rear도 None
        if self.front is None:
            self.rear = None
        return dequeued

    def peek(self):
        if self.isEmpty():
            return "Queue is Empty"
        else:
            return self.front.data
