import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Button {
    private final String name;
    private Rectangle hitBox;
    private BufferedImage image;

    public Button(String name,boolean hasImage)
    {
        this.name = name;
        hitBox = new Rectangle(0,0,100,20);
        if(hasImage)
        {
            image = readImage("Image/button/"+name+".png");
        }
    }

    public BufferedImage getImage(){return image;}

    public String getName() {
        return name;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(int x,int y, int width, int height)
    {
        //System.out.println(2);
        hitBox = new Rectangle(x,y,width,height);
    }

    public BufferedImage readImage(String imageName)
    {
        try{
            return ImageIO.read(new File(imageName));
        }
        catch(IOException e)
        {
            try{
                return ImageIO.read(new File("Image/button/previous.png"));
            }
            catch (IOException a)
            {
                return null;
            }
        }
    }


}