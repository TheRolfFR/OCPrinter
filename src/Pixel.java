@SuppressWarnings("unused")
public class Pixel {
    protected int rgb;

    public Pixel() {
        rgb = 0xffffff;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public int getRgb() {
        return rgb;
    }

    public Pixel(int rgb) {
        this.rgb = rgb;
    }

    static int abs(int i){return i<0?-i:i;}
    static int a(int rgb){return (rgb>>24)&255;}
    static int r(int rgb){return (rgb>>16)&255;}
    static int g(int rgb){return (rgb>>8)&255;}
    static int b(int rgb){return rgb&255;}

    public boolean equals (Pixel o) {
        return a(this.rgb)>220 && abs(r(this.rgb)-r(o.rgb))<30 && abs(g(this.rgb)-g(o.rgb))<20 && abs(b(this.rgb)-b(o.rgb))<50;
    }
}
