package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mario on 26/08/14.
 */
public class ConnectionFrames extends Thread {

    private static String ipAddressFromJTextfield ="N/A";
    private JFrame window;

    public void createClientWindow(){
        try {
            window = new JFrame("Client");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            final JTextField ipAddressJTextfield = new JTextField();
            JButton connectTo = new JButton("Connect");
            viewPanel.add(new JLabel("IP Adress: "));
            viewPanel.add(ipAddressJTextfield);
            viewPanel.add(connectTo);
            window.setResizable(false);
            window.add(viewPanel);
            window.pack();
            window.setSize(250,250);
            window.setVisible(true);
            connectTo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ipAddressFromJTextfield = ipAddressJTextfield.getText();
                    if(ipAddressFromJTextfield.isEmpty()){
                        ipAddressFromJTextfield = "localhost";
                    }
                    System.out.println(ipAddressFromJTextfield);
                }
            });

        }catch(HeadlessException hle){
            System.err.println("Error caused by: " + hle);
        }catch(Exception e){
            System.err.println("Error caused by: " + e);
        }
    }

    public void createServerWindow(){
        try {
            window = new JFrame("Server");
            JPanel panel= new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel title = new JLabel("Waiting for a connection...");
            panel.add(title);
            window.add(panel);
            window.pack();
            window.setSize(250, 250);
            window.setVisible(true);
        }catch(Exception e){
            System.err.println("Error caused by: " + e);
            e.printStackTrace();
        }
    }

    public String getIPAddress(){
        return ipAddressFromJTextfield;
    }

    public JFrame getWindow(){
        return window;
    }
}
