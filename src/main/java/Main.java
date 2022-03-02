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
        return area;
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
    }
}