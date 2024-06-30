import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Rectangle;
public class Player extends Entity implements ActionListener{
    private final AnimSprites walkLeft;
    private final AnimSprites walkRight;
    private final AnimSprites jumpLeft;
    private final AnimSprites jumpRight;
    private final AnimSprites idleLeft;
    private final AnimSprites idleRight;
    private final AnimSprites fallRight;
    private final AnimSprites fallLeft;
    private AnimSprites currentSprite;
    private boolean directionRight;
    private double playerR;
    private double playerC;
    private GamePage test;
    private Rectangle hitBox;

    public Player(int attack, int speed, int health,int width, int height,String name, GamePage test)
    {
        super(attack,speed,health,width, height);
        walkLeft = new AnimSprites(name+"_walk_left",name,true,width,0,0);
        walkRight = new AnimSprites(name+"_walk_right",name,true,width,0,0);
        jumpLeft = new AnimSprites(name+"_jump_left",name,true,width,0,0);
        jumpRight = new AnimSprites(name+"_jump_right",name,true,width,0,0);
        idleLeft = new AnimSprites(name+"_idle_left",name,true,width,0,0);
        idleRight = new AnimSprites(name+"_idle_right",name,true,width,0,0);
        fallRight = new AnimSprites(name+"_fall_right",name,true,width,0,0);
        fallLeft = new AnimSprites(name+"_fall_left",name,true,width,0,0);
        Timer t = new Timer(10,this);
        directionRight = false;
        currentSprite = idleRight;
        playerR = 3;
        playerC = 0;
        this.test = test;
        hitBox = new Rectangle(0,0,100,100);
        t.start();
    }
    public AnimSprites getCurrentSprite(){return  currentSprite;}
    public void move(int x)
    {
        if(x<0) {
            if(test.checkHitBox((int)playerR,(int)(playerC-0.1)))
            {
                playerC-=0.1;
            }
        }

        else {
            if(test.checkHitBox((int)playerR,(int)(playerC+0.1)))
            {
                playerC+=0.1;
            }
        }

        if(playerC < 0) playerC = 0;
        else if(playerC > 49) playerC = 49;
        if(playerC < 2 )
        {
            if(x > 0) setX(getX()+25);
            else {
                setX(getX() - 25);
                if(getX()<-85) setX(-85);
            }
        }
        else if(playerC > 47)
        {
            if(x > 0)
            {
                setX(getX() + 25);
                if(getX()>300) setX(300);
            }
            else {
                setX(getX()-25);;
            }
        }
        else setX(250);
        System.out.println("Player X coord: " + getX());
    }
    public void jump(int y)
    {
        if(playerR+1 == 10) playerR = 8;
        else if( playerR +1 == 1) playerR = 1;
        if(y>0) {
            if(test.checkHitBox((int)playerR+1,(int)playerC))
            {
                playerR ++;
            }
        }
        else {

            if(test.checkHitBox((int)playerR-1,(int)playerC))
            {
                playerR --;
            }
        }
    }
    public void setWidth(int width) {
        if(width!=getWidth())
        {
            setX((int)((double)getX()/getWidth()*width));
        }
        super.setWidth(width);
    }
    public void setHeight(int height) {
        if(height!=getHeight())
        {
            setY((int)((double)getY()/getHeight()*height));
        }
        super.setHeight(height);
    }
    public Rectangle getHitBox(){return hitBox;}


    public void actionPerformed(ActionEvent e){
        if(DrawPanel.keyPressed.equals("A"))
        {
            currentSprite = walkLeft;
            directionRight = false;
            move((int)(-0.0054*getWidth()));
            setHitBox(getX()+(int)(0.0763358*getWidth()),getY()+(int)(0.120865*getWidth()),(int)(0.10178*getWidth()),(int)(0.1246819338*getWidth()));
        }
        else if(DrawPanel.keyPressed.equals("D"))
        {
            currentSprite = walkRight;
            directionRight = true;
            move((int)(0.0054*getWidth()));
            setHitBox(getX()+2*(int)(0.0763358*getWidth()),getY()+(int)(0.120865*getWidth()),(int)(0.10178*getWidth()),(int)(0.1246819338*getWidth()));
        }
        else if(DrawPanel.keyPressed.equals("Space"))
        {
            if(directionRight)
            {
                currentSprite = jumpRight;
            }
            else currentSprite = jumpLeft;
            jump(-10);
        }
        else if(DrawPanel.keyPressed.equals("W"))
        {
            if(directionRight)
            {
                currentSprite = jumpRight;
            }
            else currentSprite = jumpLeft;
            jump(-10);
        }
        else if(DrawPanel.keyPressed.equals("S"))
        {
            if(directionRight)
            {
                currentSprite = fallRight;
            }
            else currentSprite = fallLeft;
            jump(10);
        }
        else{
            if(directionRight)
            {
                currentSprite = idleRight;
            }
            else currentSprite = idleLeft;
        }
    }
    public void setHitBox(int x, int y, int width, int height)
    {
        hitBox = new Rectangle(x,y,width,height);
    }

    public void setPlayerR(int r){playerR = r;}
    public void setPlayerC(int r){playerR = r;}
    public double getPlayerR(){return  playerR;}
    public double getPlayerC(){return  playerC;}



}
