import view.ConnectionFrames;

import javax.swing.*;
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
        ConnectionFrames connectionFrames = new ConnectionFrames();
        connectionFrames.createServerWindow();
        ServerSocket pongServer = new ServerSocket(4502);
        System.out.println("Waiting for a connection...");
        Socket pongServerSocket = pongServer.accept();
        System.out.println("Connection Accepted");
        JFrame frm = connectionFrames.getWindow();
        frm.dispose();
        frm = new JFrame();
        frm.setTitle("Player 1");
        GameServer gs = new GameServer(pongServerSocket.getInputStream(), pongServerSocket.getOutputStream());
        frm.setContentPane(gs);
        frm.setSize(300, 700);
        frm.setResizable(false);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread serverReceiver = new Thread(gs);
        serverReceiver.start();
    }
}
