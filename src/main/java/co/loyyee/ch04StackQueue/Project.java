package co.loyyee.ch04StackQueue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
   *4.1
   * Write a method for the Queue class
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

  public static void main(String[] args) {
    var d = new Deque(10);
    d.insertRear(10);
    for (int i = 1; i < 9; i++) {
      d.insertRear(i * 10);
    }
    d.display();
    d.removeFront();
    d.display();
    d.insertFront(100);
    d.display();
    d.removeFront();
    d.display();
    d.removeRear();
    d.display();
  }

  // same as normal queue
  // insert rear
  public void insertRear(long val) {
    if (rear == max - 1) {
      rear = -1;
    }
    deque[++rear] = val;
    if (front == max) {
      front = 0;
    }
    n++;
  }

  // remove from front
  public long removeFront() {
    if (front == max) {
      front = 0;
    }
    int idx = front++;
    long val = deque[idx];
    deque[idx] = 0;
    n--;
    return val;
  }

  // special of deque
  public void insertFront(long val) {
    if (isFull()) throw new IllegalArgumentException("Deque is full");
    if (front == 0) {
      front = max;
    }
    deque[--front] = val;
    if (rear == -1) {
      rear = max - 1;
    }
    n++;
  }

  public long removeRear() {
    if (isEmpty()) {
      System.out.println("Underflow Rear");
      return -1;
    }
    int idx = rear--;
    long val = deque[idx];
    deque[idx] = 0;
    if (rear == -1) {
      rear = max - 1;
    }
    n--;

    return val;
  }

  public long peekFront() {
    return deque[front];
  }

  public long peekRear() {
    return deque[rear];
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

/** 4.3 Implement Stack with Deque. */
class Stack {
  private int max;
  private int top; // we need to offset by 1
  private Deque stack;

  public Stack(int max) {
    this.max = max;
    stack = new Deque(max);
    top = -1;
  }

  public static void main(String[] args) {
    var stack = new Stack(10);
    stack.push(10);
    stack.print();
    stack.push(20);
    stack.print();
    stack.pop();
    stack.print();
  }

  public void push(long val) {
    stack.insertFront(val);
  }

  public long pop() {
    return stack.removeFront();
  }

  public long peek() {
    return stack.peekFront();
  }

  public boolean isFull() {
    return top == max - 1;
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public void print() {
    stack.display();
  }
}

/** 4.4 insert from O(N) -> O(1), remove from O(1) -> O(N) */
class PriorityQ {
  private int max;
  private long[] queue;
  private int n;

  // there are no front and rear index is because we will order it ourselves
  public PriorityQ(int max) {
    this.max = max;
    queue = new long[max];
    n = 0;
  }

  public static void main(String[] args) {
    PriorityQ pq = new PriorityQ(10);
    IntStream.range(0, 10)
        .forEach(
            (ignored) -> {
              pq.insert(ThreadLocalRandom.current().nextLong(0, 100));
              pq.print();
            });
    while (!pq.isEmpty()) {
      pq.remove();
      pq.print();
    }
  }

  // insert position based on the value
  public void insert(long val) {
    queue[n++] = val;
  }

  public long remove() {
    long hi = Integer.MIN_VALUE;
    int idx = 0;
    for (int i = 0; i < queue.length; i++) {
      if (queue[i] > hi) {
        hi = queue[i];
        idx = i;
      }
    }
    queue[idx] = 0; // remove it .
    n--;
    return hi;
  }

  public long peek() {
    return queue[n - 1];
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean isFull() {
    return n == max;
  }

  private void print() {
    Arrays.stream(queue).forEach(el -> System.out.print(el + ", "));
    System.out.println();
  }
}

/** Queuing up. */
class SupermarketQueue {
  private Queue line1;
  private Queue line2;
  private Queue line3;

  public SupermarketQueue() {
    line1 = new Queue(5);
    line2 = new Queue(5);
    line3 = new Queue(5);
  }

  public static void main(String[] args) {
    var queue = new SupermarketQueue();
    queue.run();
  }

  public void run() {

    boolean cont = true;
    try (Scanner sc = new Scanner(System.in)) {
      while (cont) {
        System.out.println("Pick a line to checkout: 1, 2 or 3");
        int pick = Integer.valueOf(sc.nextLine());
        int ticket = 0;
        switch (pick) {
          case 1:
            if (!line1.isFull()) {
              ticket = pick + ThreadLocalRandom.current().nextInt(1, pick * 10);
              line1.insert(ticket);
            }
            line1.display();
            break;
          case 2:
            if (!line2.isFull()) {
              ticket = pick + ThreadLocalRandom.current().nextInt(1, pick * 20);
              line2.insert(ticket);
            }
            line2.display();
            break;
          case 3:
            if (!line3.isFull()) {
              ticket = pick + ThreadLocalRandom.current().nextInt(1, pick * 30);
              line3.insert(ticket);
            }
            line3.display();
            break;
          default:
            break;
        }
        System.out.println("more?");
        String toCont = sc.nextLine();
        if (!toCont.contains("y")) {
          System.out.println("Bye!");
          cont = false;
          break;
        }
      }
      boolean contCheckout = true;
      try (Scanner sc2 = new Scanner(System.in)) {
        while (contCheckout) {
          System.out.println("Process customer, pick a line 1, 2 or 3 | 9 to show all lines ");
          int pick = Integer.valueOf(sc2.nextLine());

          switch (pick) {
            case 1:
              line1.display();
              if (!line1.isEmpty()) {
                line1.remove();
              }
              line1.display();
              System.out.println(LocalDateTime.now());
              break;
            case 2:
              line2.display();
              if (!line2.isEmpty()) {
                line2.remove();
              }
              System.out.println(LocalDateTime.now());
              line2.display();
              break;
            case 3:
              line3.display();
              if (!line3.isEmpty()) {
                line3.remove();
              }
              System.out.println(LocalDateTime.now());
              line3.display();
              break;
            default:
              displayAll();
              break;
          }
        }
      }
    }
  }

  public void displayAll() {
    List<Queue> queues = List.of(line1, line2, line3);
    for (Queue q : queues) {
      q.display();
    }
  }
}
