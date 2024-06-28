import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Background {
    private ArrayList<BufferedImage> stack;
    public  Background(String name)
    {
        stack = genStack(name);
    }

    private ArrayList<BufferedImage> genStack(String name)
    {
        ArrayList<BufferedImage> stack = new ArrayList<>();
        int i =1;
        while(true)
        {
            BufferedImage tempStorage = readImage("Image/"+name+"/"+name+"_"+i+".png");
            if(tempStorage == null) break;
            else stack.add(tempStorage);
            i++;
        }
        stack.add(readImage("Image/menu/u6Y2Di.gif"));
        return  stack;
    }

    public ArrayList<BufferedImage> getStack()
    {
        return stack;
    }

    public BufferedImage readImage(String imageName)
    {
        try{
            return ImageIO.read(new File(imageName));
        }
        catch(IOException e)
        {
            return null;
        }
    }



}
