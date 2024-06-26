import java.awt.Rectangle;

public class Button {
    private final String name;
    private Rectangle button;
    public Button(String name)
    {
        this.name = name;
        button = new Rectangle(0,0,200,400);
    }

    public String getName() {
        return name;
    }

    public Rectangle getButton() {
        return button;
    }

    public void setRec(int x,int y, int width, int height)
    {
        button = new Rectangle(x,y,width,height);
    }

}