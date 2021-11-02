package ua.com.alevel.level2.entity;

import ua.com.alevel.level2.TreeHelper;

public class BinaryTree {
    TreeNode root;

    public BinaryTree(int[] values) {
        for (int value : values) {
            add(value);
        }
    }

    public BinaryTree() {
    }

    public static BinaryTree create() {
        return new BinaryTree().add(6).add(4).add(8).add(3).add(5).add(7).add(9);
    }

    public BinaryTree add(int value) {
        root = addRecursive(root, value);
        return this;
    }

    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.getValue()) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.getValue()) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    public int depth() {
        if (root == null) {
            return 0;
        }
        return root.depth();
    }

    public String toString() {
        return TreeHelper.traversePreOrder(root);
    }
}
