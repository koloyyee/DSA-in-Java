package co.loyyee.ch04StackQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Project {

  public static void main(String[] args) {
    queue();
  }

  public static void queue() {
    Queue q = new Queue(10);
    IntStream.range(0, 10)
        .forEach(
            (ignored) -> {
              q.insert(ThreadLocalRandom.current().nextLong(0, 1000));
              q.display();
            });
    System.out.println("=======================");
    while (!q.isEmpty()) {
      q.remove();
      q.display();
    }
  }
}

class Queue {
  private int max;
  private long[] queue;
  private int front; // front index
  private int rear; // rear index
  private int n;

  public Queue(int max) {
    this.max = max;
    queue = new long[max];
    front = 0;
    rear = -1;
    n = 0;
  }

  // always insert from the rear
  public void insert(long value) {

    if (isFull()) throw new NoSuchElementException("Queue is full");
    if (rear == max - 1) { // wrapped around
      rear = -1;
    }
    queue[++rear] = value;
    n++;
  }

  // always take from the front
  public long remove() {
    if (isEmpty()) throw new NoSuchElementException("Queue is empty");
    int idx = front++;
    long first = queue[idx];
    if (front == max) {
      front = 0;
    }
    n--;
    queue[idx] = 0; // clean up and change back to 0;
    return first;
  }

  public long peekFront() {
    if (isEmpty()) throw new NoSuchElementException("Queue is empty");
    return queue[front];
  }

  public long peekRear() {
    if (isEmpty()) throw new NoSuchElementException("Queue is empty");
    return queue[rear];
  }

  public boolean isFull() {
    return n == max;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  // No n version.
  public void insertnn(long value) {
    if (rear == max - 1) {
      rear = -1;
    }
    queue[++rear] = value;
  }

  public long removenn() {
    int idx = front++;
    long result = queue[idx];
    if (front == max) {
      front = 0;
    }
    queue[idx] = 0;
    return result;
  }

  public boolean isEmptynn() {
    // the queue's front and rear met
    return rear + 1 == front || front + max - 1 == rear;
  }

  public boolean isFullnn() {
    return rear + 2 == front || front + max - 2 == rear;
  }

  public int size() {
    if (rear >= front) {
      return rear - front + 1;
    } else {
      return (max - front) + (rear + 1);
    }
  }

  public void print() {
    Arrays.stream(queue).forEach(el -> System.out.printf("%s, ", el));
    System.out.println();
  }

  /***
   *4.1 Write a method for the Queue class
   * in the queue.java program (Listing 4.4)
   *  that displays the contents of the queue.
   *
   *  Note that this does not mean simply displaying the contents
   *  of the underlying array.
   *  You should show the queue contents from
   *  the first item inserted to the last, without indicating to the viewer
   *  whether the sequence is broken by wrapping around the end of the array.
   *  Be careful that one item and no items display properly,
   *  no matter where front and rear are
   */
  public void display() {
    IntStream.range(0, queue.length)
        .forEach(i -> System.out.print(queue[queue.length - i - 1] + ", "));
    System.out.println();
  }
}

/**
 * 4.2 Create a Deque class based on the discussion of deques (double-ended queues) in this chapter.
 *
 * <p>It should include insertLeft(), insertRight(), removeLeft(), removeRight(), isEmpty(), and
 * isFull() methods. It will need to support wraparound at the end of the array, as queues do
 */
class Deque {
  private long[] deque;
  private int front;
  private int rear;
  private int max;
  private int n;

  public Deque(int max) {
    this.max = max;
    deque = new long[max];
    n = -1;
    front = 0;
    rear = -1;
  }

  public static void main(String[] args){
    var d = new Deque(10);
    d.insertRight(10);
    for(int i = 1; i < 9 ; i++) {
      d.insertRight(i * 10);
    }
    d.display();
    d.removeLeft();
    d.display();
    d.insertLeft(100);
    d.display();
    d.removeLeft();
    d.display();
    d.removeRight();
    d.display();
  }
 // same as normal queue
  // insert rear
 public void insertRight(long val) {
    if(rear == max - 1) {
      rear = -1;
    }
    deque[++rear] = val;
    n++;
 }
 // remove from front
  public long removeLeft() {
    if(front == max -1 ) {
      front = 0;
    }
    int idx = front++;
    long val = deque[idx];
    deque[idx] = 0;
    n--;
    return val;
  }
  
  // special of deque
  public void insertLeft(long val) {
    if( isFull()) throw new IllegalArgumentException("Deque is full");
    if(isEmpty()) {
      front = rear = 0;
      deque[front] = val;
    } else {
      front--;
      deque [front] = val;
    }
  }
  
  public long removeRight() {
    if(isEmpty()) {
      System.out.println("Underflow Rear");
      return -1;
    } else {
      
      long val = deque[rear];
      deque[rear] = 0;
      rear--;
      return val;
    }
  }

  public boolean isFull() {
    return n == max;
  }

  public boolean isEmpty() {
    return n == -1;
  }
  
  public void display() {
//    IntStream.range(0, deque.length)
//        .forEach(i -> System.out.print(deque[deque.length - i - 1] + ", "));
    Arrays.stream(deque).forEach(el -> System.out.printf("%s, ", el));
    System.out.println();
  }
}
