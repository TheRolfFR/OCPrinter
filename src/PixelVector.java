@SuppressWarnings("unused")
public class PixelVector extends Pixel {

    @SuppressWarnings("SpellCheckingInspection")
    protected static final String HEX = "0123456789abcdef";

    protected int x, y, width, height;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int setX(int x) {
        this.x = x;
        return x;
    }

    public int setY(int y) {
        this.y = y;
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PixelVector(int rgb) {
        super(rgb);
        init(0, 0);
    }

    public PixelVector(int rgb, int x, int y) {
        super(rgb);
        init(x, y);
    }

    public PixelVector(Pixel p, int x, int y) {
        super(p.getRgb());
        init(x, y);
    }

    private void init(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 1;
        this.height = 1;
    }

    public int increaseHeight() {
        return (++this.height);
    }

    public int increaseWidth() {
        return (++this.width);
    }

    public boolean contains(int sx, int sy) {
        return sx >= x && sx < x + width && sy >= y && sy < y + width;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height;
    }

    public String toFile() {
        return new String(new char[]{
            HEX.charAt(x),
            HEX.charAt(16-y-height),
            HEX.charAt(height-1),
            HEX.charAt(width-1),
            HEX.charAt((rgb>>20)&15),
            HEX.charAt((rgb>>16)&15),
            HEX.charAt((rgb>>12)&15),
            HEX.charAt((rgb>>8)&15),
            HEX.charAt((rgb>>4)&15),
            HEX.charAt((rgb)&15)
        });
    }
}
