import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AnimSprites implements ActionListener {
    private final Timer t;
    private final String name;
    private final String page;
    private final ArrayList<BufferedImage> frames;
    private int counter;
    private int x;
    private final boolean moveable;
    private int size;
    private int speed;
    public AnimSprites(String name,String page,boolean moveable,int size,int x,int speed)
    {
        t = new Timer(200,this);
        this.name = name;
        this.page = page;
        frames = new ArrayList<>();
        getFrames();
        counter = 0;
        this.x =x;
        this.size = size;
        this.moveable = moveable;
        this.speed = speed;
        t.start();

    }

    public boolean isMoveable(){return moveable;}

    private void getFrames()
    {
        int counter = 1;
        while(true)
        {
            BufferedImage temp = readImage("Image/"+ page + "/sprites/"+name+"_"+counter+ ".png");
            counter++;
            if(temp==null) break;
            else frames.add(temp);
        }
    }
    public BufferedImage returnImage(){return frames.get(counter);}

    public void actionPerformed(ActionEvent e)
    {
        if(counter == frames.size()-1) counter =0;
        else counter++;
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

    public void move(int size)
    {
        if(size!=this.size)
        {
          x = (int)(((double)x/this.size)*size);
        }
        this.size = size;
        x+=size/speed;
    }
    public int getX(){return x;}

    public String getName(){return name;}

}
