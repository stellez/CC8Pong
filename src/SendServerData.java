import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class SendServerData {

    private PrintWriter outServer;
    Ball ball;
    int contx, conty;

    public SendServerData(PrintWriter outServer){
        this.outServer = outServer;
        ball = new Ball(outServer);
        contx = conty = 0;
    }

    public void sendBallInfo(){
        ball.prepareData(contx, conty);
        ball.run();
        contx++;
        conty++;
    }

    public void sendRacketInfo(){
        outServer.println("01");
    }

    public void sendScoreData(){
        outServer.println("10");
    }
}
