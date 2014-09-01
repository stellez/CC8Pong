import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Steven on 31/08/2014.
 */
public class PongServer {
    public static void main(String args[]) throws IOException{
        Socket pongServerSocket;
        ServerSocket pongServer = new ServerSocket(4502);
        pongServerSocket = pongServer.accept();
        System.out.println("Connection Accepted");
        PrintWriter out = new PrintWriter(pongServerSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(pongServerSocket.getInputStream()));
        SendServerData sendData = new SendServerData(out);
        while(true){
            sendData.sendBallInfo();
            //sendData.sendRacketInfo();
            //sendData.sendScoreInfo();
            System.out.println("Ball position(x,y);  (" + (sendData.XPos-1) + "," + sendData.YPos+ ")");
        }
    }
}
