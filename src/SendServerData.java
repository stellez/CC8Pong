import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class SendServerData {

    private PrintWriter outServer;
    Ball ball;
    int XPos, YPos;
    MakeFunction makeFunc;

    public SendServerData(PrintWriter outServer){
        this.outServer = outServer;
        ball = new Ball(outServer);
        XPos = YPos = 0;
        makeFunc = new MakeFunction();
    }

    public void sendBallInfo(){
        YPos = makeFunc.getYPosition(XPos);
        ball.prepareData(XPos, YPos);
        ball.run();
        XPos++;
    }

    public void sendRacketInfo(){
        outServer.println("01");
    }

    public void sendScoreData(){
        outServer.println("10");
    }
}
