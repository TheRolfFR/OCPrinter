import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class PrintingPart {

    protected Pixel[][] pixels;
    protected ArrayList<PixelVector> pixelVectors = new ArrayList<>();

    protected int width, height;
    protected int offsetX, offsetY;

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public PrintingPart(BufferedImage img, int sx, int sy, int width, int height) {
        pixels = new Pixel[width][height];
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                pixels[x][y] = new Pixel(img.getRGB(sx+x, sy+y));
            }
        }

        this.offsetX = sx;
        this.offsetY = sy;

        this.width = width;
        this.height = height;
    }

    public void toPixelVectors() {
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                if(!this.isContained(x, y)) {
                    PixelVector tmp = new PixelVector(pixels[x][y], x, y);

                    // expand in diagonal as far as possible
                    /* + -------->
                       |
                       |
                       |
                       V
                     */
                    boolean same = true;
                    int gx = 1;
                    int gy = 1;

                    // while current diagonal extension lower than part && color same
                    while(x+gx < width && y+gy < height && same) {
                        // first diagonal pixel
                        same = pixels[x+gx][y+gy].equals(tmp);
//                        System.err.println(same);

                        // if diagonal pixel same
                        if(same) {
                            // ok then column
                            int c = y;
                            while(c < y+gy && same) {
                                same = pixels[x+gx][c].equals(tmp);
                                ++c;
                            }

                            if(same) {
                                // ok then row
                                int r = x;
                                while(r < x+gx && same) {
                                    same = pixels[r][y+gy].equals(tmp);
                                    ++r;
                                }

                                // if all same
                                if(same) {
                                    ++gx;
                                    ++gy;
                                }
                            }
                        }
                    }

                    // now try vertical (x fixed, y increase)
                    same = true;

                    while(x+gx < width && same) {
                        int c = y;
                        while(c < y+gy && same) {
                            same = pixels[x+gx][c].equals(tmp);
                            ++c;
                        }

                        if(same) {
                            ++gx;
                        }
                    }

                    same = true;
                    // if vertical failed (growing x == growing y (x not modified))
                    if(gx == gy) {
                        // then horizontal (x increase, y fixed)
                        while(y+gy < height && same) {
                            int r = x;
                            while(r < x+gx && same) {
                                same = pixels[r][y+gy].equals(tmp);
                                ++r;
                            }

                            if(same) {
                                ++gy;
                            }
                        }
                    }

                    tmp.setWidth(gx);
                    tmp.setHeight(gy);

                    pixelVectors.add(tmp);
                }
            }
        }
    }

    private boolean isContained(int x, int y) {
        boolean isContained = false;
        int i = 0;

        while(i < pixelVectors.size() && !isContained) {
            isContained = pixelVectors.get(i).contains(x, y);
            ++i;
        }

        return isContained;
    }

    public void printVectors() {
        for (PixelVector pv :
                pixelVectors) {
            System.out.println(pv);
        }
    }

    public ArrayList<PixelVector> getPixelVectors() {
        return pixelVectors;
    }

    public int getNumberOfVectors() {
        return pixelVectors.size();
    }

    public String toFile() {
        StringBuilder sb = new StringBuilder();

        for (PixelVector pv :
                pixelVectors) {
            sb.append(pv.toFile());
        }

        return sb.toString();
    }
}
