import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
public class Enemy extends Entity implements ActionListener {
    private String name;
    private AnimSprites walkLeft;
    private AnimSprites walkRight;
    private AnimSprites attackLeft;
    private AnimSprites attackRight;
    private AnimSprites takeDamageRight;
    private AnimSprites takeDamageLeft;
    private int row;
    private int column;
    private Rectangle hitBox;
    private AnimSprites currentSprite;
    private GamePage world;
    private boolean isRight;
    public Enemy(int attack, int speed, int health, int width, int height, int x, int y, String name, GamePage world)
    {
      super(attack,speed, health, width, height, x, y);
      this.name = name;
      this.world = world;
      int row = 6;
      int column = 45;
      hitBox = new Rectangle(getX()+(int)(0.0690585242*getWidth()),getY()+200,(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
      genSprites();
      currentSprite = walkLeft;
      isRight = false;
      Timer t = new Timer(100,this);
      t.start();
    }

    public Rectangle getHitBox(){return hitBox;}
    private void genSprites()
    {
        walkLeft = new AnimSprites(name+"_walk_left","enemy",true,getWidth(),0,0);
        walkRight = new AnimSprites(name+"_walk_right","enemy",true,getWidth(),0,0);
        attackLeft = new AnimSprites(name+"_attack_left","enemy",true,getWidth(),0,0);
        attackRight = new AnimSprites(name+"_attack_right","enemy",true,getWidth(),0,0);
        takeDamageLeft = new AnimSprites(name+"_takeDamage_left","enemy",true,getWidth(),0,0);
        takeDamageRight = new AnimSprites(name+"_takeDamage_right","enemy",true,getWidth(),0,0);
    }

    public AnimSprites getCurrentSprite()
    {
        return currentSprite;
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

    public void actionPerformed(ActionEvent e)
    {
        int x = world.getPlayer().getX();
        hitBox = new Rectangle(getX()+(int)(0.0690585242*getWidth()),getY()+200,(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
        if(world.checkPlayerHitBox(hitBox))
        {
            if(isRight) currentSprite = attackRight;
            else currentSprite = attackLeft;
        }
        else if(x>getX()){
            currentSprite = walkRight;
            isRight = true;
        }
        else if(x<getX())
        {
            currentSprite = walkLeft;
            isRight = false;
        }
    }




}
