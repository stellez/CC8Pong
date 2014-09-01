import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class SendServerData {

    private PrintWriter outServer;

    public SendServerData(PrintWriter outServer){
        this.outServer = outServer;
    }

    public void sendBallInfo(){
        outServer.println("00");
    }

    public void sendRacketInfo(){
        outServer.println("01");
    }

    public void sendScoreData(){
        outServer.println("10");
    }
}
