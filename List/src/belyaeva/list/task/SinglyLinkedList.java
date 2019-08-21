package belyaeva.list.task;

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

    private ListItem<T> getIndexItem(int index) {
        int indexCount = 0;
        ListItem<T> p = head;

        while (indexCount != index) {
            p = p.getNext();
            indexCount++;
        }

        return p;
    }

    public T getDataByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        return getIndexItem(index).getData();
    }

    public T changeDataByIndex(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        ListItem<T> p = getIndexItem(index);

        T changedData = p.getData();
        p.setData(data);

        return changedData;
    }

    public T removeByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        ListItem<T> p = getIndexItem(index - 1);
        T removedData = p.getNext().getData();

        p.setNext(p.getNext().getNext());

        count--;

        return removedData;
    }

    public void insertFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void insertByIndex(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and <= " + count);
        }

        ListItem<T> q = new ListItem<>(data);

        if (head == null) {
            insertFirst(q.getData());

            return;
        }

        ListItem<T> p = getIndexItem(index - 1);

        q.setNext(p.getNext());
        p.setNext(q);

        count++;
    }

    public boolean removeByData(T data) {
        if (head.getData() == data) {
            head = head.getNext();
            count--;

            return true;
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                p = prev;
                p.setNext(p.getNext().getNext());

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
        if (head == null) {
            throw new NullPointerException("List is empty");
        }

        SinglyLinkedList<T> list = new SinglyLinkedList<>();

        list.head = head;
        list.count++;

        if (count == 1) {
            return list;
        }

        ListItem<T> q = list.head;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            q.setNext(p);
            q = q.getNext();
            list.count++;
        }

        return list;
    }
}

