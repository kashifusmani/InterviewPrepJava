package ebay;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    public int findMedian() {
        if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        }
        return maxHeap.peek();
    }

    int[] runningMedian(int[] dataStream) {
        int [] output = new int[dataStream.length];
        MedianFinder medianFinder = new MedianFinder();
        int i = 0;
        for (int elem: dataStream) {
            medianFinder.addNum(elem);
            output[i] = medianFinder.findMedian();
            i++;
        }
        return output;
    }
}


