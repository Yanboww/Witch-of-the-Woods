import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class World{
    private final Tile[][] worldMap;
    private static HashMap<String,String> mapKey;
    private static HashMap<String,Boolean>tileProperty;
    private final String worldName;

    public World(String name)
    {
        worldName = name;
        worldMap = new Tile[10][50];
        mapKey = genHashMap();
        tileProperty = genTileProperty();
        genWorldMap();
    }

    private void genWorldMap()
    {
        String[][] mapInput = new String[worldMap.length][worldMap[0].length];
        int row = 0;
        File f;
        try{
            f = new File("TileMap/"+worldName);
            Scanner s = new Scanner(f);
            while(s.hasNextLine())
            {
                String line = s.nextLine();
                for(int i  = 0; i < line.length();i++)
                {
                    mapInput[row][i] = line.substring(i,i+1);
                }
                row++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Map file not found");
        }
        setWorldMap(mapInput);
    }
    private void setWorldMap(String[][] input)
    {
        for(int r = 0; r < worldMap.length;r++)
        {
            for(int c = 0; c < worldMap[r].length; c++)
            {
                //System.out.println(tileProperty.get("#"));
                //System.out.println(tileProperty.get("B"));
                worldMap[r][c] = new Tile(worldName,tileProperty.get(input[r][c]),mapKey.get(input[r][c]));
            }
        }
    }

    private HashMap<String,String> genHashMap()
    {
        if(mapKey == null)
        {
            HashMap<String,String> tempKey = new HashMap<>();
            tempKey.put("#","space");
            tempKey.put("B","block");
            return  tempKey;
        }
        return mapKey;
    }
    private HashMap<String,Boolean> genTileProperty()
    {
        if(tileProperty == null)
        {
            HashMap<String,Boolean> tempProperty = new HashMap<>();
            tempProperty.put("#",true);
            tempProperty.put("B",false);
            return  tempProperty;
        }
        return tileProperty;
    }

    public Tile[][] getWorldMap(){return worldMap;}


}
