package communication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Topper on 5/16/2018.
 */

public class Poller {
    private static final Poller instance = new Poller();
    private Timer mTimer;
    private boolean running;
    private Poller(){}
    public static Poller getInstance()
    {
        return getInstance();
    }
    public void run()
    {
        running = true;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Poll();
            }
        },0, 3000);
    }
    private void Poll()
    {
        if(running) {
            // ClientCommunicator.SINGLETON().testSend();
        }
    }
    public void stop()
    {
        System.out.println("DONE!");
        running=false;
        mTimer.cancel();
        mTimer.purge();
    }
}
