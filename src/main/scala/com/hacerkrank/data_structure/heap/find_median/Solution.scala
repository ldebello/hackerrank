package com.hacerkrank.data_structure.heap.find_median

object Solution {

  class Heap(val maxSize: Int, val initialValue: Int, heapifyUpCondition: (Array[Int], Int) => Boolean, heapifyDownCondition: (Array[Int], Int) => Boolean, checkCondition: (Array[Int], Int) => Boolean) {
    var size = 0
    val heap = new Array[Int](maxSize + 1)
    heap(0) = initialValue

    def insert(element: Int) = {
      size = size + 1
      heap(size) = element
      var currentIndex = size

      while (heapifyUpCondition(heap, currentIndex)) {
        swap(currentIndex, parent(currentIndex))
        currentIndex = parent(currentIndex)
      }
    }

    def swap(from: Int, to: Int) {
      val tmp = heap(from)
      heap(from) = heap(to)
      heap(to) = tmp
    }

    def remove() = {
      val maxValue = heap(1)
      heap(1) = heap(size)
      size = size - 1
      heapify(1)
      maxValue
    }

    def heapify(index: Int) {
      if (!isLeaf(index)) {
        if (heapifyDownCondition(heap, index)) {
          if (checkCondition(heap, index)) {
            swap(index, leftChild(index))
            heapify(leftChild(index))
          } else {
            swap(index, rightChild(index))
            heapify(rightChild(index))
          }
        }
      }
    }

    def length(): Int = {
      size
    }

    def peek() = {
      heap(1)
    }

    def parent(index: Int) = {
      index / 2
    }

    def leftChild(index: Int) = {
      2 * index
    }

    def rightChild(index: Int) = {
      (2 * index) + 1
    }

    def isLeaf(index: Int) = {
      leftChild(index) > size
    }
  }

  class MedianHeap(val maxSize: Int) {
    var size = maxSize / 2 + 2
    val minHeap = new Heap(size, Integer.MIN_VALUE, minHeapifyUpCondition, minHeapifyDownCondition, minCheckCondition)
    val maxHeap = new Heap(size, Integer.MAX_VALUE, maxHeapifyUpCondition, maxHeapifyDownCondition, maxCheckCondition)

    def insert(element: Int) {
      if (isEmpty()) {
        minHeap.insert(element)
      } else {
        if (element > median()) {
          minHeap.insert(element)
        } else {
          maxHeap.insert(element)
        }
      }
      balance()
    }

    def length() = {
      minHeap.length() + maxHeap.length()
    }

    def median() = {
      if (maxHeap.length() == minHeap.length()) {
        (maxHeap.peek() + minHeap.peek()) / 2.0
      } else if (maxHeap.length() > minHeap.length()) {
        maxHeap.peek()
      } else {
        minHeap.peek()
      }
    }

    def isEmpty() = {
      length() == 0
    }

    def balance() = {
      if (Math.abs(maxHeap.length() - minHeap.length()) > 1) {
        if (maxHeap.length() > minHeap.length()) {
          minHeap.insert(maxHeap.remove())
        } else {
          maxHeap.insert(minHeap.remove())
        }
      }
    }
  }

  def main(args: Array[String]) {
    val lines = io.Source.stdin.getLines()
    val numberOfElements = lines.next().toInt

    val medianHeap = new MedianHeap(numberOfElements)
    for (index <- 0 until numberOfElements) {
      val currentNumber = lines.next().toInt

      medianHeap.insert(currentNumber)

      println("%.1f".format(medianHeap.median()))
    }
  }

  val maxHeapifyUpCondition = (heap: Array[Int], currentIndex: Int) => {
    def parent(index: Int) = {
      index / 2
    }

    heap(currentIndex) > heap(parent(currentIndex))
  }

  val maxHeapifyDownCondition = (heap: Array[Int], index: Int) => {
    def leftChild(index: Int) = {
      2 * index
    }

    def rightChild(index: Int) = {
      (2 * index) + 1
    }

    heap(index) < heap(leftChild(index)) || heap(index) < heap(rightChild(index))
  }

  val maxCheckCondition = (heap: Array[Int], index: Int) => {
    def leftChild(index: Int) = {
      2 * index
    }

    def rightChild(index: Int) = {
      (2 * index) + 1
    }

    heap(leftChild(index)) > heap(rightChild(index))
  }

  val minHeapifyUpCondition = (heap: Array[Int], currentIndex: Int) => {
    def parent(index: Int) = {
      index / 2
    }

    heap(currentIndex) < heap(parent(currentIndex))
  }

  val minHeapifyDownCondition = (heap: Array[Int], index: Int) => {
    def leftChild(index: Int) = {
      2 * index
    }

    def rightChild(index: Int) = {
      (2 * index) + 1
    }

    heap(index) > heap(leftChild(index)) || heap(index) > heap(rightChild(index))
  }

  val minCheckCondition = (heap: Array[Int], index: Int) => {
    def leftChild(index: Int) = {
      2 * index
    }

    def rightChild(index: Int) = {
      (2 * index) + 1
    }

    heap(leftChild(index)) < heap(rightChild(index))
  }
}