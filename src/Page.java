import java.util.ArrayList;
public class Page {
    private final ArrayList<Button> buttons;
    private final String pageName;
    private String bgmName;
    private final SoundUtil bgm;
    private static boolean muted;
    private static boolean mutedBefore;
    public static String prevPage;
    public static String nextPage;
    public Page(String name)
    {
        pageName = name;
        buttons = genButtons();
        bgm = new SoundUtil();
        bgmName = name+".wav";
        startBgm();
    }
    public ArrayList<Button> getButtons()
    {
        return buttons;
    }
    public ArrayList<Button> genButtons()
    {
        ArrayList<Button> currentButtons = new ArrayList<>();
        if(pageName.equals("menu"))
        {
            currentButtons.add(new Button("Continue",false));
            currentButtons.add(new Button("New Game",false));
            currentButtons.add(new Button("Options",false));
            currentButtons.add(new Button("Quit",false));
        }
        else{
            currentButtons.add(new Button(prevPage,true));
        }
        return currentButtons;
    }

    public String getPageName() {
        return pageName;
    }


    public void startBgm()
    {
        if(muted && !mutedBefore)
        {
            stopBgm();
            mutedBefore = true;
        }
        else if(!muted) bgm.playSound(pageName+"/"+bgmName);

    }
    public void stopBgm()
    {
        bgm.stopSound();
    }
    public boolean isMuted(){return muted;}
    public void setMuted(){
        muted = !muted;
        mutedBefore = false;
    }

}