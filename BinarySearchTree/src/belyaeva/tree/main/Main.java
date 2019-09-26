package belyaeva.tree.main;

import belyaeva.tree.task.Tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        Consumer<Integer> print = node -> System.out.print(node + " ");

        tree.insert(8);
        tree.insert(3);
        tree.insert(14);
        tree.insert(12);
        tree.insert(1);
        tree.insert(6);
        tree.insert(20);
        tree.insert(13);
        tree.insert(9);

        System.out.println("getSize() = " + tree.getSize());

        System.out.println("widthRound:");
        tree.widthRound(print);
        System.out.println();


        System.out.println("depthRound:");
        tree.depthRound(print);
        System.out.println();

        System.out.println("isContains = " + tree.contains(12));

        System.out.println("depthRoundRecursion:");
        tree.depthRoundRecursion(tree.getRoot(), print);
        System.out.println();

        System.out.println("remove = " + tree.remove(12));
        tree.widthRound(print);
        System.out.println();

        Comparator<Integer> comparator = Integer::compareTo;
        Tree<Integer> comparatorTree = new Tree<>(comparator);

        comparatorTree.insert(10);
        comparatorTree.insert(12);
        comparatorTree.insert(9);
        comparatorTree.insert(11);
        comparatorTree.insert(14);

        comparatorTree.depthRound(print);
        System.out.println();

        System.out.println("remove = " + comparatorTree.remove(12));
        comparatorTree.widthRound(print);
        System.out.println();
    }
}
