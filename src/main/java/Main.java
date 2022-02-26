class Main implements IMain {
    @Override
    public float test(float r) {
        float pi = 3.14f;
        float area = 2 * pi * r;
        return area;
    }

    public static float area(float r) {
        float pi = 1f;
        float area = 2 * pi * r;
        return area;
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

    public int test = Integer.MAX_VALUE / 2;
    protected int test1 = Integer.MAX_VALUE / 2;
    private boolean bool = true;
    final byte by = 123;
    char ch = 'a';
    short sh = 1234;
    int in = 123456789;
    long lon = 1000;
    float pi = 3.14f;
    double e = 2.71828;

    static String b = "abcd";

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 2; i++) {
            sum += i;
        }
//        System.out.println(sum);
    }
}