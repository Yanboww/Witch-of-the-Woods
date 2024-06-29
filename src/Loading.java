import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Loading extends Page implements  ActionListener{
    private int percentage;
    private AnimSprites fire;
    private AnimSprites loadingBar;
    private int panelWidth;
    private ArrayList<String> quotes;
    private String quote;
    public Loading(int width)
    {
        super("loading");
        Timer t = new Timer(1000,this);
        percentage = 0;
        panelWidth = width;
        fire = new AnimSprites("fire","loading",false,panelWidth,0,0);
        loadingBar = new AnimSprites("loadingBar","loading",false,panelWidth,0,0);
        quotes = new ArrayList<>();
        genQuotes();
        quote = randomQuote();
        t.start();
    }

    public int getPercentage(){return percentage;}
    public void updatePanelWidth(int width){panelWidth = width;}
    public AnimSprites getFire(){return fire;}
    public AnimSprites getLoadingBar(){return loadingBar;}
    public String randomQuote()
    {
        return quotes.get((int)(Math.random()*quotes.size()));
    }
    public String getQuote(){
        return  quote.substring(0,quote.indexOf("_"));
    }
    public double getX()
    {
        return Double.parseDouble(quote.substring(quote.indexOf("_")+1));
    }
    public void genQuotes()
    {
        try{
            File f= new File("Loading/loadQuotes");
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                quotes.add(s.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        percentage+=(int)(Math.random()*41);
        if(percentage>100) percentage = 100;
    }
}
