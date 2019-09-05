package belyaeva.arraylist.task;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(items[i]);
        }
        s.append("]");

        return s.toString();
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        int currentModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("List was changed");
            }
            if (currentIndex >= size) {
                throw new NoSuchElementException("End of the list");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }

    private void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, (items.length * 3) / 2 + 1);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
            modCount++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        System.arraycopy(items, 0, array, 0, size);

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < items.length) {
            //noinspection unchecked
            return Arrays.copyOf((T[]) items, size);
        } else {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(items, 0, array, 0, size);

            if (array.length > size) {
                array[size] = null;
            }
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        ensureCapacity(size + 1);

        items[size] = item;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                remove(i);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                count++;
            }
        }

        return count == collection.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        ensureCapacity(size + collection.size());

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collection.toArray(), 0, items, size, collection.size());

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be > 0 and < " + size);
        }

        ensureCapacity(size + collection.size());

        Object[] collectionArray = collection.toArray();

        System.arraycopy(items, index, items, index + collectionArray.length, collectionArray.length);
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collectionArray, 0, items, index, collectionArray.length);

        size += collection.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int currentModCount = modCount;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int currentModCount = modCount;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        items = (E[]) new Object[10];
        size = 0;
        modCount++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be > 0 and < " + size);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        ensureCapacity(size);

        return items[index] = item;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be > 0 and <= " + size);
        }

        ensureCapacity(size + 1);

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E item = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size - 1] = null;

        size--;
        modCount++;

        return item;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i > 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public List<E> subList(int i, int i1) {
        //noinspection ConstantConditions
        return null;
    }
}