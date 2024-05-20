package week5;

import java.util.Arrays;

/**堆的建立、插入、删除（有哨兵模式）
 * 这个实现中的堆是大根堆，即堆顶元素是堆中的最大值。如果需要实现小根堆，只需要将插入和删除元素时的比较符号反转即可。
 * @author tyh
 * @version 1.0
 */
public class Heap {
    private int[] heap;
    private int size;
    private int capacity;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity+1];// 建立一个大小为capacity+1的数组，0位置不使用，作为哨兵
        this.heap[0] = Integer.MAX_VALUE;// 哨兵的值设置为最大值，保证不会与其他元素进行比较
    }

    public static void main(String[] args) {
        Heap heap = new Heap(30);
//        int[] elements = {91,87,83,19,72,38,43,55,30,66,49,9};
        int[] elements = {79,66,43,83,30,87,38,55,91,72,49,9};
        heap.buildHeap2(elements);
        System.out.println(heap.delete());
        System.out.println(heap.delete());
        System.out.println(heap.delete());
        System.out.println(heap.delete());
        System.out.println(heap.delete());
        System.out.println(heap.delete());
    }
    private int parent(int i) {
        return i / 2; // 计算父节点的位置
    }

    private int leftChild(int i) {
        return 2 * i; // 计算左子节点的位置
    }

    private int rightChild(int i) {
        return 2 * i + 1; // 计算右子节点的位置
    }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp; // 交换两个元素的位置
    }
    /*insert方法：
    在插入元素时，我们首先检查堆是否已满，如果已满则退出。然后将元素插入到堆的末尾，接着将该元素与其父节点比较，
    如果该元素的值大于其父节点的值，则将该元素与其父节点交换位置，直到该元素的值小于等于其父节点的值，或者到达堆的根节点。*/
    public void insert(int element) {
        if(size>=capacity){
            return;// 如果堆已满，则退出
        }
        heap[++size] = element;// 将元素插入到堆的末尾
        int current = size;
        while(heap[current]>heap[parent(current)]){
            swap(current, parent(current));//交换节点值
            current = parent(current);//更新current位置，继续向上比较
        }
    }
/*    delete方法：
 在删除元素时，我们首先检查堆是否为空，如果为空则返回最小值。然后将堆底元素移到堆顶，并将堆的大小减1。
 接着将堆顶元素与其子节点比较，选择较大的子节点进行交换，直到该元素的值大于其子节点的值，或者到达堆的底部。*/
    public int delete() {
        if(size<=0){
            return Integer.MIN_VALUE; // 如果堆为空，则返回最小值
        }
        int deleted = heap[1]; // 获取堆顶元素
        heap[1] = heap[size--];// 将堆底元素移到堆顶，并将堆的大小减1
        int current = 1;
        heapify(1);
//以下做法等价heapify(1)，二选一
//        while(current<=size && heap[current]<heap[leftChild(current)] || heap[current] < heap[rightChild(current)]){
//            if(heap[leftChild(current)]>heap[rightChild(current)]){
//                swap(current, leftChild(current));// 向下调整堆的结构
//                current = leftChild(current);
//            }else{
//                swap(current, rightChild(current));
//                current = rightChild(current);
//            }
//        }
        return deleted;
    }
/*    建立堆的方法一：通过插入操作，将N个元素一个个相继插入到初始为空的堆中去，时间复杂度O(NlogN)
    建立堆时，我们首先检查传入的数组是否为空或长度为0，如果是则退出。
    然后将数组中的元素依次插入到堆中，这个过程中会自动进行堆的调整，使得堆满足堆的性质。*/
    public void buildHeap(int[] elements) {
        if(elements==null || elements.length==0){
            return;// 如果传入的数组为空或长度为0，则退出
        }
        for(int item : elements){
            insert(item);// 将数组中的元素依次插入到堆中
        }
    }
/*    建立堆的方法二：在线性时间复杂度下建立堆，也称为“堆化”（heapify）
    1.将N个元素按输入顺序存入，先满足完全二叉树的结构特性
    2，调整各节点位置，以满足最大堆的有序特性。调整：从倒数第一个有儿子的节点开始（左右最多只有一个儿子），调整成一个堆，再往前一个节点，调整成堆*/
    public void buildHeap2(int[] elements) {
        if(elements==null || elements.length==0){
            return;// 如果传入的数组为空或长度为0，则退出
        }
        this.size = elements.length;
        System.arraycopy(elements, 0, heap, 1, size); // 将传入的数组复制到堆的数组中,重点就是heap从位置1开始存放
//        this.heap = elements;
        for (int i = size / 2; i >= 0; i--) {
            heapify(i); // 从最后一个非叶子节点开始进行堆化
        }
    }
    private void heapify(int i) {
        int largest = i;
        int left = 2 * i ;
        int right = 2 * i + 1;
        if (left < size && heap[left] > heap[largest]) {
            largest = left; // 找到左子节点和父节点中的最大值
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right; // 找到右子节点和父节点中的最大值
        }
        if (largest != i) {
            swap(i, largest); // 如果父节点不是最大值，则将父节点和最大值交换位置，继续进行堆化
            heapify(largest);
        }
    }
}
