package belyaeva.tree.task;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (data2 == null) {
            return 1;
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
            } else if (compareResult > 0) {
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

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() != null) {
            if (parentNode == null) {
                root = nodeToRemove.getRight();
            } else if (isLeft) {
                parentNode.setLeft(nodeToRemove.getRight());
            } else {
                parentNode.setRight(nodeToRemove.getRight());
            }
            size--;

            return true;
        }

        if (nodeToRemove.getRight() == null && nodeToRemove.getLeft() != null) {
            if (parentNode == null) {
                root = nodeToRemove.getLeft();
            } else if (isLeft) {
                parentNode.setLeft(nodeToRemove.getLeft());
            } else {
                parentNode.setRight(nodeToRemove.getLeft());
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

        if (minParent != null) {
            minParent.setLeft(minNode.getRight());
            minNode.setRight(nodeToRemove.getRight());
        }
        minNode.setLeft(nodeToRemove.getLeft());

        if (parentNode == null) {
            root = minNode;
        } else {
            if (isLeft) {
                parentNode.setLeft(minNode);
            } else {
                parentNode.setRight(minNode);
            }
        }

        size--;

        return true;
    }

    public int getSize() {
        return size;
    }

    public void widthRound(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

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
        if (root == null) {
            return;
        }

        consumer.accept(currentNode.getData());

        if (currentNode.getLeft() != null) {
            depthRoundRecursion(currentNode.getLeft(), consumer);
        }

        if (currentNode.getRight() != null) {
            depthRoundRecursion(currentNode.getRight(), consumer);
        }
    }

    public void depthRound(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.add(root);

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