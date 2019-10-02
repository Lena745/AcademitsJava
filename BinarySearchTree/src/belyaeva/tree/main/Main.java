package belyaeva.tree.main;

import belyaeva.tree.task.Tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        Consumer<Integer> print = node -> System.out.print(node + " ");

        System.out.println("widthRound:");
        tree.widthRound(print);
        System.out.println();

        System.out.println("depthRound:");
        tree.depthRound(print);
        System.out.println();

        System.out.println("depthRoundRecursion:");
        tree.depthRoundRecursion(tree.getRoot(), print);
        System.out.println();

        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(15);
        tree.insert(30);
        tree.insert(25);
        tree.insert(40);
        tree.insert(1);
        tree.insert(8);
        tree.insert(6);
        tree.insert(9);
        tree.insert(7);

        System.out.println("getSize() = " + tree.getSize());

        System.out.println("widthRound:");
        tree.widthRound(print);
        System.out.println();


        System.out.println("depthRound:");
        tree.depthRound(print);
        System.out.println();

        System.out.println("contains = " + tree.contains(null));

        System.out.println("depthRoundRecursion:");
        tree.depthRoundRecursion(tree.getRoot(), print);
        System.out.println();

        System.out.println("remove = " + tree.remove(5));
        tree.widthRound(print);
        System.out.println();

        Comparator<Integer> comparator = Integer::compareTo;
        Tree<Integer> comparatorTree = new Tree<>(comparator);

        System.out.println("widthRound:");
        comparatorTree.widthRound(print);
        System.out.println();

        System.out.println("depthRound:");
        comparatorTree.depthRound(print);
        System.out.println();

        System.out.println("depthRoundRecursion:");
        comparatorTree.depthRoundRecursion(comparatorTree.getRoot(), print);
        System.out.println();

        comparatorTree.insert(10);
        comparatorTree.insert(12);
        comparatorTree.insert(9);

        System.out.println("depthRound:");
        comparatorTree.depthRound(print);
        System.out.println();

        System.out.println("remove = " + comparatorTree.remove(10));
        comparatorTree.widthRound(print);
        System.out.println();
    }
}
