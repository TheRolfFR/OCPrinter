import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PrintingCanvas extends JComponent {

    protected Printing printing;

    @Override
    public void paint(Graphics g) {
        PrintingPart[][] pp = printing.getParts();

        for (int x = 0; x < pp.length; x++) {
            for (int y = 0; y < pp[0].length; y++) {
                printPart(pp[x][y], g);
            }
        }
    }

    private void printPart(PrintingPart pp, Graphics g) {
        ArrayList<PixelVector> pvs = pp.getPixelVectors();
        for (PixelVector pv :
                pvs) {
            g.setColor(new Color(pv.getRgb()));
            g.fillRect(pp.getOffsetX() + pv.getX(), pp.getOffsetY() + pv.getY(), pv.getWidth(), pv.getHeight());
        }
    }

    public PrintingCanvas(Printing printing) {
        this.printing = printing;
    }
}
