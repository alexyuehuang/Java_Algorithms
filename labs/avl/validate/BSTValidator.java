package avl.validate;

import avl.TreeNode;
import avl.AVLTree;
import avl.util.TreeToStrings;

public class BSTValidator<T extends Comparable<T>> {
    final public AVLTree<T> tree;
    private String before;

    public BSTValidator(final AVLTree<T> tree) {
        this.tree = tree;
        this.before = tree.toString();
    }

    /**
     * The instance variable "before" captures the state of the heap
     * last time we looked.   This method runs our validation methods,
     * and if something goes wrong, it reports the before tree, the error,
     * and the after tree.  This should provide enough information to
     * diagnose your problems with your binary heap implementation.
     */
    public void check() {
        try {
            TreeNode<T> root = tree.getRoot();
            CheckTree(root, null);
	    
            before = TreeToStrings.toTree(tree);
        } catch (Throwable t) {
            String oops = "\nTree before the problem occurred:\n";
            oops += before + "\n";
            oops += "What went wrong: " + t.getMessage() + "\n";
	    oops += "Tree that triggered this problem:" + "\n";
            oops += TreeToStrings.toTree(tree);
            t.printStackTrace();
            throw new BSTValidationError(t + "" + oops);
        }
    }
    
    /**
     * Check that the tree is a valid AVL tree.
     * In particular, make sure that
     *    - it satsfies the (strict) BST property 
     */
    public void CheckTree(TreeNode<T> child, TreeNode<T> parent) {
        if (child == null) {
            return;
        } else {
	    
            CheckTree(child.left, child);
            CheckTree(child.right, child);
	    
	    if (parent != null)
		{
		    if (child == parent.left && child.value.compareTo(parent.value) >= 0) {
			throw new BSTValidationError(String.format("The left child {%s} is >= its parent {%s} ", 
								   child.value, parent.value));
		    }
		    if (child == parent.right && child.value.compareTo(parent.value) <= 0) {
			throw new BSTValidationError(String.format("The right child {%s} is <= its parent {%s} ", 
								   child.value, parent.value));
		    }
		    if (child != parent.left && child != parent.right) {
			throw new BSTValidationError(String.format("Parent {%s} and child {%s} are not properly linked.", 
								   parent.value, child.value));
		    }
		}
	    
	    if (child.height != computeHeight(child)) {
		throw new BSTValidationError(String.format("The node {%s} does not have the correct height: expected %s, got %s",
							   child.value, computeHeight(child), child.height));
	    }
	    
	    int balance = 
	    			(child.left != null ? child.left.height : -1) - 
	    			(child.right != null ? child.right.height : -1);
	    if (balance < -1 || balance  > 1) {
		throw new BSTValidationError(String.format("The tree is not balanced starting at node %s", child.value));
	    }
	}
    }
    
    private int computeHeight(TreeNode<T> node) {
	int hl = (node.left  == null ? -1 : node.left.height);
	int hr = (node.right == null ? -1 : node.right.height);
	
        return Math.max(hl, hr) + 1;
    }
}