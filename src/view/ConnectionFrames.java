package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mario on 26/08/14.
 */
public class ConnectionFrames extends Thread {

    public static String ipAddressFromJTextfield;
    public static boolean waitForAConnectionPressed;
    public static boolean makeConnectionPressed;
    public JFrame frameWindow;

    private void createConnectionWindow(){
        try {
            frameWindow = new JFrame("Pong");
            frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final JPanel viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("Pong Connection");
            JButton connectTo = new JButton("Connect to");
            final JButton waitForAConnection = new JButton("Wait for a connection");
            frameWindow.setResizable(false);

            viewPanel.add(title);
            viewPanel.add(connectTo);
            viewPanel.add(waitForAConnection);
            frameWindow.add(viewPanel);
            frameWindow.pack();
            frameWindow.setSize(250,250);
            frameWindow.setVisible(true);
            connectTo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    createConnectToWindow(frameWindow, viewPanel);
                }
            });
            waitForAConnection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    createWaitingForAConnectionWindow(frameWindow, viewPanel);
                    waitForAConnectionPressed = true;
                        /*LoadMenu loadMenuServer = new LoadMenu(frameWindow, 0);
                        try {
                            loadMenuServer.loadStartMenu();
                            loadMenuServer.loadSelector();
                        } catch (Exception e) {
                            System.out.println("Error caused by: " + e);
                            e.printStackTrace();
                        }*/
                    }
            });
        }catch(HeadlessException hle){
            System.err.println("Error caused by: " + hle);
        }catch(Exception e){
            System.err.println("Error caused by: " + e);
        }
    }

    private void createConnectToWindow(final JFrame jframe, JPanel panel){
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Insert the connection fields");
        final JTextField ipAddressJTextfield = new JTextField();
        JButton makeConnection = new JButton("Connect");
        makeConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ipAddressFromJTextfield = ipAddressJTextfield.getText();
                if (ipAddressFromJTextfield == null){
                    ipAddressFromJTextfield = "localhost";
                }
                makeConnectionPressed = true;
                /*LoadMenu loadMenuClient = new LoadMenu(jframe, 1);
                try {
                    loadMenuClient.loadStartMenu();
                    loadMenuClient.loadSelector();
                } catch (Exception e) {
                    System.err.println("Error caused by: " + e);
                }*/
            }
        });
        panel.add(title);
        panel.add(new JLabel("IP Adress: "));
        panel.add(ipAddressJTextfield);
        panel.add(makeConnection);
        jframe.add(panel);
        jframe.pack();
        jframe.setSize(250,250);
        //Show it.
        jframe.setVisible(true);
    }

    private void createWaitingForAConnectionWindow(JFrame frameWindow, JPanel panel){
        try {
            panel.removeAll();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("Waiting for a connection...");
            panel.add(title);
            frameWindow.add(panel);
            frameWindow.pack();
            frameWindow.setSize(250, 250);
            frameWindow.setVisible(true);
        }catch(Exception e){
            System.err.println("Error caused by: " + e);
            e.printStackTrace();
        }
    }

    public void run(){
        createConnectionWindow();
    }
}
