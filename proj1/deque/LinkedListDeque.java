package deque;

public class LinkedListDeque<T> {
    private class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new IntNode(null, null, null);
        IntNode i = new IntNode(sentinel, x, sentinel);
        sentinel.prev = i;
        sentinel.next = i;
        size += 1;
    }

    public void addFirst(T item) {
        IntNode i = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.prev = i;
        sentinel.next = i;
        size += 1;
    }

    public void addLast(T item) {
        IntNode i = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = i;
        sentinel.prev = i;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (IntNode i = sentinel.next; i != sentinel; i = i.next) {
            System.out.print(i.item + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T i = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return i;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T i = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return i;
    }

    public T get(int index) {
        int x = 0;
        for (IntNode i = sentinel.next; i != sentinel; i = i.next) {
            if (x == index) {
                return i.item;
            }
            x += 1;
        }
        return null;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, IntNode I) {
        if (I == sentinel) {
            return null;
        } else if (index == 0) {
            return I.item;
        }
        return getRecursiveHelper(index - 1, I.next);
    }
}