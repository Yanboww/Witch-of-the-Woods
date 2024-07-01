import java.util.Arrays;
public class GamePage extends Page{
    private final World world;
    private final Player player;
    public GamePage(String name, int frameHeight, int frameWidth)
    {
        super(name);
        world = new World(name);
        player = new Player(10,100,10,frameWidth,frameHeight,"player",this);
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
        Tile[][] display = new Tile[9][9];
        int top = playerR -4;
        int bottom = playerR+4;
        int left = playerC -4;
        int right = playerC+4;
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

    public boolean checkHorizontalHitBox()
    {
        Tile[][] map = world.getWorldMap();
        int r = (int)player.getPlayerR();
        if(DrawPanel.keyPressed.equals("A"))
        {
            for(int c = 0 ; c < map.length;c++)
            {
                Tile currentTile = map[r][c];
                if(!currentTile.isCharacterOnTile() && currentTile.getHitBox().intersects(player.getHitBox()))
                {
                    return false;
                }
            }
        }
        else{
            for(int c = map[0].length-1; c >= 0 ;c--)
            {
                Tile currentTile = map[r][c];
                if(!currentTile.isCharacterOnTile() && currentTile.getHitBox().intersects(player.getHitBox()))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean setPlayerPos(int x)
    {
        for(Tile[] row : worldMap())
        {
            for(Tile block : row)
            {
                if(block.isCharacterOnTile())
                {
                   if(block.getHitBox().intersects(player.getHitBox()))
                   {
                       System.out.println(block.getTileR() + "," + block.getTileC());
                       if(block.getTileC() != 49 && block.getTileC() != 0)
                       {
                           if(!world.getWorldMap()[(int)player.getPlayerR()][block.getTileC()+1].isCharacterOnTile() && x>0)
                           {
                               return false;
                           }
                           if(!world.getWorldMap()[(int)player.getPlayerR()][block.getTileC()-1].isCharacterOnTile() && x<0)
                           {
                               return false;
                           }
                       }
                   }
                }
            }
        }
        return true;
    }

}
