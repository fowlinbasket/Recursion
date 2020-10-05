// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     *
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> curr;  // Last node accessed in tree
    private String treeName;     // Name of tree
    private E[] sortedArr;
    private int height;

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        this.sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        for (E item : arr) {
            bstInsert(item);
        }
    }

    public static void getLeaves(Integer[] preorder, int beg, int end, ArrayList<Integer> leaves) {
    }

    public BinaryNode<E> removeBinaryNode(BinaryNode<E> n) {
        BinaryNode<E> toRemove = n;
        // node doesn't exist
        if (n == null) return null;
        // node is a leaf node
        if (n.left == null && n.right == null) n = null;
        // node has a left child and no right child
        else if (n.left != null && n.right == null) n = n.left;
        // node has a right child and no left child
        else if (n.left == null) n = n.right;
        // node has two children
        else {
            // node changes to its in-order successor
            n = leftMost(n.right);
            // now that the in-order successor has moved, delete it
            removeBinaryNode(leftMost(n.right));
        }
        return toRemove;
    }

    /**
     * Create Tree By Level.  Parents are set.
     * This runs in  O(n)
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public void createTreeByLevel(E[] arr, String label) {
        treeName = label;
        if (arr.length <= 0) {
            root = null;
            return;
        }
        this.sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        ArrayList<BinaryNode<E>> nodes = new ArrayList<BinaryNode<E>>();
        root = new BinaryNode<E>(arr[0]);
        nodes.add(root);
        BinaryNode<E> newr = null;
        for (int i = 1; i < arr.length; i += 2) {
            BinaryNode<E> curr = nodes.remove(0);
            BinaryNode<E> newl = new BinaryNode<E>(arr[i], null, null, curr);
            nodes.add(newl);
            newr = null;
            if (i + 1 < arr.length) {
                newr = new BinaryNode<E>(arr[i + 1], null, null, curr);
                nodes.add(newr);
            }

            curr.left = newl;
            curr.right = newr;
        }
    }

    /**
     * Change name of tree
     *
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    public int getHeight() {
        return getHeight(root, 0);
    }

    private int getHeight(BinaryNode<E> n, int count) {
        if (n == null) return count - 1;
        else count += 1;
        return Math.max(getHeight(n.left, count), getHeight(n.right, count));
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    public String toString() {
        if (root == null)
            return (treeName + " Empty tree\n");
        else
            return treeName + ":\n" + toString(root, "   ");
    }


    /**
     * This runs in  O(n)
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * Find successor of "curr" node in tree
     * Big Oh complexity = O(log(n))
     * @return String representation of the successor
     */
    public String successor() {
        if (curr == null) curr = root;
        curr = successor(curr, curr.element);
        if (curr == null) return "null";
        else return curr.toString();
    }

    private BinaryNode<E> successor(BinaryNode<E> n, E element) {
        // node doesn't exist
        if (n == null) return null;
        // node has a parent
        if (n.parent != null) {
            // node is a right child
            if (n.element.compareTo(n.parent.element) > 0) {
                // node has no right child or right child has already been evaluated
                if (n.right == null || n.right.element.compareTo(element) == 0) return successor(n.parent, n.element);
            }
            // node is a left child
            else {
                // node has no right child or right child has already been evaluated
                if (n.right == null || n.right.element.compareTo(element) == 0) return n.parent;
            }
        }
        // node has unevaluated right child or node has no parent
        return leftMost(n.right);
    }

    private BinaryNode<E> leftMost(BinaryNode<E> n){
        // left child doesn't exist
        if (n.left == null) return n;
        // node has a left child
        else return leftMost(n.left);
    }

    /**
     * Big Oh complexity = O(n)
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        printAllPaths(root, new ArrayList<>());
    }

    private void printAllPaths(BinaryNode<E> n, ArrayList<BinaryNode<E>> nodes) {
        if (n != null) {
            // add node to path
            nodes.add(n);
            // add left side to path
            if (n.left != null) printAllPaths(n.left, new ArrayList<>(nodes));
            // add right side to path
            if (n.right != null) printAllPaths(n.right, new ArrayList<>(nodes));
            // recursion has reached a leaf node
            else if (n.left == null) {
                // print the path that reached this node
                System.out.print("[");
                for (BinaryNode<E> node : nodes) {
                    if (node.left == null && node.right == null) System.out.print(node.element);
                    else System.out.print(node.element + ", ");
                }
                System.out.print("]\n");
            }
        }
        // remove the leaf node
        nodes.remove(n);
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * Big Oh complexity = 2^n
     * @return Count of embedded binary search trees
     */
    public Integer countBST() {
        if (root == null) return 0;
        return countBST(root);
    }

    private Integer countBST(BinaryNode<E> n) {
        // node doesn't exist
        if (n == null) return 0;
        // node is a leaf
        if (n.left == null && n.right == null) return 1;
        // node has one child
        if (n.left == null && n.right.element.compareTo(n.element) > 0 ||
                n.right == null && n.left.element.compareTo(n.element) <= 0) return 1;
        // node has two children
        assert n.left != null;
        assert n.right != null;
        if (n.left.element.compareTo(n.element) <= 0 && n.right.element.compareTo(n.element) > 0)
            // add 1 and continue in the recursion
            return 1 + countBST(n.left) + countBST(n.right);
        // add the sum of both sides together
        return countBST(n.left) + countBST(n.right);
    }
    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void bstInsert(E x) {
        root = bstInsert(x, root, null);
    }

    public BinaryNode<E> find(E element) {
        if (!this.contains(element)) return null;
        return find(root, element);
    }

    private BinaryNode<E> find(BinaryNode<E> n, E element) {
        if (n == null) return null;
        if (n.element.compareTo(element) == 0) return n;
        find(n.left, element);
        find(n.right, element);
        return null;
    }

    /**
     * Determines if item is in tree
     *
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {
        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value
     * Big Oh = O(n)
     * @param k: minimum path sum allowed in final tree
     */
    public void pruneK(BinaryNode<Integer> root, Integer k) {
        INT sum = new INT(0);
        pruneK(root, k, new INT(0));
    }
    private static BinaryNode<Integer> pruneK(BinaryNode<Integer> n, Integer k, INT sum) {
        // node doesn't exist
        if (n == null) return null;
        // initialize left and right sums
        INT leftSum = new INT(sum.v + n.element);
        INT rightSum = new INT(leftSum.v);
        // prune left and right subtrees
        n.left = pruneK(n.left, k, leftSum);
        n.right = pruneK(n.right, k, rightSum);
        // get max of left and right sums
        sum.v = Math.max(leftSum.v, rightSum.v);
        // delete node if sum is smaller than k
        if (sum.v < k) n = null;
        return n;
    }


    private static class INT {
        int v;
        public INT(int a) {
            v = a;
        }
    }

    /**
     * Find the least common ancestor of two nodes
     * Big oh = O(n^2)
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(E a, E b) {
        // path to element a
        ArrayList<E> pathToA = new ArrayList<>();
        // path to element b
        ArrayList<E> pathToB = new ArrayList<>();
        E lca = lca(root, a, b, pathToA, pathToB);
        // return lca of a and b
        if (lca != null) return lca.toString();
        // lca is null (a and b don't exist)
        else return "";
    }

    private E lca(BinaryNode<E> n, E a, E b, ArrayList<E> path1, ArrayList<E> path2) {
        // a or b doesn't exist
        if(!findPath(n, a, path1) || !findPath(n, b, path2)) return null;
        // iterate through the two paths to find lca
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            // iteration has gone beyond where there are ancestors. i is the index of the most recent element.
            if (path1.get(i).compareTo(path2.get(i)) != 0) break;
        }
        // return the element before i, which is the least common ancestor
        return path1.get(i - 1);
    }

    private boolean findPath(BinaryNode<E> n, E element, ArrayList<E> path) {
        // node doesn't exist
        if (n == null) return false;
        // add node to path
        path.add(n.element);
        // path has been found
        if (n.element == element) return true;
        // path found on left
        if (findPath(n.left, element, path)) return true;
        // path found on right
        if (findPath(n.right, element, path)) return true;
        // path not found. Remove last element and try on new branch.
        path.remove(path.size() -1);
        return false;
    }

    /**
     * Balance the tree
     * O(log n)
     * This method erases everything in the tree and recreates it in a more balanced way
     */
    public void balanceTree() {
        root = null;
        root = balanceTree(sortedArr, 0, sortedArr.length);
    }

    private BinaryNode<E> balanceTree(E[] elements, int low, int high) {
        // base case
        if (low == high) return root;
        // calculate mid index
        int mid = (high + low) / 2;
        // insert middle value into tree
        this.bstInsert(elements[mid]);
        // get the mid of the left side
        balanceTree(elements, low, mid);
        // get the mid of the right side
        balanceTree(elements, mid + 1, high);
        return root;
    }


    /**
     * In a BST, keep only nodes between range
     * O(n)
     * @param a lowest value
     * @param b highest value
     */
    public void keepRange(E a, E b) {
        root = keepRange(root, a, b);
    }

    private BinaryNode<E> keepRange(BinaryNode<E> n, E min, E max) {
        // node doesn't exist
        if (n == null) return null;
        // remove any nodes outside of range in left and right subtrees
        n.left = keepRange(n.left, min, max);
        n.right = keepRange(n.right, min, max);
        // element is less than min
        if (n.element.compareTo(min) < 0) {
            // reattach orphaned node to tree
            return n.right;
        }
        // element is greater than max
        if (n.element.compareTo(max) > 0) {
            // reattach orphaned node to tree
            return n.left;
        }
        // node is in range
        return n;
    }

    /**
     * @return for the level with maximum sum, print the sum of the nodes
     */
    public Integer maxLevelSum() {
        int max = 0;
        for (int i = 0; i < this.getHeight(); i++) {
            int curr = maxLevelSum(i);
            if (max < curr) max = curr;
        }
        return max;
    }

    private Integer maxLevelSum(int level) {
        if (level > this.height) return null;
    }

    /**
     * @return the sum of the maximum path between any two leaves
     */
    public Integer maxPath() {
        return -999;
    }

    /**
     * @param preorder traversal of a BST,
     *                 print the leaves (without creating the tree)
     */
    public void printLeaves(E[] preorder) {
    }

    /**
     * @return true if the tree is a Sum Tree (every non-leaf node is sum of nodes in subtree)
     */
    public boolean isSum() {
        return false;

    }

    /**
     * @return largest path value for any path between two leaves
     */
    public int maxPathSum() {
        //return maxPathSum(root);
        return 0;
    }

    //PRIVATE

    /**
     * @param preorderT  preorder traversal of tree
     * @param postorderT postorder traversal of tree
     * @param name       of tree
     *                   create a full tree (every node has 0 or 2 children) from its traversals.  This is not a BST.
     */

    public void createTreeTraversals(E[] preorderT, E[] postorderT, String name) {
        this.treeName = "Need to Write ";

    }


    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>(x, null, null, parent);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean bstContains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return bstContains(x, t.left);
        else if (compareResult > 0)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }

    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     *
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }

    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(2^n)
     *
     * @param t the node that roots the subtree.
     */
    private String toString(BinaryNode<E> t, String indent) {
        // return the right tree, root, and left tree
        if (t != null) return toString(t.right, indent + "   ") + "\n" + indent + t.toString() +
                toString(t.left, indent + "   ");
        // return an empty string if node is null
        else return "";
    }

    private Integer isSum(BinaryNode<E> curr) {
        return -1;
    }

    private static class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node

        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            //sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("[no parent]");
            } else {
                sb.append("[");
                sb.append(parent.element);
                sb.append("]");
            }

            return sb.toString();
        }

    }
    // Test program
    public static void main(String[] args) {

        final String ENDLINE = "\n";


        // Assignment Problem 1
        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9, 61};
        Tree<Integer> tree1 = new Tree<Integer>(v1, "Tree1:");
        System.out.println(tree1.toString());
        System.out.println(tree1.toString2());

        // Assignment Problem 2
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        int val = 60;
        final int SIZE = 8;

        List<Integer> v2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
            int t = generator.nextInt(200);
            v2.add(t);
        }
        v2.add(val);
        Integer[] v = v2.toArray(new Integer[v2.size()]);
        Tree<Integer> tree2 = new Tree<Integer>(v, "Tree2");
        System.out.println(tree2.toString());
        tree2.contains(val);  //Sets the current node inside the tree class.
        int succCount = 5;  // how many successors do you want to see?
        System.out.println("In Tree2, starting at " + val + ENDLINE);
        for (int i = 0; i < succCount; i++) {
            System.out.println("The next successor is " + tree2.successor());
        }


        // Assignment Problem 3
        System.out.println(tree1.toString());
        System.out.println("All paths from tree1");
        tree1.printAllPaths();


        // Assignment Problem 4
        Integer[] v4 = {66, 75, -15, 3, 65, -83, 83, -10, 16, -7, 70, 200, 71, 90};
        Tree<Integer> treeA = new Tree<Integer>("TreeA");
        treeA.createTreeByLevel(v4, "TreeA");
        System.out.println(treeA.toString());
        System.out.println("treeA Contains BST: " + treeA.countBST());

        Integer[] a = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        Tree<Integer> treeB = new Tree<Integer>("TreeB");
        treeB.createTreeByLevel(a, "TreeB");
        System.out.println(treeB.toString());
        System.out.println("treeB Contains BST: " + treeB.countBST());

        // Assignment Problem 5

        treeB.pruneK(treeB.root, 60);
        treeB.changeName("treeB after pruning 60");
        System.out.println(treeB.toString());
        treeA.pruneK(treeA.root, 200);
        treeA.changeName("treeA after pruning 200");
        System.out.println(treeA.toString());

        // Assignment Problem 6

        System.out.println(tree1.toString());
        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(56, 61) + ENDLINE);

        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(6, 25) + ENDLINE);

        // Assignment Problem 7
        Integer[] v7 = {20, 15, 10, 5, 8, 2, 100, 28, 42};
        Tree<Integer> tree7 = new Tree<>(v7, "Tree7:");

        System.out.println(tree7.toString());
        tree7.balanceTree();
        tree7.changeName("tree7 after balancing");
        System.out.println(tree7.toString());

        // Assignment Problem 8
        System.out.println(tree1.toString());
        tree1.keepRange(10, 50);
        tree1.changeName("tree1 after keeping only nodes between 10 and 50");
        System.out.println(tree1.toString());

        tree7.changeName("Tree 7");
        System.out.println(tree7.toString());
        tree7.keepRange(8, 85);
        tree7.changeName("tree7 after keeping only nodes between 8  and 85");
        System.out.println(tree7.toString());

        // Assignment Problem 9
        Integer[] v9 = {66, -15, -83, 3, -10, -7, 65, 16, 75, 70, 71, 83, 200, 90};
        Tree<Integer> tree4 = new Tree<Integer>(v9, "Tree4:");
        System.out.println(tree4.toString());
        System.out.println(tree4.maxLevelSum());

