class Main implements Runnable {
    public float test(float r) {
        Main.staticMethod();
        Main main = new Main();

        main.area(3);
        super.equals(null);
        this.run();
        ((Runnable) main).run();
        float pi = 3.14f;
        float area = 2 * pi * r;

        testArray();

        return area;
    }

    private void testArray() {
        int[] intArray = new int[10];
        String[] strArray = new String[10];
        int[][] intAA = new int[10][10];
        int[][][] intDim = new int[3][4][5];
        int x = intArray.length;
        intArray[0] = 1000;
        int y = intArray[0];
        strArray[0] = "hello array";
        String s = strArray[0];
    }

    public float area(float r) {
        float pi = 1f;
        float area = 2 * pi * r;
        return area;
    }

    public static void staticMethod() {
        System.out.println(b);
    }

    @Override
    public void run() {
        System.out.println(1024111);
    }

    public int test = Integer.MAX_VALUE / 2;
    protected static int test1 = Integer.MAX_VALUE / 2;
    private boolean bool = true;
    final byte by = 123;
    char ch = 'a';
    short sh = 1234;
    int in = 123456789;
    long lon = 1000;
    float pi = 3.14f;
    double e = 2.71828;
    int[] arr = new int[]{111, 123};

    static int b = 4201;
    static int d = 1024121;

    public static void main(String[] args) {
        int x = 32768;
        Main main = new Main();
        main.test = 100;
        x = main.test;
        Main.test1 = 11111;
        x = Main.test1;

        Object obj = main;
        if (obj instanceof Main) {
            main = (Main) obj;
            System.out.println(main.test);
            System.out.println(Main.test1);
        }

        new Main().test(3);

        System.out.println(args.length > 0 ? args[0] : "hello kvm");
        testClass();
        testNative();
    }

    private static void testNative() {
        String s1 = "abc1";
        String s2 = "abc1";
        System.out.println(s1 == s2);
        int x = 1;
        String s3 = "abc" + x;
        System.out.println(s1 == s3);
        s3 = s3.intern();
        System.out.println(s1 == s3);
    }

    private static void testClass() {
        System.out.println(void.class.getName());
        System.out.println(boolean.class.getName());
        System.out.println(byte.class.getName());
        System.out.println(char.class.getName());
        System.out.println(short.class.getName());
        System.out.println(int.class.getName());
        System.out.println(long.class.getName());
        System.out.println(float.class.getName());
        System.out.println(double.class.getName());
        System.out.println(Object.class.getName());
        System.out.println(int[].class.getName());
        System.out.println(int[][].class.getName());
        System.out.println(Object[].class.getName());
        System.out.println(Object[][].class.getName());
        System.out.println(Runnable.class.getName());
        System.out.println("abc".getClass().getName());
        System.out.println(new double[0].getClass().getName());
        System.out.println(new String[0].getClass().getName());
    }
}