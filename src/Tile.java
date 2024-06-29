import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
public class Tile {
    private final BufferedImage tileImage;
    private final String name;
    private boolean characterOnTile;
    private Rectangle hitBox;
    public Tile(String world, boolean characterOnTile, String name)
    {
        this.characterOnTile = characterOnTile;
        this.name = name;
        tileImage = readImage("Image/Tile/"+world+"/" + name +".png");
        hitBox = new Rectangle(0,0,100,100);
    }
    public void setCharacterOnTile(boolean characterOnTile){this.characterOnTile = characterOnTile;}
    public String getName(){return name;}
    public boolean isCharacterOnTile(){return characterOnTile;}
    public BufferedImage getTileImage(){return  tileImage;}
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
    public void setHitBox(int x,int y, int width,int height)
    {
        hitBox = new Rectangle(x,y,width,height);
    }
    public Rectangle getHitBox(){return hitBox;}




}