//        // Assignment Problem 10
//        ArrayList<Integer> leaves = new ArrayList<Integer>();
//        Integer[] preorder1 = {10, 3, 1, 7, 18, 13};
//
//        getLeaves(preorder1, 0, preorder1.length - 1, leaves);
//        System.out.print("Leaves are ");
//        for (int leaf : leaves) {
//            System.out.print(leaf + " ");
//        }
//        System.out.println();
//
//        leaves = new ArrayList<Integer>();
//        Integer[] preorder2 = {66, -15, -83, 3, -10, -7, 65, 16, 75, 70, 71, 83, 200, 90};
//
//        getLeaves(preorder2, 0, preorder2.length - 1, leaves);
//        System.out.print("Leaves are ");
//        for (int leaf : leaves) {
//            System.out.print(leaf + " ");
//        }
//        System.out.println();
//
//        // Assignment Problem 11
//        Tree<Integer> treeC = new Tree<Integer>("TreeC");
//        Integer[] data = {44, 9, 13, 4, 5, 6, 7};
//        treeC.createTreeByLevel(data, "Sum Tree1 ?");
//        if (treeC.isSum()) {
//            System.out.println(treeC.toString() + " is Sum Tree");
//        } else {
//            System.out.println(treeC.toString() + " is NOT a Sum Tree");
//        }
//        Integer[] data1 = {52, 13, 13, 4, 5, 6, 7, 0, 4};
//        treeC.createTreeByLevel(data1, "Sum Tree2 ?");
//        if (treeC.isSum()) {
//            System.out.println(treeC.toString() + " is Sum Tree");
//        } else {
//            System.out.println(treeC.toString() + " is NOT a Sum Tree");
//        }
//        Integer[] data2 = {44, 13, 13, 4, 5, 6, 7, 1, 4};
//        treeC.createTreeByLevel(data2, "Sum Tree3?");
//        if (treeC.isSum()) {
//            System.out.println(treeC.toString() + " is Sum Tree");
//        } else {
//            System.out.println(treeC.toString() + " is NOT a Sum Tree");
//        }
//
//        // Assignment Problem 12
//        treeC.changeName("Tree12");
//        System.out.println(treeC.toString() + "MaxPath=" + treeC.maxPath());
//
//
//        Integer[] data12 = {1, 3, 2, 5, 6, -3, -4, 7, 8};
//        treeC.createTreeByLevel(data12, "Another Tree");
//        System.out.println(treeC.toString() + "MaxPath=" + treeC.maxPath());
//
//
//        // Assignment Problem 13
//        Integer[] preorderT = {1, 2, 4, 5, 3, 6, 8, 9, 7};
//        Integer[] postorderT = {4, 5, 2, 8, 9, 6, 7, 3, 1};
//        tree1.createTreeTraversals(preorderT, postorderT, "Tree13 from preorder & postorder");
//        System.out.println(tree1.toString());
//        Integer[] preorderT2 = {5, 10, 25, 1, 57, 6, 15, 20, 3, 9, 2};
//        Integer[] postorderT2 = {1, 57, 25, 6, 10, 20, 9, 2, 3, 15, 5};
//        tree1.createTreeTraversals(preorderT2, postorderT2, "Tree from preorder & postorder");
//        System.out.println(tree1.toString());
    }
}
