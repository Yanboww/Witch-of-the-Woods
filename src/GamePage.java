import java.awt.Rectangle;
import java.util.ArrayList;

public class GamePage extends Page{
    private final World world;
    private final Player player;
    private Tile currentTile;
    private Physics playerPhysics;
    private ArrayList<AnimSprites> pageSprites;
    private Enemy[] enemies;
    public GamePage(String name, int frameHeight, int frameWidth)
    {
        super(name);
        world = new World(name);
        player = new Player(10,50,10,frameWidth,frameHeight,"player",this);
        currentTile = world.getWorldMap()[3][0];
        playerPhysics = new Physics(player,this);
        pageSprites = new ArrayList<>();
        genPageSprite();
        enemies = new Enemy[1];
        genEnemies();
    }
    public Enemy[] getEnemies(){return enemies;}
    public void genEnemies(){
        for(int i = 0; i < enemies.length; i++)
        {
            if(enemies[i]==null) {
                int randomC = (int)(Math.random()*10)+30;
                enemies[i] = new Enemy(10,60,10, player.getWidth(), player.getHeight(),-69420,230,"solomon",this,6,randomC);
            }
        }
    }
    public void setFrameHeight(int height){
        player.setHeight(height);
        for(Enemy e : enemies)
        {
            e.setHeight(height);
        }
    }
    public void setFrameWidth(int width){
        player.setWidth(width);
        for(Enemy e : enemies)
        {
            e.setWidth(width);
        }
    }
    public World getWorld(){return world;}
    public Player getPlayer(){return player;}

    public boolean checkHorizontalHitBox()
    {
        Tile[][] worldMap = world.getWorldMap();
       setPlayerPosX(0);
       int currentColumn = currentTile.getTileC();
       int testColumn = 0;
       if(DrawPanel.keyPressed.equals("A") && currentColumn != 0) testColumn = currentColumn-1;
       else if(DrawPanel.keyPressed.equals("D") && currentColumn != 39) testColumn = currentColumn+1;
       Tile nextTile = worldMap[currentTile.getTileR()][testColumn];
       if(!nextTile.isCharacterOnTile())
       {
           if(nextTile.getHitBox().intersects(player.getHitBox())) return false;
       }
       return true;
    }
    public boolean checkVerticalHitBox(String state)
    {
        Tile[][] map = world.getWorldMap();
        setPlayerPosX(0);
        if(currentTile.getTileR() == 5 && state.equals("down")) return false;
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
    public void setPlayerPosX(int x)
    {
        for(Tile[] row : world.getWorldMap())
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
                               return;
                           }
                       }
                   }
                }
            }
        }
    }

    public boolean checkPlayerHitBox(Rectangle objectHitBox)
    {
        if(objectHitBox.intersects(player.getHitBox())) return true;
        else return false;
    }

    public Tile currentTile()
    {
        for(Tile[] tiles : world.getWorldMap())
        {
            for(Tile tile : tiles)
            {
                if(tile.getHitBox().intersects(player.getHitBox())) return tile;
            }
        }
        return world.getWorldMap()[3][0];
    }


    public Tile getCurrentTile(){return currentTile;}

    public void hitEvent(int hp)
    {
        player.setHealth(player.getHealth()+hp);
    }

    public void simulateJump(double y)
    {
        playerPhysics.setCurrentVelocity(y);
    }

}
