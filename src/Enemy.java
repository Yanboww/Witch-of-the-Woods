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
    private Rectangle damageHitBox;
    private AnimSprites currentSprite;
    private GamePage world;
    private boolean isRight;
    private int originX;
    private int distanceFromOrigin;
    private boolean didDamage;
    public Enemy(int attack, int speed, int health, int width, int height, int x, int y, String name, GamePage world, int row, int column)
    {
      super(attack,speed, health, width, height, x, y);
      this.name = name;
      this.world = world;
      this.row = row;
      this.column = column;
      originX = 0;
      distanceFromOrigin = 0;
      hitBox = new Rectangle(getX()+50,getY()+200,(int)(0.0481679389*getWidth()),(int)(0.075*getWidth()));
      damageHitBox = new Rectangle(getX()+50,getY()+200,200,(int)(0.075*getWidth()));
      genSprites();
      currentSprite = walkLeft;
      isRight = false;
      Timer t = new Timer(100,this);
      t.start();
    }

    public void setX(int x)
    {
        originX = x;
    }
    public int getColumn(){return column;}


    public Rectangle getHitBox(){return hitBox;}
    public Rectangle getDamageHitBox(){return damageHitBox;}
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
            distanceFromOrigin = (int)((double)distanceFromOrigin/getWidth()*width);
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

    public int getX()
    {
        return originX + distanceFromOrigin;
    }

    public void actionPerformed(ActionEvent e)
    {
        int x = (int)world.getPlayer().getHitBox().getX();
        damageHitBox = new Rectangle(originX+distanceFromOrigin+(int)(0.1272264631*getWidth()),getY()+(int)(0.1079913607*getHeight()),50,100);
        if(world.checkPlayerHitBox(hitBox))
        {
            if(isRight) {
                hitBox = new Rectangle(originX+distanceFromOrigin+(int)(0.1272264631*getWidth()),getY()+(int)(0.1079913607*getHeight()),(int)(0.1908396947*getWidth()),(int)(0.2159827214*getHeight()));
                currentSprite = attackRight;
            }
            else {
                hitBox = new Rectangle(originX+distanceFromOrigin+(int)(0.0254452926*getWidth()),getY()+(int)(0.10799131607*getHeight()),(int)(0.1908396947*getWidth()),(int)(0.2159827214*getHeight()));
                currentSprite = attackLeft;
            }
            if(currentSprite.getCounter() >=6 && !didDamage){
                world.hitEvent(-10);
                didDamage = true;
            }
            else if(currentSprite.getCounter()==1) didDamage = false;
        }
        else if(x> hitBox.getX()){
            hitBox = new Rectangle(originX+distanceFromOrigin+(int)(0.1272264631*getWidth()),getY()+(int)(0.1079913607*getHeight()),(int)(0.1908396947*getWidth()),(int)(0.2159827214*getHeight()));
            currentSprite = walkRight;
            isRight = true;
            int random = (int)(Math.random()*2)+1;
            if(random==1) distanceFromOrigin += (int)(0.0127226463*getWidth());

            else distanceFromOrigin += (int)(0.0087226463*getWidth());
        }
        else if(x< hitBox.getX())
        {
            hitBox = new Rectangle(originX+distanceFromOrigin+(int)(0.0254452926*getWidth()),getY()+(int)(0.10799131607*getHeight()),(int)(0.1908396947*getWidth()),(int)(0.2159827214*getHeight()));
            currentSprite = walkLeft;
            isRight = false;
            int random = (int)(Math.random()*2)+1;
            if(random == 1) distanceFromOrigin-= (int)(0.0127226463*getWidth());
            else distanceFromOrigin -=(int)(0.0087226463*getWidth());
        }
    }




}
