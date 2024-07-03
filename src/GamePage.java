import java.awt.Rectangle;
import java.util.ArrayList;

public class GamePage extends Page{
    private final World world;
    private final Player player;
    private Tile currentTile;
    private Physics playerPhysics;
    private ArrayList<AnimSprites> pageSprites;
    public GamePage(String name, int frameHeight, int frameWidth)
    {
        super(name);
        world = new World(name);
        player = new Player(10,60,10,frameWidth,frameHeight,"player",this);
        currentTile = world.getWorldMap()[3][0];
        playerPhysics = new Physics(player,this);
        pageSprites = new ArrayList<>();
        genPageSprite();
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
        setPlayerPosX(0);
        if(currentTile.getTileC() == 0 && DrawPanel.keyPressed.equals("A")) return false;
        else if(currentTile.getTileC() == 49 && DrawPanel.keyPressed.equals("D")) return false;
        int r = currentTile.getTileR();
        if(DrawPanel.keyPressed.equals("A"))
        {
            int c = currentTile.getTileC()-1;
            Tile currentTile = map[r][c];
            if(!currentTile.isCharacterOnTile() && currentTile.getHitBox().intersects(player.getHitBox()))
            {
                return false;
            }
        }
        else{
            int c = currentTile.getTileC()+1;
            Tile currentTile = map[r][c];
            if(!currentTile.isCharacterOnTile() && currentTile.getHitBox().intersects(player.getHitBox()))
            {
                return false;
            }
        }
        return true;
    }
    public boolean checkVerticalHitBox(String state)
    {
        Tile[][] map = world.getWorldMap();
        setPlayerPosX(0);
        if(currentTile.getTileR() == 8 && state.equals("down")) return false;
        if(currentTile.getTileR() == 0 && state.equals("up")) return false;
        int c = currentTile.getTileC();
        if(state.equals("up"))
        {
            Tile temp = map[currentTile.getTileR()-1][c];
            if(!temp.isCharacterOnTile() && temp.getHitBox().intersects(player.getHitBox())) return false;
        }
        else{
            Tile temp = map[currentTile.getTileR()+1][c];
            if(!temp.isCharacterOnTile() &&  temp.getHitBox().intersects(player.getHitBox())) return false;
        }
        return true;
    }
    public ArrayList<AnimSprites> getPageSprites(){return pageSprites;}
    public void genPageSprite(){
        if(getPageName().equals("dark matter"))
        {
            pageSprites.add(new AnimSprites("Church","Tile",false, getPlayer().getWidth(), 0,0));
            pageSprites.add(new AnimSprites("Water","Tile",false, getPlayer().getWidth(), 0,0));
            pageSprites.add(new AnimSprites("Fire","Tile",false, getPlayer().getWidth(), 0,0));
            pageSprites.add(new AnimSprites("Tree","Tile",false, getPlayer().getWidth(), 0,0));
            pageSprites.add(new AnimSprites("Grave","Tile",false, getPlayer().getWidth(), 0,0));
            pageSprites.add(new AnimSprites("Cross","Tile",false, getPlayer().getWidth(), 0,0));
        }
    }
    public boolean setPlayerPosY(String state)
    {
        Tile[][] map = world.getWorldMap();
        setPlayerPosX(0);
        if(currentTile.getTileR()==19) return false;
        if(currentTile.getTileR() == 0) return false;
        Tile aboveTile = map[currentTile.getTileR()-1][currentTile.getTileC()];
        Tile belowTile = map[currentTile.getTileR()+1][currentTile.getTileC()];
        if(aboveTile.isCharacterOnTile() && state.equals("up")){
            return true;
        }
        else if(!state.equals("up") && belowTile.isCharacterOnTile()) return true;
        else return false;
    }
    public boolean setPlayerPosX(int x)
    {
        for(Tile[] row : worldMap())
        {
            for(Tile block : row)
            {
                if(block.isCharacterOnTile())
                {
                   if(block.getHitBox().intersects(player.getHitBox()))
                   {
                       if(currentTile != block)
                       {
                           Rectangle playerHitBox = player.getHitBox();
                           Rectangle blockHitBox = block.getHitBox();
                           Rectangle currentTileHitBox = currentTile.getHitBox();
                           Rectangle playerBlockIntersect = blockHitBox.intersection(playerHitBox);
                           Rectangle playerCurrentIntersect = currentTileHitBox.intersection(playerHitBox);
                           double playerBlockArea = playerBlockIntersect.getHeight()*playerBlockIntersect.getWidth();
                           double playerCurrentArea = playerCurrentIntersect.getHeight()*playerCurrentIntersect.getWidth();
                           if(playerBlockArea>playerCurrentArea)
                           {
                               currentTile = block;
                           }
                       }
                       //System.out.println(block.getTileR() + "," + block.getTileC());
                       if(block.getTileC() != 49 && block.getTileC() != 0)
                       {
                           if(x>0)
                           {
                               if(!world.getWorldMap()[block.getTileR()][block.getTileC()+1].isCharacterOnTile() )
                               {
                                   return false;
                               }
                           }
                           else if( x<0)
                           {
                               if(!world.getWorldMap()[block.getTileR()][block.getTileC()-1].isCharacterOnTile())
                               {
                                   return false;
                               }

                           }
                       }
                   }
                }
            }
        }
        return true;
    }

    public void simulateJump(double y)
    {
        playerPhysics.setCurrentVelocity(y);
    }

}
