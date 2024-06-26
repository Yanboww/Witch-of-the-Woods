import java.util.ArrayList;
public class Page {
    private final ArrayList<Button> buttons;
    private final String pageName;
    private String bgmName;
    private final SoundUtil bgm;
    private static boolean muted;
    private static boolean mutedBefore;
    public Page(String name)
    {
        pageName = name;
        buttons = getButtons();
        bgm = new SoundUtil();
    }
    public ArrayList<Button> getButtons()
    {
        return buttons;
    }
    public ArrayList<Button> getCurrentButtons() {
        return buttons;
    }

    public String getPageName() {
        return pageName;
    }

    public String getBgmName(){return bgmName;}
    public void startBgm()
    {
        if(muted && !mutedBefore)
        {
            stopBgm();
            mutedBefore = true;
        }
        else if(!muted) bgm.playSound(bgmName);

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