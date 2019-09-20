package belyaeva.tree.main;

import belyaeva.tree.task.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

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
        tree.widthRound();

        System.out.println("depthRound:");
        tree.depthRound();

        System.out.println("isContains = " + tree.isContains(12));

        System.out.println("depthRoundRecursion:");
        tree.depthRoundRecursion(tree.getNode());

        System.out.println(tree.remove(8));
        tree.widthRound();
        }
}
