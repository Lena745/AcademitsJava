package belyaeva.list.main;

import belyaeva.list.task.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertFirst(5);
        list.insertFirst(1);
        list.insertFirst(3);
        list.insertFirst(8);

        System.out.println(list);

        System.out.println("getSize() = " + list.getSize());

        list.insertByIndex(2, 2);
        System.out.println(list);
        System.out.println("getSize() = " + list.getSize());

        SinglyLinkedList<Integer> copyList = list.getCopy();
        System.out.println("copy() = " + copyList);
        System.out.println("copy getSize() = " + copyList.getSize());

        list.revert();
        System.out.println("revert() = " + list);

        System.out.println("getFirstData() = " + list.getFirstData());

        System.out.println("removeByIndex() = " + list.removeByIndex(3));
        System.out.println(list);
        System.out.println("getSize() = " + list.getSize());

        System.out.println("removeFirst() = " + list.removeFirst());
        System.out.println(list);
        System.out.println("getSize() = " + list.getSize());

        System.out.println("removeByData() = " + list.removeByData(1));
        System.out.println(list);
        System.out.println("getSize() = " + list.getSize());

        list.insertByIndex(2, 9);
        System.out.println(list);
        System.out.println("getSize() = " + list.getSize());

        System.out.println("getDataByIndex() = " + list.getDataByIndex(2));

        System.out.println("changeDataByIndex() = " + list.changeDataByIndex(2, 4));
        System.out.println(list);
    }
}
