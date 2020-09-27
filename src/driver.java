import java.util.ArrayList;
import java.util.Random;

public class driver {
    public static void main(String[] args) {
        Integer[] ints = new Integer[50];
        for (int i = 0; i < 50; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        Tree<Integer> bst = new Tree(ints, "DemoTree");
        System.out.println(bst.toString());
        System.out.println(bst.toString2());
        bst.printAllPaths();
    }
}
