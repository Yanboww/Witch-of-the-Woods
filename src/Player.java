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
        super(attack,speed,health,width, height,-70,110);
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
        hitBox = new Rectangle(getX()+(int)(0.0690585242*getWidth()),getY()+(int)(0.0572519084*getWidth()),(int)(0.0581679389*getWidth()),(int)(0.075*getWidth()));
        t.start();
    }
    public AnimSprites getCurrentSprite(){return  currentSprite;}
    public void setFalling(){
        if(directionRight)
        {
            currentSprite = fallRight;
        }
        else currentSprite = fallLeft;
    }
    public void setIdle()
    {
        if(directionRight) currentSprite = idleRight;
        else currentSprite = idleLeft;
    }

    public void move(int x)
    {
        int xValue = getX();
        if(playerC>45)
        {
            if(xValue+x>getWidth()-getWidth()/10) setX(getWidth()-getWidth()/10);
            else setX(xValue+x);
            if(getX()<=(int)(0.44529*getWidth())) playerC = 45;
        }
        else if(playerC < 4)
        {
            if(xValue<-(int)(0.08905855242*getWidth())) setX(-(int)(0.08905855242*getWidth()));
            else setX(xValue+x);
            if(getX()>=(int)(0.2926*getWidth())) playerC = 4;
        }
        else
        {
            if( x< 0 && xValue<=(int)(0.1755725191*getWidth())){
                setX((int)(0.1755725191*getWidth()));
                if(test.setPlayerPosX(x)) playerC-=0.15;

            }
            else if(x >0 && xValue>=(int)(0.6615776081*getWidth()))
            {
                setX((int)(0.6615776081*getWidth()));
                if( test.setPlayerPosX(x)) playerC+=0.15;

            }
            else setX(xValue+x);
        }
        //test.setPlayerPos(x);
        if(playerC < 0) playerC = 0;
        if(playerC > 49) playerC = 49;
    }
    public void jump(double y)
    {
        test.simulateJump(y);
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
    public boolean getDirectionRight(){return directionRight;}


    public void actionPerformed(ActionEvent e){
        if(DrawPanel.keyPressed.equals("A"))
        {
            if(directionRight)
            {
                setX(getX()-25);
                setHitBox(getX()+(int)(0.0690585242*getWidth()),getY()+(int)(0.0572519084*getWidth()),(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
            }
            currentSprite = walkLeft;
            directionRight = false;
            if(test.checkHorizontalHitBox())
            {
                move(-getWidth()/getSpeed());
                setHitBox(getX()+(int)(0.0690585242*getWidth()),getY()+(int)(0.0572519084*getWidth()),(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
            }
        }
        else if(DrawPanel.keyPressed.equals("D"))
        {
            if(!directionRight)
            {
                setX(getX()+26);
                setHitBox(getX()+(int)(0.0501679389*getWidth()),getY()+(int)(0.0572519084*getWidth()),(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
            }
            currentSprite = walkRight;
            directionRight = true;
            if(test.checkHorizontalHitBox())
            {
                move(getWidth()/getSpeed());
                setHitBox(getX()+(int)(0.0501679389*getWidth()),getY()+(int)(0.0572519084*getWidth()),(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
            }
        }
        else if(DrawPanel.keyPressed.equals("Space"))
        {
            if(directionRight)
            {
                currentSprite = jumpRight;
            }
            else currentSprite = jumpLeft;
            jump(-(0.0769330454*getHeight()));
        }
       /* else if(currentSprite!=fallLeft && currentSprite != fallRight){
            if(directionRight)
            {
                currentSprite = idleRight;
            }
            else currentSprite = idleLeft;
        }**/
    }
    public void setHitBox(int x, int y, int width, int height)
    {
        hitBox = new Rectangle(x,y,width,height);
    }

    public void setPlayerR(double r){playerR = r;}
    public void setPlayerC(double c){playerC = c;}
    public double getPlayerR(){return  playerR;}
    public double getPlayerC(){return  playerC;}



}
