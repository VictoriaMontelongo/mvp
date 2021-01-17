package control;

import imageviewer.apps.swing.ImagePanel;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageDisplay.Shift;
import java.util.List;
import model.Image;

public class ImagePresenter {
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageDisplay.on(shift());
    }


    private int current(){
        return images.indexOf(imageDisplay.currentImage());
        
    }
    
    private ImageDisplay.Shift shift() {
        return new Shift() {
            @Override
            public Image left() {
                return images.get((current()+1 % images.size()));
            }

            @Override
            public Image rigth() {
                return images.get((current() -1 % images.size()) % images.size());

            }
        };
        
    }
}
