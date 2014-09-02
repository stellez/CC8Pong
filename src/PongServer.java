import view.ConnectionFrames;

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
        try {
            Socket pongServerSocket;
            ConnectionFrames connectionFrames = new ConnectionFrames();
            connectionFrames.start();
            System.out.println("Executing program");
            boolean state;
            while (!connectionFrames.waitForAConnectionPressed) {
                state = connectionFrames.waitForAConnectionPressed;
            }
            ServerSocket pongServer = new ServerSocket(4502);
            System.out.println("Waiting for a connection...");
            pongServerSocket = pongServer.accept();
            System.out.println("Connection Accepted");
            PrintWriter out = new PrintWriter(pongServerSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(pongServerSocket.getInputStream()));
            SendServerData sendData = new SendServerData(out);
            ReceiveServerData inData = new ReceiveServerData(in);
            LoadMenu menuServer = new LoadMenu(connectionFrames.frameWindow, 0);
            menuServer.loadSelector();
            inData.start();
            while (!inData.startGame || !menuServer.startGameServer) ;
            sendData.start();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
}
