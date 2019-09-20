package belyaeva.tree.task;

import java.util.*;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (data.compareTo(currentNode.getData()) < 0) {
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

    public boolean isContains(T data) {
        if (root == null) {
            return false;
        }

        if (data.compareTo(root.getData()) == 0) {
            return true;
        }

        TreeNode<T> currentNode = root;

        while (data.compareTo(currentNode.getData()) != 0) {
            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            }

            if (data.compareTo(currentNode.getData()) > 0) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> nodeToRemove = root;
        TreeNode<T> parentNode = null;
        boolean isLeft = false;

        while (data.compareTo(nodeToRemove.getData()) != 0) {
            parentNode = nodeToRemove;
            if (data.compareTo(nodeToRemove.getData()) < 0) {
                if (nodeToRemove.getLeft() != null) {
                    isLeft = true;
                    nodeToRemove = nodeToRemove.getLeft();
                } else {
                    return false;
                }
            } else if (data.compareTo(nodeToRemove.getData()) > 0) {
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

    public void widthRound() {
        if (root == null) {
            throw new NullPointerException("Tree is empty");
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            System.out.print(currentNode.getData() + " ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
        System.out.println();
    }

    public TreeNode<T> getNode() {
        return root;
    }

    public void depthRoundRecursion(TreeNode<T> currentNode) {
        if (root == null) {
            throw new NullPointerException("Tree is empty");
        }

        System.out.print(currentNode.getData() + " ");

        if (currentNode.getLeft() != null) {
            depthRoundRecursion(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            depthRoundRecursion(currentNode.getRight());
        }
    }

    public void depthRound() {
        if (root == null) {
            throw new NullPointerException("Tree is empty");
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.removeLast();
            System.out.print(currentNode.getData() + " ");

            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
        System.out.println();
    }
}