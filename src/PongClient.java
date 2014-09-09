import view.ConnectionFrames;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Steven on 31/08/2014.
 */
public class PongClient {
    public static void main(String args[]) throws IOException, InterruptedException {
        ConnectionFrames connectionFrames = new ConnectionFrames();
        connectionFrames.createClientWindow();
        while(connectionFrames.getIPAddress().equals("N/A")){
            System.out.println();
            Thread.sleep(1000);
        };
        try {
            Socket pongClientSocket = new Socket(connectionFrames.getIPAddress(), 4502);
            System.out.println("Connection Accepted");
            JFrame frm = connectionFrames.getWindow();
            frm.dispose();
            frm = new JFrame();
            frm.setTitle("Player 2");
            GameClient g = new GameClient(pongClientSocket.getInputStream(), pongClientSocket.getOutputStream());
            frm.setContentPane(g);
            frm.setSize(300, 700);
            frm.setResizable(false);
            frm.setVisible(true);
            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Thread clientReceiver = new Thread(g);
            clientReceiver.start();
        } catch (IOException ioe) {
            System.out.println("Error making the connection, not found a server aplication");
        }
    }
}
