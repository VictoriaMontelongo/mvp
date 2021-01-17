package imageviewer.apps.swing;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import model.Image;
import imageviewer.view.ImageLoader;

public class FileImageLoader implements ImageLoader{

    private final File root;

    public FileImageLoader(File root) {
        this.root = root;
    }

    FileImageLoader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Image> load() {
        List<Image> result = new ArrayList<>();
        File[] files = root.listFiles(filter());
        for(File file: files){
            result.add(new Image(file.getAbsolutePath()));
        }
        return result;
    }

    private static final String[] ImageExtensions = new String[]{"jpg", "png", "jpeg"};
    private FilenameFilter filter() {
        return new FilenameFilter(){
            @Override
            public boolean accept(File file, String name){
                for(String extension : ImageExtensions)
                    if(name.endsWith(extension)) return true;
                return false;
            }
        };
    }
    
}
