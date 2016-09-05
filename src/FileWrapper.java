
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;

/**
 * Created by scary on 9/4/2016.
 */
public class FileWrapper {

    private final File out = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Pictures\\Wallpapers");

    public void transfer(int sizeLimit){
        File managerDir = new File("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\Packages");
        String deliveryManager = "";
        for(String file: managerDir.list()){
            if(file.contains("Microsoft.Windows.ContentDeliveryManager")){
                deliveryManager = file;
            }
        }
        File assets = new File(managerDir, deliveryManager + "\\LocalState\\Assets");
        System.out.println("Assets Directory: "+ assets.getAbsolutePath());
        if(out.exists()){
            for(File f: out.listFiles()){
                f.delete();
            }
        } else {
            out.mkdirs();
        }
        if(assets.isDirectory()){
            for(File f: assets.listFiles()){
                ImageIcon icon = new ImageIcon(f.getAbsolutePath());
                int width = icon.getIconWidth();
                if(!f.isDirectory() && width > sizeLimit){
                    File fCopy = new File(out.getAbsolutePath() + File.separator +f.getName() + ".png");
                    try{
                        Files.copy(f.toPath(), fCopy.toPath());
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
