import view.ConnectionFrames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Steven on 31/08/2014.
 */
public class PongClient {
    public static void main(String args[]) throws IOException {
        ConnectionFrames connectionFrames = new ConnectionFrames();
        connectionFrames.start();
        while(!connectionFrames.makeConnectionPressed){
            System.out.println(connectionFrames.makeConnectionPressed);
        }
        Socket pongClientSocket = new Socket(connectionFrames.ipAddressFromJTextfield, 4502);
        System.out.println("Connection Accepted");
        PrintWriter out = new PrintWriter(pongClientSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(pongClientSocket.getInputStream()));
        SendClientData sendClientData = new SendClientData(out);
        ReceiveClientData receiveClientData = new ReceiveClientData(in);
        LoadMenu menuClient = new LoadMenu(connectionFrames.frameWindow, 1);
        menuClient.loadSelector();
        menuClient.setSenderClient(sendClientData);
        receiveClientData.start();

    }
}
