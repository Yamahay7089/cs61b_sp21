package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(T x) {
        items = (T[]) new Object[8];
        items[1] = x;
        size = 1;
        nextFirst = 0;
        nextLast = 2;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int i = (nextFirst + 1) % items.length;
        for (int n = 0; n < size; n += 1) {
            a[n] = items[i];
            i = (i + 1) % items.length;
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (items.length == size) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        if (items.length == size) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        }
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = nextFirst + 1;
        if (i == items.length) {
            i = 0;
        }
        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i += 1;
            if (i == items.length) {
                i = 0;
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        int i = (nextFirst + 1) % items.length;
        T x = items[i];
        items[i] = null;
        nextFirst = i;
        size -= 1;
        if (items.length >= 16 && size * 4 < items.length) {
            resize(items.length / 2);
        }
        return x;
    }

    public T removeLast() {
        int i = (nextLast - 1 + items.length) % items.length;
        T x = items[i];
        items[i] = null;
        nextLast = i;
        size -= 1;
        if (items.length >= 16 && size * 4 < items.length) {
            resize(items.length / 2);
        }
        return x;
    }

    public T get(int index) {
        return items[(nextFirst + index + 1) % items.length];
    }


}