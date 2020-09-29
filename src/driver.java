import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class driver {
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


//        // Assignment Problem 3
//        System.out.println(tree1.toString());
//        System.out.println("All paths from tree1");
//        tree1.printAllPaths();
//
//
//        // Assignment Problem 4
//        Integer[] v4 = {66, 75, -15, 3, 65, -83, 83, -10, 16, -7, 70, 200, 71, 90};
//        Tree<Integer> treeA = new Tree<Integer>("TreeA");
//        treeA.createTreeByLevel(v4, "TreeA");
//        System.out.println(treeA.toString());
//        System.out.println("treeA Contains BST: " + treeA.countBST());
//
//        Integer[] a = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
//        Tree<Integer> treeB = new Tree<Integer>("TreeB");
//        treeB.createTreeByLevel(a, "TreeB");
//        System.out.println(treeB.toString());
//        System.out.println("treeB Contains BST: " + treeB.countBST());
//
//        // Assignment Problem 5
//
//        treeB.pruneK(60);
//        treeB.changeName("treeB after pruning 60");
//        System.out.println(treeB.toString());
//        treeA.pruneK(200);
//        treeA.changeName("treeA after pruning 200");
//        System.out.println(treeA.toString());
//
//        // Assignment Problem 6

//        System.out.println(tree1.toString());
//        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(56, 61) + ENDLINE);
//
//        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(6, 25) + ENDLINE);
//
//        // Assignment Problem 7
//        Integer[] v7 = {20, 15, 10, 5, 8, 2, 100, 28, 42};
//        Tree<Integer> tree7 = new Tree<>(v7, "Tree7:");
//
//        System.out.println(tree7.toString());
//        tree7.balanceTree();
//        tree7.changeName("tree7 after balancing");
//        System.out.println(tree7.toString());
//
//        // Assignment Problem 8
//        System.out.println(tree1.toString());
//        tree1.keepRange(10, 50);
//        tree1.changeName("tree1 after keeping only nodes between 10 and 50");
//        System.out.println(tree1.toString());
//
//        tree7.changeName("Tree 7");
//        System.out.println(tree7.toString());
//        tree7.keepRange(8, 85);
//        tree7.changeName("tree7 after keeping only nodes between 8  and 85");
//        System.out.println(tree7.toString());
//
//        // Assignment Problem 9
//        Integer[] v9 = {66, -15, -83, 3, -10, -7, 65, 16, 75, 70, 71, 83, 200, 90};
//        Tree<Integer> tree4 = new Tree<Integer>(v9, "Tree4:");
//        System.out.println(tree4.toString());
//        tree4.maxLevelSum();
//
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
