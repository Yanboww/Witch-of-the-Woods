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
        for(int i = 1; i < 4; i++)
        {
            stack.add(readImage("Image/Menu/"+name+"_"+i+".png"));
        }
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
