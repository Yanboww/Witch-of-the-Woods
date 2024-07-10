import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Spell implements ActionListener {
    private final boolean doesDamage;
    private final int multiplier;
    private final String name;
    private int attack;
    private final AnimSprites animLeft;
    private final AnimSprites animRight;
    private int x;
    private int y;
    private boolean isRight;
    private boolean shouldStay;
    private Timer t;
    public Spell(boolean doesDamage,int multiplier, String name, int attack)
    {
        this.doesDamage = doesDamage;
        this.multiplier = multiplier;
        this.name = name;
        this.attack = attack;
        int x =0;
        int y = 0;
        animLeft = new AnimSprites(name+"_left","Spells",true,0,0,0);
        animRight = new AnimSprites(name+"_right","Spells",true,0,0,0);
        t = new Timer(100,this);
        shouldStay = true;
    }

    public AnimSprites getCurrentAnim(boolean isRight)
    {
        this.isRight = isRight;
        shouldStay = true;
        t.start();
        if(isRight) return  animRight;
        else return animLeft;
    }

    public int getDamageDealt(){
        return  multiplier*attack;
    }

    public void updateAttack(int attack){this.attack = attack;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int getX(){return x;}
    public int getY(){return y;}
    public boolean isDoesDamage(){return doesDamage;}
    public String getName(){return name;}
    public boolean isRight(){return  isRight;}
    public boolean isShouldStay(){return shouldStay;}

    public void actionPerformed(ActionEvent e)
    {
        if(isRight) x+=40;
        else x-=40;
        if(x>700 || x<0) {
            shouldStay = false;
            t.stop();
        }
    }



}
