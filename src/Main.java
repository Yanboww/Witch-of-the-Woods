import java.io.File;
public class Main {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame("Temp");
        //renameFile("Image/Spells/sprites/tile","Image/Spells/sprites/fireball_right", int 45);
    }

    public static void renameFile(String name, String rename, int max)
    {
        int change = 0;
        int digit1 = 0;
        int digit2 = 0;
        int digit3 = 0;
       while(change!=max+1)
       {
           File f = new File(name+digit1+digit2+digit3+".png");
           File newFile = new File(rename+"_"+change+".png");
           f.renameTo(newFile);
           change++;
           digit3++;
           if(digit3 == 10)
           {
               digit2++;
               digit3 = 0;
               if(digit2==10){
                   digit2=0;
                   digit1++;
               }
           }
       }

    }

}