package ua.com.alevel.level2.entity;

public class TreeNode {
    TreeNode left, right;
    private final int val;

    TreeNode(int val) {
        this.val = val;
    }

    public int depth() {
        int l = this.left == null ? 0 : this.left.depth();
        int r = this.right == null ? 0 : this.right.depth();
        return Math.max(r, l) + 1;
    }

    public int getValue() {
        return this.val;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getRight() {
        return this.right;
    }
}
