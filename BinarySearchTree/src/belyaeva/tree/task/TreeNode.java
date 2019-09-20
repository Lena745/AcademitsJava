package belyaeva.tree.task;

class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    TreeNode(T data) {
        this.data = data;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }

    TreeNode<T> getRight() {
        return right;
    }

    T getData() {
        return data;
    }
}