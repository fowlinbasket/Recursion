import java.util.ArrayList;
import java.util.Random;

public class driver {
    public static void main(String[] args) {
        Integer[] ints = new Integer[500];
        for (int i = 0; i < 500; i++) {
            ints[i] = (int) (Math.random() * 500);
        }
        Tree<Integer> bst = new Tree(ints, "DemoTree");
        System.out.println(bst.toString());
    }
}
