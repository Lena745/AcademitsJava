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
            if (i == hashArray.length - 1) {
                s.append(hashArray[i]);
            } else {
                s.append(hashArray[i]).append("\n");
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

    private int getHashCode(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % hashArray.length);
    }

    @Override
    public boolean contains(Object o) {
        if (hashArray[getHashCode(o)] == null) {
            return false;
        }

        return hashArray[getHashCode(o)].contains(o);
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

        for (ArrayList<E> indexList : hashArray) {
            if (indexList != null) {
                for (Object item : indexList) {
                    array[i] = item;
                    i++;
                }
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        if (ts.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, ts.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, ts, 0, size);

        if (ts.length > size) {
            ts[size] = null;
        }

        return ts;
    }

    @Override
    public boolean add(E item) {
        int index = getHashCode(item);

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
        int index = getHashCode(o);

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
            //noinspection SuspiciousMethodCalls
            if (!hashArray[getHashCode(item)].contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int currentSize = size;

        for (Object item : collection) {
            //noinspection unchecked
            add((E) item);
        }

        return currentSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int currentSize = size;

        for (Object item : collection) {
            int index = getHashCode(item);
            //noinspection SuspiciousMethodCalls
            if (hashArray[index].contains(item)) {
                //noinspection SuspiciousMethodCalls
                hashArray[index].remove(item);
                size--;
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
        for (int i = 0; i < hashArray.length; i++) {
            hashArray[i] = null;
        }

        size = 0;
        modCount++;
    }
}
