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
        ball.start();
        XPos++;
    }

    public void sendRacketInfo(){
        outServer.println("01,02,03");
        outServer.flush();
    }

    public void sendScoreInfo(){
        outServer.println("10,15,16");
        outServer.flush();
    }

    public void run(){
        while(true){
            sendBallInfo();
            sendRacketInfo();
            sendScoreInfo();
            System.out.println("Ball position(x,y);  (" + (XPos-1) + "," + YPos+ ")");
        }
    }
}
