package imageviewer.apps.swing;

import control.ImagePresenter;
import imageviewer.apps.swing.ImagePanel;
import imageviewer.view.ImageDisplay;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import model.Image;

public class main extends JFrame{

    public static void main(String[] args) {
        new main().execute();
    }
    
    private ImagePanel imageDisplay;
    private final ImagePresenter imagePresenter;
    
    public main(){
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        List<Image> images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        this.imagePresenter = new ImagePresenter(images, imageDisplay);
    }
    

    private void execute() {
        this.setVisible(true);
        
    }
     private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        this.imageDisplay = imagePanel;
        return imagePanel;
    }

    
}
