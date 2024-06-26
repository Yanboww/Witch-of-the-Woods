import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable {

    private final DrawPanel p;


    public GameFrame(String display) {
        super(display);
        int frameWidth =500;
        int frameHeight = 500;
        p = new DrawPanel();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(60, 100);
        this.setVisible(true);
        startThread();

    }

    public void startThread() {
        Thread windowThread;
        windowThread = new Thread(this);
        windowThread.start();
    }

    public void run() {
        while (true) {
            p.repaint();
        }
    }
}
