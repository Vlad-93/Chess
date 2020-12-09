package board;

public enum File {
    a(1), b(2), c(3), d(4), e(5), f(6), g(7), h(8);
    private final int value;

    File(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static File getFile(int num) {
        switch (num) {
            case 1: return a;
            case 2: return b;
            case 3: return c;
            case 4: return d;
            case 5: return e;
            case 6: return f;
            case 7: return g;
            case 8: return h;
            default: return null;
        }
    }
}
