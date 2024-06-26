import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.Locale;
import javax.swing.Timer;

class DrawPanel extends JPanel implements MouseListener, KeyListener,ActionListener{
    private SpecialPage s;

    public DrawPanel() {
        this.addMouseListener(this);
        setFocusable(true);
        this.addKeyListener(this);
        s = new SpecialPage("menu",getWidth());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        s.setPanelWidth(getWidth());
        for(BufferedImage bg : s.getBackground().getStack())
        {
            g.drawImage(bg,s.getX1(),0,getWidth(),getHeight(),this);
            g.drawImage(bg,s.getX2(),0,getWidth(),getHeight(),this);
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void actionPerformed(ActionEvent e){}

}