import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Timer;

class DrawPanel extends JPanel implements MouseListener, KeyListener,ActionListener{
    private Page page;
    final int WIDTH = 786;
    final int HEIGHT = 463;
    public static String keyPressed;
    boolean drawHitBox;

    public DrawPanel() {
        this.addMouseListener(this);
        setFocusable(true);
        this.addKeyListener(this);
        //page = new SpecialPage("menu",getWidth());
        keyPressed = " ";
        page = new GamePage("test",HEIGHT,WIDTH);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(page instanceof SpecialPage)
        {
            if(page.getPageName().equals("menu"))paintMenu(g);
        }
        else if(page instanceof Loading)
        {
            if(page.getPageName().equals("loading")) paintLoading(g);
        }
        else if(page instanceof  GamePage)
        {
            paintGame(g);
        }
        else{
            if(page.getPageName().equals("Quit")) System.exit(0);
            else if(page.getPageName().equals("New Game")) page = new Page("Continue");
            else if(page.getPageName().equals("Continue")) paintContinue(g);
        }

    }

    protected void paintMenu(Graphics g) {
        SpecialPage s = (SpecialPage) page;
        g.setColor(new Color(0, 26, 23));
        g.fillRect(0,0,getWidth(),getHeight());
        s.setPanelWidth(getWidth());
        g.drawImage(s.getBackground().getStack().get(0),0,0,getWidth(),getHeight(),this);
        int y = 0;
        int x = s.getX();
        for(int i = 0; i<4;i++)
        {
            g.drawImage(s.getBackground().getStack().get(2),x,y,getWidth(),getHeight(),this);
            y+=getHeight()/20;
            x+=getWidth()/10;
        }
        g.drawImage(s.getBackground().getStack().get(1),0,getHeight()/10,getWidth(),getHeight(),this);
        y = -getHeight()/5;
        x = s.getX();
        for(int i = 0; i<4;i++)
        {
            g.drawImage(s.getBackground().getStack().get(2),x,y,getWidth(),getHeight(),this);
            y+=getHeight()/20;
            x+=getWidth()/10;
        }
        g.drawImage(s.getSprites().get(0).returnImage(),(int)(0.76336*getWidth()),(int)(0.73434*getHeight()),getWidth()/14,getHeight()/12,this);
        g.drawImage(s.getSprites().get(1).returnImage(),(int)(0.5089*getWidth()),(int)(0.75*getHeight()),getWidth()/14,getHeight()/12,this);
        g.drawImage(s.getSprites().get(2).returnImage(),(int)(0.3244*getWidth()),(int)(0.66955*getHeight()),getWidth()/14,getHeight()/12,this);
        g.drawImage(s.getSprites().get(3).returnImage(),(int)(0.39185751*getWidth()),(int)(0.74946*getHeight()),getWidth()/12,getHeight()/10,this);
        if(s.getSprites().size()>5){
            ArrayList<AnimSprites> sprites = s.getSprites();
            for(int i = 5; i< sprites.size(); i++)
            {
                AnimSprites sprite = sprites.get(i);
                if(sprite.getName().equals("deer") || sprite.getName().equals("wolf"))
                {
                    g.drawImage(sprite.returnImage(),sprite.getX(),(int)(0.85*getHeight()),getWidth()/8,getHeight()/6,this);
                }
                else if(sprite.getName().equals("walk"))
                {
                    g.drawImage(sprite.returnImage(),sprite.getX(),(int)(0.626349*getHeight()),getWidth()/8,getHeight()/6,this);
                }

            }
        }
        g.drawImage(s.getSprites().get(4).returnImage(),(int)(0.84*getWidth()),(int)(0.6587473*getHeight()),getWidth()/8,getHeight()/6,this);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Monospaced", Font.ITALIC, getWidth()/50));
        g.drawString("Alpha 0.0.1",(int)(0.8524173*getWidth()),(int)(0.97192*getHeight()));
        g.setFont(new Font("Monospaced", Font.BOLD, getWidth()/20));
        y = (int)(0.5399568*getHeight());
        int buttonY = (int)(0.475161987*getHeight());
        x = (int)(0.01*getWidth());
        for(Button pageButton : s.getButtons())
        {
            g.setColor(new Color(0, 26, 23));
            g.drawString(pageButton.getName(),(int)(1.5*x),y);
            g.setColor(new Color(255, 255, 255));
            g.drawString(pageButton.getName(),x,y);
            pageButton.setHitBox(x,buttonY,(int)(0.24*getWidth()),(int)(0.0863930886*getHeight()));
            //g.drawRect(x,buttonY,(int)(0.24*getWidth()),(int)(0.0863930886*getHeight()));
            y+=(int)(0.10799136*getHeight());
            buttonY+=(int)(0.10799136*getHeight());
        }

    }

