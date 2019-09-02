package belyaeva.list.task;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private int count;
    private ListItem<T> head;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p != head) {
                s.append(", ");
            }
            s.append(p.getData());
        }
        s.append("}");

        return s.toString();
    }

    public int getSize() {
        return count;
    }

    public T getFirstData() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }

        return head.getData();
    }

    private ListItem<T> getItem(int index) {
        int i = 0;
        ListItem<T> p = head;

        while (i != index) {
            p = p.getNext();
            i++;
        }

        return p;
    }

    private boolean notIsInBounds(int index, int count) {
        return index < 0 || index >= count;
    }

    public T getDataByIndex(int index) {
        if (notIsInBounds(index, count)) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        return getItem(index).getData();
    }

    public T changeDataByIndex(int index, T data) {
        if (notIsInBounds(index, count)) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        ListItem<T> itemToChange = getItem(index);

        T changedData = itemToChange.getData();
        itemToChange.setData(data);

        return changedData;
    }

    public T removeByIndex(int index) {
        if (notIsInBounds(index, count)) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> prevItem = getItem(index - 1);
        T removedData = prevItem.getNext().getData();

        prevItem.setNext(prevItem.getNext().getNext());

        count--;

        return removedData;
    }

    public void insertFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void insertByIndex(int index, T data) {
        if (notIsInBounds(index, count + 1)) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and <= " + count);
        }

        if (index == 0) {
            insertFirst(data);

            return;
        }

        ListItem<T> prevItem = getItem(index - 1);
        ListItem<T> newItem = new ListItem<>(data);

        newItem.setNext(prevItem.getNext());
        prevItem.setNext(newItem);

        count++;
    }

    public boolean removeByData(T data) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
                if (prev != null) {
                    p = prev;
                    p.setNext(p.getNext().getNext());
                } else {
                    head = head.getNext();
                }

                count--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }

        T firstData = head.getData();
        head = head.getNext();

        count--;

        return firstData;
    }

    public void revert() {
        if (count <= 1) {
            return;
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            prev.setNext(p.getNext());
            p.setNext(head);
            head = p;
            p = prev;
        }
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();

        if (count == 0) {
            return list;
        }

        list.count = count;
        list.head = new ListItem<>(head.getData());

        if (list.count == 1) {
            return list;
        }

        ListItem<T> q = list.head;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            q.setNext(new ListItem<>(p.getData()));
            q = q.getNext();
        }

        return list;
    }
}