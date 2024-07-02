import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Physics implements ActionListener {

    private Entity being;
    private double gravityConstant;
    private double currentVelocity;
    private GamePage world;
    private String state;
    private boolean jumping;
    private boolean jumpAction;
    private boolean fallAction;
    public Physics(Entity being, GamePage world)
    {
        this.being = being;
        this.world = world;
        gravityConstant = 0.0215982721* being.getHeight();
        Timer t = new Timer(100,this);
        currentVelocity = 0;
        state = "down";
        t.start();
    }

    public void setCurrentVelocity(double currentVelocity){

        if(!jumpAction)
        {
            this.currentVelocity = currentVelocity;
        }
    }

    public void actionPerformed(ActionEvent e) {
        gravityConstant = 0.0215982721* being.getHeight();
        if (currentVelocity>0) {
            state = "down";
            jumping = false;
        }
        else{
            state = "up";
            jumping = true;
        }
        if(being instanceof Player)
        {
            Player player = (Player)being;
            if(!player.getDirectionRight() && DrawPanel.keyPressed.equals("D"))
            {
                player.setX(player.getX()+(int)(0.0561555076*being.getHeight()));
                player.setHitBox(player.getX()+(int)(0.0501679389*player.getWidth()),player.getY()+(int)(0.0572519084*player.getWidth()),(int)(0.0481679389*player.getWidth()),(int)(0.075*player.getWidth()));
            }
            else if(player.getDirectionRight() && DrawPanel.keyPressed.equals("A"))
            {
                player.setX(player.getX()-(int)(0.0539956803*being.getHeight()));
                player.setHitBox(player.getX()+(int)(0.0690585242*player.getWidth()),player.getY()+(int)(0.0572519084*player.getWidth()),(int)(0.0481679389*player.getWidth()),(int)(0.075*player.getWidth()));
            }
            else if(player.getDirectionRight())
            {
                player.setHitBox(player.getX()+(int)(0.0501679389*player.getWidth()),player.getY()+(int)(0.0572519084*player.getWidth()),(int)(0.0481679389*player.getWidth()),(int)(0.075*player.getWidth()));
            }
            else{
                player.setHitBox(player.getX()+(int)(0.0690585242*player.getWidth()),player.getY()+(int)(0.0572519084*player.getWidth()),(int)(0.0481679389*player.getWidth()),(int)(0.075*player.getWidth()));
            }
        }
        if(world.checkVerticalHitBox(state) && jumping)
        {
            jumpAction = true;
            int y = being.getY();
            if(being instanceof  Player && ((Player)being).getPlayerR() <4)
            {
                if(y+currentVelocity < 0 - (int)being.getHeight()/10) being.setY(0-(int)being.getHeight()/10);
                else being.setY(y+(int)currentVelocity);
            }
            else if(y+currentVelocity<(int)(0.1144708423*being.getHeight())) being.setY((int)(0.1144708423*being.getHeight()));
            else being.setY(being.getY()+(int)currentVelocity);
            currentVelocity+=gravityConstant;
        }
        else if(world.checkVerticalHitBox(state)){
            if(!fallAction)
            {
                currentVelocity = 0.0388768898*being.getHeight();
                fallAction= true;
            }
            else{
                currentVelocity += gravityConstant;
                if(currentVelocity>0.05*being.getHeight()) currentVelocity = 0.05* being.getHeight();
            }
            int y = being.getY();
            if(being instanceof Player)
            {
                ((Player) being).setFalling();
            }
            if(being instanceof  Player && ((Player)being).getPlayerR()>15 )
            {
                if(y + currentVelocity > being.getHeight()) being.setY(being.getHeight());
                else being.setY(y+(int)currentVelocity);

            }
            else if(y + currentVelocity > (int)(0.762419*being.getHeight()) ) being.setY((int)(0.762419*being.getHeight()));
            else being.setY(being.getY()+(int)currentVelocity);
        }
        else  {
            jumpAction = false;
            fallAction = false;
            currentVelocity = 0.0215982721* being.getHeight();
            if(being instanceof Player) {
                ((Player) being).setIdle();
            }
        }
    }
}
