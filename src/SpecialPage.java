import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SpecialPage extends Page implements ActionListener {
    private Timer t;
    private Background b;
    private int panelWidth;
    private int x;
    int aniFrame;
    int counter;
    ArrayList<AnimSprites> sprites;

    public SpecialPage(String name,int width)
    {
        super(name);
        t = new Timer(200,this);
        b = new Background(name);
        panelWidth = width;
        x = 0;
        aniFrame = 0;
        sprites = new ArrayList<>();
        counter = 1;
        genSprite();
        t.start();
    }
    public Background getBackground(){return b;}
    public int getX(){return x;}
    public void setPanelWidth(int width){
        if(panelWidth!=width)
        {
            double percentMap = (double)x/panelWidth;
            x= (int)percentMap * width;
        }
        panelWidth=width;
    }
    public void genSprite()
    {
        if(getPageName().equals("menu"))
        {
            sprites.add(new AnimSprites("chicken",getPageName(),false,panelWidth,0,0));
            sprites.add(new AnimSprites("chicken2",getPageName(),false,panelWidth,0,0));
            sprites.add(new AnimSprites("chicken",getPageName(),false,panelWidth,0,0));
            sprites.add(new AnimSprites("fire",getPageName(),false,panelWidth,0,0));
            sprites.add(new AnimSprites("wood",getPageName(),false,panelWidth,0,0));
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 1; i < sprites.size();i ++)
        {
            AnimSprites sprite = sprites.get(i);
            if(sprite.isMoveable()){
                sprite.move(panelWidth);
                if(sprite.getX()>panelWidth+panelWidth/20)
                {
                    sprites.remove(i);
                    i++;
                }
            }

        }
        if(counter%7==0)
        {
            if(aniFrame == 0)
            {
                x = -panelWidth/20;
                aniFrame++;
            }
            else if(aniFrame == 1)
            {
                x = 0;
                aniFrame=0;
            }
        }
        if(counter%100==0)
        {
           if(getPageName().equals("menu")) {
               sprites.add(new AnimSprites("deer",getPageName(),true,panelWidth,0,30));
               sprites.add(new AnimSprites("wolf",getPageName(),true,panelWidth,-panelWidth/6,30));
           }

        }
        if(counter%240==0)
        {
            if(getPageName().equals("menu"))  sprites.add(new AnimSprites("walk",getPageName(),true,panelWidth,(int)(0.09427*panelWidth),100));
        }
        counter++;
    }

    public ArrayList<AnimSprites> getSprites(){return sprites;}
}
