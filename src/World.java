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
        worldMap = new Tile[6][40];
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
                worldMap[r][c] = new Tile(worldName,tileProperty.get(input[r][c]),mapKey.get(input[r][c]),r,c);
            }
        }
    }

    private HashMap<String,String> genHashMap()
    {
        if(mapKey == null)
        {
            HashMap<String,String> tempKey = new HashMap<>();
            tempKey.put("#","space");
            tempKey.put("L", "block_edge_left");
            tempKey.put("R","block_edge_right");
            tempKey.put("U","Under_Platform_Center");
            tempKey.put("Y","Under_Platform_Left");
            tempKey.put("I","Under_Platform_Right");
            tempKey.put("B","block");
            tempKey.put("W","Water");
            tempKey.put("C","Church");
            tempKey.put("F","Fire");
            tempKey.put("T","Tree");
            tempKey.put("G","Grave");
            tempKey.put("J","Cross");
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
            tempProperty.put("L",false);
            tempProperty.put("R",false);
            tempProperty.put("B",false);
            tempProperty.put("U",false);
            tempProperty.put("Y",false);
            tempProperty.put("I",false);
            tempProperty.put("C",true);
            tempProperty.put("W",true);
            tempProperty.put("F",true);
            tempProperty.put("T",true);
            tempProperty.put("J",true);
            tempProperty.put("G",true);
            return  tempProperty;
        }
        return tileProperty;
    }

    public Tile[][] getWorldMap(){return worldMap;}


}
