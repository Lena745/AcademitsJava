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

    private int getIndex(ListItem<T> q) {
        int index = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p == q) {
                break;
            }
            index++;
        }

        return index;
    }

    public T getDataByIndex(int index) {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        ListItem<T> q = new ListItem<>();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (getIndex(p) == index) {
                q.setData(p.getData());
            }
        }

        return q.getData();
    }

    public T changeDataByIndex(int index, T data) {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        ListItem<T> q = new ListItem<>(data);

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (getIndex(p) == index) {
                T changedData = p.getData();
                p.setData(q.getData());
                q.setData(changedData);
            }
        }

        return q.getData();
    }

    public T removeByIndex(int index) {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + count);
        }

        ListItem<T> q = new ListItem<>();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (getIndex(p.getNext()) == index) {
                q.setData(p.getNext().getData());
                p.setNext(p.getNext().getNext());

                count--;
            }
        }
        return q.getData();
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

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (getIndex(p.getNext()) == index) {
                ListItem<T> nextIndexItem = p.getNext();

                p.setNext(q);
                q.setNext(nextIndexItem);

                count++;

                break;
            }
        }
    }

    public boolean removeByData(T data) {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext().getData() == data) {
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

        ListItem<T> p = new ListItem<>();

        p.setData(head.getData());
        head = head.getNext();

        count--;

        return p.getData();
    }

    public void revert() {
        ListItem<T> q = new ListItem<>();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            q.setData(p.getData());
            insertFirst(q.getData());
            removeByData(p.getData());
        }
    }

    public SinglyLinkedList<T> getCopy(SinglyLinkedList<T> list) {
        int index = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            list.insertByIndex(index, p.getData());
            index++;
        }

        return list;
    }
}

