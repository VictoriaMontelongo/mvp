package imageviewer.apps.swing;

import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageDisplay.Shift;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.transform.Scale;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;

public class ImagePanel extends JPanel implements ImageDisplay{
    
    private BufferedImage bitmap;
    private BufferedImage bitmap2;
    private Image image;
    private int x;
    private Shift shift;
    
    public ImagePanel(){
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);

    }
    
    @Override
    public void paint(Graphics g) {
        if(bitmap != null )g.drawImage(bitmap, x, 0, null);
        if(bitmap2 != null & x!=0 )g.drawImage(bitmap2, x , 0, null);
      
    }
    
    @Override
    public void display(Image image) {
        this.image = image;
        this.bitmap = loadBitmap(image);
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }

    private static BufferedImage loadBitmap(Image image){
        try{
            return ImageIO.read(new File(image.getName()));
        }catch(IOException e){
            return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        private int initial;

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.initial = e.getX();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x=0;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX() - initial;
            if(x == 0 ){
                bitmap2 = null;
            }
            if(x != 0 && ImagePanel.this.x/x <= 0){
                if (x > 0) bitmap2 = loadBitmap(shift.left());
                if( x < 0) bitmap2 = loadBitmap(shift.rigth());
            }
            ImagePanel.this.x = x;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
    

    
}