    public void paintLoading(Graphics g)
    {
        Loading l = (Loading)page;
        l.updatePanelWidth(getWidth());
        g.setColor(new Color(0, 26, 23));
        g.fillRect(0,0,getWidth(),getHeight());
        if(l.getPercentage()>=100)
        {
            page.stopBgm();
            page = new Page(Page.nextPage);
        }
        int x = (int)(0.01*getWidth());
        int y = getHeight()/20;
        int width = getWidth()/20;
        int height = getHeight()/20;
        g.drawImage(l.getButtons().get(0).getImage(),x,y,width,height,this);
        l.getButtons().get(0).setHitBox(x,y,width,height);
        g.drawImage(l.getFire().returnImage(),(int)(0.4096692112*getWidth()),(int)(0.777537797*getHeight()),getWidth()/5,getHeight()/5,this);
        g.drawImage(l.getLoadingBar().returnImage(),(int)(0.3435114504*getWidth()),(int)(0.2807775378*getHeight()),getWidth()/3,getHeight()/2,this );
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Monospaced", Font.ITALIC, getWidth()/50));
        g.drawString(l.getPercentage()+"%",(int)(0.489821883*getWidth()),(int)(0.626349892*getHeight()));
        g.setFont(new Font("Monospaced", Font.BOLD, getWidth()/30));
        g.drawString(l.getQuote(),(int)(l.getX()*getWidth()),(int)(0.7127429806*getHeight()));
    }

    public void paintContinue(Graphics g)
    {
        g.setColor(new Color(0, 26, 23));
        g.fillRect(0,0,getWidth(),getHeight());
        int x = (int)(0.01*getWidth());
        int y = getHeight()/20;
        int width = getWidth()/20;
        int height = getHeight()/20;
        g.drawImage(page.getButtons().get(0).getImage(),x,y,width,height,this);
        page.getButtons().get(0).setHitBox(x,y,width,height);
    }

    public void paintGame(Graphics g)
    {
        GamePage game = (GamePage) page;
        game.setFrameHeight(getHeight());
        game.setFrameWidth(getWidth());
        int y = 0;
        g.setColor(new Color(255,255,255));
        for(Tile[] blocks : game.worldMap())
        {
            int x = 0;
            for(Tile block: blocks)
            {
                g.drawImage(block.getTileImage(),x,y,getWidth()/5,getWidth()/8,this);
                if(!block.isCharacterOnTile())
                {
                    block.setHitBox(x,y,getWidth()/5,getHeight()/5);
                    if(drawHitBox)
                    {
                        g.drawRect(x,y,getWidth()/5,getHeight()/5);
                    }
                }
                x+=getWidth()/5;
            }
            y+=getWidth()/8;
        }
        g.drawImage(game.getPlayer().getCurrentSprite().returnImage(),game.getPlayer().getX(),game.getPlayer().getY(),getWidth()/3,getWidth()/3,this);
        if(drawHitBox)
        {
            if(game.getPlayer().getCurrentSprite().getName().contains("right"))
            {
                //g.drawRect(game.getPlayer().getX()+60,game.getPlayer().getY()+95,80,98);
                g.drawRect(game.getPlayer().getX()+(int)(0.0763358*getWidth()),game.getPlayer().getY()+(int)(0.120865*getWidth()),(int)(0.10178*getWidth()),(int)(0.1246819338*getWidth()));
            }
            else{
                //g.drawRect(game.getPlayer().getX()+120,game.getPlayer().getY()+95,80,98);
                g.drawRect(game.getPlayer().getX()+2*(int)(0.0763358*getWidth()),game.getPlayer().getY()+(int)(0.120865*getWidth()),(int)(0.10178*getWidth()),(int)(0.1246819338*getWidth()));
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        System.out.println(p);
        if(e.getButton() == 1)
        {
            for(Button currentButton : page.getButtons())
            {
                Rectangle hitBox = currentButton.getHitBox();
                if(hitBox.contains(p))
                {
                    Page.prevPage = page.getPageName();
                    page.stopBgm();
                    if(currentButton.getName().equals("menu")) page = new SpecialPage("menu",getWidth());
                    else {
                        page = new Loading(getWidth());
                        Page.nextPage = currentButton.getName();
                        if(Page.nextPage.equals("Quit")) page = new Page("Quit");
                    }

                }
            }
        }
    }

    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        keyPressed = KeyEvent.getKeyText(e.getKeyCode());
        //System.out.println(keyPressed);
        if(keyPressed.equals("Ctrl")) drawHitBox=!drawHitBox;
    }
    public void keyReleased(KeyEvent e){
        keyPressed = "idle";
    }
    public void actionPerformed(ActionEvent e){}

}