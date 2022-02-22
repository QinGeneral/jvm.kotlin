class Main implements IMain {
    @Override
    public void test() {
        int b = 1;
    }

    @Deprecated
    public void testDeprecated() throws Exception {
        Inner inner = new Inner();
        inner.doSomething();
    }

    private class Inner {
        private Inner() {
        }

        private void doSomething() {
        }
    }

    int test = Integer.MAX_VALUE / 2;
    int test1 = Integer.MAX_VALUE / 2;
    boolean bool = true;
    byte by = 123;
    char ch = 'a';
    short sh = 1234;
    int in = 123456789;
    long lon = 1000;
    float pi = 3.14f;
    double e = 2.71828;

    public static final String b = "abcd";

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}