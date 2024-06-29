import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
public class Player extends Entity implements ActionListener{
    private final AnimSprites walkLeft;
    private final AnimSprites walkRight;
    private final AnimSprites jumpLeft;
    private final AnimSprites jumpRight;
    private final AnimSprites idleLeft;
    private final AnimSprites idleRight;
    private AnimSprites currentSprite;
    private boolean directionRight;

    public Player(int attack, int speed, int health,int width, int height)
    {
        super(attack,speed,health,width, height);
        walkLeft = new AnimSprites("test_walk_left","EntityTest",true,0,0,0);
        walkRight = new AnimSprites("test_walk_right","EntityTest",true,0,0,0);
        jumpLeft = new AnimSprites("test_jump_left","EntityTest",true,0,0,0);
        jumpRight = new AnimSprites("test_jump_right","EntityTest",true,0,0,0);
        idleLeft = new AnimSprites("test_idle_left","EntityTest",true,0,0,0);
        idleRight = new AnimSprites("test_idle_right","EntityTest",true,0,0,0);
        Timer t = new Timer(100,this);
        directionRight = false;
        currentSprite = idleRight;
        t.start();
    }
    public AnimSprites getCurrentSprite(){return  currentSprite;}
    public void move(int x)
    {
        int tempValue = getX();
        setX(tempValue+x);
    }
    public void jump(int y)
    {
        int tempValue = getY();
        setY(tempValue+y);
    }


    public void actionPerformed(ActionEvent e){
        if(DrawPanel.keyPressed.equals("A"))
        {
            currentSprite = walkLeft;
            directionRight = false;
            move(-10);
        }
        else if(DrawPanel.keyPressed.equals("D"))
        {
            currentSprite = walkRight;
            directionRight = true;
            move(10);
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
                currentSprite = jumpRight;
            }
            else currentSprite = jumpLeft;
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



}
