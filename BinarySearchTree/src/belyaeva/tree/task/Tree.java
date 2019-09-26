package belyaeva.tree.task;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
        comparator = null;
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }
        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    size++;

                    return;

                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    size++;

                    return;
                }
            }
        }
    }

    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = root;
        int compareResult;

        do {
            compareResult = compare(data, currentNode.getData());

            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            }

            if (compareResult > 0) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        } while (compareResult != 0);

        return true;
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> nodeToRemove = root;
        TreeNode<T> parentNode = null;
        boolean isLeft = false;

        while (true) {
            int compareResult = compare(data, nodeToRemove.getData());

            if (compareResult == 0) {
                break;
            }

            parentNode = nodeToRemove;

            if (compareResult < 0) {
                if (nodeToRemove.getLeft() != null) {
                    isLeft = true;
                    nodeToRemove = nodeToRemove.getLeft();
                } else {
                    return false;
                }
            } else {
                if (nodeToRemove.getRight() != null) {
                    isLeft = false;
                    nodeToRemove = nodeToRemove.getRight();
                } else {
                    return false;
                }
            }
        }

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            if (parentNode == null) {
                root = null;
            } else if (isLeft) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
            size--;

            return true;
        }

        if (nodeToRemove.getLeft() == null || nodeToRemove.getRight() == null) {
            if (parentNode == null) {
                root = null;
            } else if (isLeft) {
                if (nodeToRemove.getLeft() != null) {
                    parentNode.setLeft(nodeToRemove.getLeft());
                } else {
                    parentNode.setLeft(nodeToRemove.getRight());
                }
            } else {
                if (nodeToRemove.getLeft() != null) {
                    parentNode.setRight(nodeToRemove.getLeft());
                } else {
                    parentNode.setRight(nodeToRemove.getRight());
                }
            }
            size--;

            return true;
        }

        TreeNode<T> minNode = nodeToRemove.getRight();
        TreeNode<T> minParent = null;

        while (minNode.getLeft() != null) {
            minParent = minNode;
            minNode = minNode.getLeft();
        }

        if (parentNode == null) {
            if (minParent == null) {
                minNode.setLeft(root.getLeft());
                root = minNode;
            } else {
                minParent.setLeft(minNode.getRight());
                minNode.setLeft(root.getLeft());
                minNode.setRight(root.getRight());
                root = minNode;
            }
        } else {
            if (minParent == null) {
                minNode.setLeft(nodeToRemove.getLeft());
                if (isLeft) {
                    parentNode.setLeft(minNode);
                } else {
                    parentNode.setRight(minNode);
                }
            } else {
                minParent.setLeft(minNode.getRight());
                parentNode.setRight(minNode);
                minNode.setLeft(nodeToRemove.getLeft());
                minNode.setRight(nodeToRemove.getRight());
            }
        }

        size--;

        return true;
    }

    public int getSize() {
        return size;
    }

    public void widthRound(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void depthRoundRecursion(TreeNode<T> currentNode, Consumer<T> consumer) {
        if (root != null) {
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                depthRoundRecursion(currentNode.getLeft(), consumer);
            }

            if (currentNode.getRight() != null) {
                depthRoundRecursion(currentNode.getRight(), consumer);
            }
        }
    }

    public void depthRound(Consumer<T> consumer) {
        Deque<TreeNode<T>> stack = new LinkedList<>();

        if (root != null) {
            stack.add(root);
        }

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.removeLast();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
    }
}