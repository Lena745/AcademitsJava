package belyaeva.hashtable.main;

import belyaeva.hashtable.task.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> ht = new MyHashTable<>(11);
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 20, 1, 90, 45, 4, 63, 18, 11, 53, 71));
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(4, 71, 1, 45, 18, 11));

        System.out.println("addAll = " + ht.addAll(list));
        System.out.println(ht);
        System.out.println("size = " + ht.size());

        System.out.println("retainAll = " + ht.retainAll(list3));
        System.out.println(ht);
        System.out.println("size = " + ht.size());

        System.out.println("add = " + ht.add(17));
        System.out.println(ht);
        System.out.println("size = " + ht.size());

        System.out.println("remove = " + ht.remove(45));
        System.out.println(ht);
        System.out.println("size = " + ht.size());

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(4, 1, 11, 71));

        System.out.println("containsAll = " + ht.containsAll(list1));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(7, 90, 63, 18, 53));

        System.out.println("removeAll = " + ht.removeAll(list2));
        System.out.println(ht);
        System.out.println("size = " + ht.size());

        System.out.println("contains = " + ht.contains(78));

        System.out.println("toArray() = " + Arrays.toString(ht.toArray()));

        Integer[] array = ht.toArray(new Integer[8]);
        System.out.println("toArray(T[] ts) = " + Arrays.toString(array));

        System.out.println("clear:");
        ht.clear();
        System.out.println(ht);

        MyHashTable<Integer> ht2 = new MyHashTable<>();

        System.out.println();
        ht2.addAll(list3);
        System.out.println(ht2);
        System.out.println("size = " + ht2.size());

        for (Integer value : ht2) {
            System.out.print(value + " ");
        }
    }
}
