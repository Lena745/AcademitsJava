package belyaeva.hashtable.task;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private int size;
    private ArrayList<E>[] hashArray;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        hashArray = new ArrayList[7];
    }

    public MyHashTable(int arraySize) {
        if (arraySize < 1) {
            throw new IllegalArgumentException("Size must be >= 1");
        }
        //noinspection unchecked
        hashArray = new ArrayList[arraySize];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < hashArray.length; i++) {
            s.append(i).append(": ");
            s.append(hashArray[i]);
            if (i != hashArray.length - 1) {
                s.append(System.lineSeparator());
            }
        }

        return s.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % hashArray.length);
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        if (hashArray[index] == null) {
            return false;
        }

        return hashArray[index].contains(o);
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentItem = -1;
        private int currentIndex = 0;
        private int currentIndexItem = -1;
        private int currentModCount = modCount;

        public boolean hasNext() {
            return currentItem + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of the table");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("HashTable was changed during iteration");
            }

            while (hasNext()) {
                if (hashArray[currentIndex] == null) {
                    currentIndex++;
                } else {
                    currentIndexItem++;
                    if (currentIndexItem == hashArray[currentIndex].size()) {
                        currentIndex++;
                        currentIndexItem = -1;
                    } else {
                        currentItem++;
                        return hashArray[currentIndex].get(currentIndexItem);
                    }
                }
            }

            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (E item : this) {
            array[i] = item;
            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (hashArray[index] == null) {
            hashArray[index] = new ArrayList<>();
        }

        hashArray[index].add(item);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (hashArray[index] == null) {
            return false;
        }

        if (hashArray[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int currentSize = size;

        for (E item : collection) {
            add(item);
        }

        return currentSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int currentSize = size;

        for (ArrayList<E> indexList : hashArray) {
            if (indexList != null) {
                size -= indexList.size();
                indexList.removeAll(collection);
                size += indexList.size();
            }
        }

        if (currentSize != size) {
            modCount++;
        }

        return currentSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int currentSize = size;

        for (ArrayList<E> indexList : hashArray) {
            if (indexList != null) {
                size -= indexList.size();
                indexList.retainAll(collection);
                size += indexList.size();
            }
        }

        if (currentSize != size) {
            modCount++;
        }

        return currentSize != size;
    }

    @Override
    public void clear() {
        if (size > 0) {
            modCount++;
        }

        Arrays.fill(hashArray, null);

        size = 0;
    }
}