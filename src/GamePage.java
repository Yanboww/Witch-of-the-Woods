public class GamePage extends Page{
    private World world;
    private int frameHeight;
    private int frameWidth;
    private Player player;
    public GamePage(String name, int frameHeight, int frameWidth)
    {
        super(name);
        world = new World(name);
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        player = new Player(10,10,10,frameWidth,frameHeight);
    }
    public void setFrameHeight(int height){frameHeight = height;}
    public void setFrameWidth(int width){frameWidth = width;}
    public World getWorld(){return world;}
    public Player getPlayer(){return player;}

}
