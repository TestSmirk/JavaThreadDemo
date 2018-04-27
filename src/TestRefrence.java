public class TestRefrence {
    static int i=1;
    static {
        i=0;
        System.out.println(i);
    }

    public static void main(String[] args) {
        TestRefrence testRefrence = new TestRefrence();
    }
}
