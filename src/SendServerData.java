import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class SendServerData extends Thread{

    private PrintWriter outServer;
    Ball ball;
    int XPos, YPos;
    MakeFunction makeFunc;

    public SendServerData(PrintWriter outToClient){
        this.outServer = outToClient;
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

    public void sendRacketInfo(String data){
        outServer.println(data);
        outServer.flush();
    }

    public void sendScoreInfo(String data){
        outServer.println(data);
        outServer.flush();
    }

    public void run(){
        while(true){
            sendBallInfo();
            System.out.println("Ball position(x,y);  (" + (XPos-1) + "," + YPos+ ")");
        }
    }
}
