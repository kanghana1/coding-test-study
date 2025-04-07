import java.util.ArrayList;
import java.util.List;

// 힙 != 이진트리
// 힙 같은 경우에는 항상 부모가 자식보다 높은 우선순위를 가진다는 특징이 있다.

class MaxHeap {
    private List<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // push
    public void push(int value){
        heap.add(value);
        upHeap(heap.size() - 1);
    }

    public int pop(){
        if (heap.isEmpty()){
            System.out.println("Heap is empty");
            return -1;
        }

        int root =  heap.get(0);
        int last = heap.remove(heap.size() - 1);

        // 가장 끝에 있는 요소를 루트로 올린 후에
        // Heapify. 여기서는 downHeap을 시킨다.

        if (!heap.isEmpty()){
            heap.set(0, last);
            downHeap(0);
        }

        return root;
    }

    public int peek(){
        if (heap.isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }
        return heap.get(0);
    }

    public int size(){
        return heap.size();
    }

    private void upHeap(int index){
        while (index > 0){
            int parent = (index - 1) / 2;
            if(heap.get(parent) < heap.get(index)){
                swap(index, parent);
                index = parent;
            }
            else
                break;
        }
    }

    private void downHeap(int index){
        int size = heap.size();
        while (true){
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int largest = index;

            if (left < size && heap.get(left) > heap.get(largest)){
                largest = left;
            }
            if (right < size && heap.get(right) > heap.get(largest)){
                largest = right;
            }

            if (largest != index){
                swap(index, largest);
                index = largest;
            }
            else
                break;
        }
    }
    private void swap(int i, int j){
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void printHeap(){
        System.out.print(heap);
    }

}

public  class Main {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        
        // push
        heap.push(20);
        heap.push(5);
        heap.push(15);
        heap.push(30);
        heap.printHeap();
        
        System.out.println("peek : " + heap.peek());
        
        System.out.println("Pop : " + heap.pop());
        heap.printHeap();
        
        System.out.println("size : " + heap.size());
    }
}
