import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecialPage extends Page implements ActionListener {
    private Timer t;
    private Background b;
    private int panelWidth;
    private int x1;
    private int x2;
    public SpecialPage(String name,int width)
    {
        super(name);
        t = new Timer(200,this);
        b = new Background(name);
        panelWidth = width;
        x1 = 0;
        x2 = width;
        t.start();
    }
    public Background getBackground(){return b;}
    public int getX1(){return x1;}
    public int getX2(){return x2;}
    public void setPanelWidth(int width){
        if(panelWidth!=width)
        {
            double percentMap = (double)x2/panelWidth;
            x2 = (int)percentMap * width;
        }
        panelWidth=width;
    }
    public void actionPerformed(ActionEvent e)
    {
        x1 -= panelWidth/10;
        x2 -= panelWidth/10;
        if(x2 <= 0)
        {
            x1 = 0;
            x2 = panelWidth;
        }
    }
}
