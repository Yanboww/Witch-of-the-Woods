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
        page = new GamePage("dark matter",HEIGHT,WIDTH);
        page.stopBgm();
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
            else if(page.getPageName().equals("Continue")){
                page = new GamePage("dark matter",HEIGHT,WIDTH);
                paintContinue(g);
            }
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
        for(int i = 0; i < game.getBg().getStack().size(); i++)
        {
            g.drawImage(game.getBg().getStack().get(i),0,0,getWidth(),getHeight(),this);
        }
        game.setFrameHeight(getHeight());
        game.setFrameWidth(getWidth());
        g.drawImage(game.getPlayer().getCurrentSprite().returnImage(),game.getPlayer().getX(),game.getPlayer().getY(),getWidth()/6,getWidth()/6,this);
        int y = 3*getHeight()/9;
        for(Tile[] blocks : game.getWorld().getWorldMap())
        {
            int x = game.getPlayer().getWorldX();
            for(Tile block: blocks)
            {
                block.setHitBox(x,y,getWidth()/9,getHeight()/9);
                if(!block.isCharacterOnTile())
                {
                    g.drawImage(block.getTileImage(),x,y,getWidth()/9,getHeight()/9,this);
                }
                else if(!block.getName().equals("space"))
                {
                    if(block.getName().equals("Water"))
                    {
                        g.drawImage(game.getPageSprites().get(1).returnImage(),x,y,getWidth()/8,getHeight()/8,this);
                    }
                    else if(block.getName().equals("Church"))
                    {
                        g.drawImage(game.getPageSprites().get(0).returnImage(),x,y-(int)(0.3887688985*getHeight()),getWidth()/6,getHeight()/2,this);
                    }
                    else if(block.getName().equals("Tree"))
                    {
                        g.drawImage(game.getPageSprites().get(3).returnImage(),x,y-(int)(0.3455723542*getHeight()),getWidth()/6,getHeight()/2,this);
                    }
                    else if(block.getName().equals("Fire"))
                    {
                        g.drawImage(game.getPageSprites().get(2).returnImage(),x,y+(int)(0.0215982721*getHeight()),getWidth()/9,getHeight()/9,this);
                    }
                    else if(block.getName().equals("Grave"))
                    {
                        g.drawImage(game.getPageSprites().get(4).returnImage(),x,y+(int)(0.0215982721*getHeight()),getWidth()/9,getHeight()/9,this);
                    }
                    else if(block.getName().equals("Cross"))
                    {
                        g.drawImage(game.getPageSprites().get(5).returnImage(),x,y+(int)(0.0215982721*getHeight()),getWidth()/12,getHeight()/9,this);
                    }
                }
                x+=getWidth()/9;
            }
            y+=getHeight()/9;
        }
        if(drawHitBox )
        {
            int pX = (int)game.getPlayer().getHitBox().getX();
            int pY = (int)game.getPlayer().getHitBox().getY();
            int pW = (int)game.getPlayer().getHitBox().getWidth();
            int pH = (int)game.getPlayer().getHitBox().getHeight();
            g.drawRect(pX,pY,pW,pH);
        }
        g.setColor(new Color(255,255,255));
        game.genEnemies();
        Enemy e = game.getEnemies()[0];
        e.setX((int)game.getWorld().getWorldMap()[0][e.getColumn()].getHitBox().getX());
        g.drawImage(e.getCurrentSprite().returnImage(),e.getX(),e.getY(),getWidth()/3,getHeight()/3,this);
       /* Rectangle hitE = e.getDamageHitBox();
        g.drawRect((int)hitE.getX(),(int)hitE.getY(),(int)hitE.getWidth(),(int)hitE.getHeight());*/
        g.drawString("Health: " + game.getPlayer().getHealth(),100,100);
        if(!game.getSpellsOnMap().isEmpty())
        {
            System.out.println("ap");
            Spell spell = game.getSpellsOnMap().get(0);
            AnimSprites spellSprite = spell.getCurrentAnim(spell.isRight());
            g.drawImage(spellSprite.returnImage(),spell.getX(),spell.getY(),getWidth()/10,getHeight()/10,this);

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
        if(keyPressed.equals("M")){
            page.setMuted();
            page.startBgm();
        }
    }
    public void keyReleased(KeyEvent e){
        keyPressed = "idle";
    }
    public void actionPerformed(ActionEvent e){}

}