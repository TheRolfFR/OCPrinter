import java.util.ArrayList;

@SuppressWarnings("unused")
public class Printing {
    private PrintingPart[][] parts;

    protected int width, height;

    public PrintingPart[][] getParts() {
        return parts;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Printing(int width, int height) {
        this.width = width;
        this.height = height;

        parts = new PrintingPart[width][height];
    }

    public void addPart(PrintingPart pp) {
        parts[pp.getOffsetX()/16][pp.getOffsetY()/16] = pp;
    }

    public String toFile() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                sb.append('+');
                sb.append(parts[x][y].toFile());
            }
        }

        return sb.toString().substring(1);
    }
}
