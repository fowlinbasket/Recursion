

public class tester {
    public void testCount(Tree<Integer> t) {
        System.out.println(t.getHeight());
    }

    public static void main(String[] args) {
        Tree<Integer> t = new Tree<Integer>("Tester Tree");
        for (int i = 0; i < 30; i++) {
            t.bstInsert((int) (Math.random() * 100));
        }
        System.out.println(t.toString());
        System.out.println(t.getHeight());
    }
}
