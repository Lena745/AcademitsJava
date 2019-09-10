package belyaeva.arraylist.main;

import belyaeva.arraylist.task.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new MyArrayList<>();

        list.add(0, 3);
        list.add(1, 2);
        list.add(2, 8);
        list.add(3, 5);
        list.add(4, 1);

        System.out.println(list);
        System.out.println("list.size = " + list.size());

        List<Integer> list2 = new ArrayList<>(Arrays.asList(5, 8, 6));

        System.out.println("list.lastIndexOf = " + list.lastIndexOf(8));

        System.out.println("list.get = " + list.get(4));

        System.out.println("list.set = " + list.set(4, 9));
        System.out.println(list);

        System.out.println("list.remove(index) = " + list.remove(3));
        System.out.println(list);
        System.out.println("list.size = " + list.size());

        System.out.println("list.remove(item) = " + list.remove((Integer) 12));
        System.out.println(list);
        System.out.println("list.size = " + list.size());

        System.out.println("list.addAll = " + list.addAll(list2));
        System.out.println("list.addAll = " + list);
        System.out.println("list.size = " + list.size());

        System.out.println("list.removeAll = " + list.removeAll(list2));
        System.out.println("list.removeAll = " + list);
        System.out.println("list.size = " + list.size());

        list.add(1);
        System.out.println("list.add(item) = " + list);
        System.out.println("list.size = " + list.size());

        list.add(7);
        System.out.println("list.add(item) = " + list);
        System.out.println("list.size = " + list.size());

        List<Integer> list5 = new ArrayList<>(Arrays.asList(9, 12));
        System.out.println("list.containsAll = " + list.containsAll(list5));

        List<Integer> list3 = new ArrayList<>(Arrays.asList(3, 1, 7));

        System.out.println("list.retainAll = " + list.retainAll(list3));
        System.out.println("list.retainAll = " + list);
        System.out.println("list.size = " + list.size());

        list.clear();
        System.out.println(list);

        MyArrayList<Integer> list4 = new MyArrayList<>(15);
        System.out.println(list4);
        list4.add(3);
        list4.add(8);
        list4.add(1);
        System.out.println(list4);

        System.out.println("list.contains = " + list4.contains(1));

        System.out.println("list.isEmpty = " + list4.isEmpty());

        Integer[] array1 = list3.toArray(new Integer[list4.size()]);
        System.out.println("list.toArray(T[] array) = " + Arrays.toString(array1));

        System.out.println("list.toArray() = " + Arrays.toString(list2.toArray()));

        System.out.println("list.indexOf = " + list2.indexOf(6));

        list4.trimToSize();
        list4.ensureCapacity(10);
    }
}
