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
        Socket pongServerSocket;
        ConnectionFrames connectionFrames = new ConnectionFrames();
        connectionFrames.start();
        while(!connectionFrames.waitForAConnectionPressed){
            System.out.println(connectionFrames.waitForAConnectionPressed);
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
        menuServer.setReceiveServer(inData);
        inData.start();
        menuServer.setSenderServer(sendData);
        while(!inData.startGame || !menuServer.startGameServer){
            System.out.println(inData.startGame);
            try {
                connectionFrames.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sendData.start();

    }
}
