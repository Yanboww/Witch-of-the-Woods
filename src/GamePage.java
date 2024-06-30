public class GamePage extends Page{
    private final World world;
    private final Player player;

    public GamePage(String name, int frameHeight, int frameWidth)
    {
        super(name);
        world = new World(name);
        player = new Player(10,10,10,frameWidth,frameHeight,"player",this);
    }
    public void setFrameHeight(int height){
        player.setHeight(height);
    }
    public void setFrameWidth(int width){
        player.setWidth(width);
    }
    public World getWorld(){return world;}
    public Player getPlayer(){return player;}
    public Tile[][] worldMap()
    {
        Tile[][] worldMap = world.getWorldMap();
        int playerR = (int)player.getPlayerR();
        int playerC = (int)player.getPlayerC();
        Tile[][] display = new Tile[5][5];
        int top = playerR -2;
        int bottom = playerR+2;
        int left = playerC -2;
        int right = playerC+2;
        if(top<0)
        {
            bottom -=top;
            top = 0;
        }
        else if(bottom>= worldMap.length)
        {
            top -= bottom- (worldMap.length-1);
            bottom = worldMap.length-1;
        }
        if(left < 0 )
        {
            right -= left;
            left = 0;
        }
        else if(right >= worldMap[0].length)
        {
            left -= right - (worldMap[0].length-1);
            right = worldMap[0].length-1;
        }
        int row = 0;
        for(int r = top; r <= bottom; r++)
        {
            int col = 0;
            for(int c = left; c <= right; c++)
            {
                display[row][col] = worldMap[r][c];
                col++;
            }
            row++;
        }
        return display;
    }

    public boolean checkHitBox(int r, int c)
    {
        System.out.println( "Player world Pos: " + r + " " + c);
        if(!world.getWorldMap()[r][c].isCharacterOnTile())
        {
           return false;
        }
        return true;
    }

}
