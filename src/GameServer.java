import com.sun.corba.se.spi.orbutil.fsm.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.HashSet;

/**
 * Created by mario on 7/09/14.
 */
public class GameServer extends JPanel implements KeyListener, ActionListener, Runnable {

    private int height, width;
    private Timer t = new Timer(5, this);
    private boolean first;

    private HashSet<String> keys = new HashSet<String>();

    // pad
    private final int SPEED = 1;
    private int padH = 10, padW = 40;
    private int bottomPadX, topPadX;
    private int inset = 10;

    // ball
    private double ballX, ballY, velX = 1, velY = 1, ballSize = 20;

    // score
    private int scoreTop, scoreBottom;

    //Buffers
    private PrintWriter serverOut;
    private BufferedReader serverIn;

    public GameServer(InputStream is, OutputStream os) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        serverOut = new PrintWriter(os);
        serverIn = new BufferedReader(new InputStreamReader(is));
        serverOut.flush();
        first = true;
        t.setInitialDelay(100);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        height = getHeight();
        width = getWidth();

        // initial positioning
        if (first) {
            bottomPadX = width / 2 - padW / 2;
            topPadX = bottomPadX;
            ballX = width / 2 - ballSize / 2;
            ballY = height / 2 - ballSize / 2;
            first = false;
        }

        // bottom pad
        Rectangle2D bottomPad = new Rectangle(bottomPadX, height - padH - inset, padW, padH);
        g2d.fill(bottomPad);

        // top pad
        Rectangle2D topPad = new Rectangle(topPadX, inset, padW, padH);
        g2d.fill(topPad);

        // ball
        Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, ballSize, ballSize);
        g2d.fill(ball);

        // scores
        String scoreB = "Bottom: " + new Integer(scoreBottom).toString();
        String scoreT = "Top: " + new Integer(scoreTop).toString();
        g2d.drawString(scoreB, 10, height / 2);
        g2d.drawString(scoreT, width - 50, height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // side walls
        if (ballX < 0 || ballX > width - ballSize) {
            velX = -velX;
        }
        // top / down walls
        if (ballY < 0) {
            velY = -velY;
            ++ scoreBottom;
        }

        if (ballY + ballSize > height) {
            velY = -velY;
            ++ scoreTop;
        }
        // bottom pad
        if (ballY + ballSize >= height - padH - inset && velY > 0)
            if (ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padW)
                velY = -velY;

        // top pad
        if (ballY <= padH + inset && velY < 0)
            if (ballX + ballSize >= topPadX && ballX <= topPadX + padW)
                velY = -velY;

        ballX += velX;
        ballY += velY;

        // pressed keys
        if (keys.size() == 1) {
            if (keys.contains("LEFT")) {
                topPadX -= (topPadX > 0) ? SPEED : 0;
                serverOut.println("01,"+topPadX);
                serverOut.flush();
            }
            else if (keys.contains("RIGHT")) {
                topPadX += (topPadX < width - padW) ? SPEED : 0;
                serverOut.println("01,"+topPadX);
                serverOut.flush();
            }
        }
        /*
        // AI
        double delta = ballX - topPadX;
        if (delta > 0) {
            topPadX += (topPadX < width - padW) ? SPEED : 0;
        }
        else if (delta < 0) {
            topPadX -= (topPadX > 0) ? SPEED : 0;
        }*/

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT:
                keys.add("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                keys.add("RIGHT");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT:
                keys.remove("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                keys.remove("RIGHT");
                break;
        }
    }

    public void run(){
        String[] dataFragments;
        String code;
        String ballX;
        String ballY;
        String padTop;
        String padBottom;
        String scoreL;
        String scoreR;
        String dataReceived;
        try {
            while(true){
                System.out.println("Executing run method");
                dataReceived = serverIn.readLine();
                System.out.println("Data Received is: " + dataReceived);
                dataFragments = dataReceived.split(",");
                code = dataFragments[0];
                if(dataFragments != null) {
                    if (code.equals("00")) {
                        System.out.println("Received Ball info");
                        ballX = dataFragments[1];
                        ballY = dataFragments[2];
                    } else if (code.equals("01") || code.equals("10")) {
                        if (code.equals("01")) {
                            padTop = dataFragments[1];
                            topPadX = Integer.parseInt(padTop);
                        } else if (code.equals("10")){
                            padBottom = dataFragments[1];
                            bottomPadX = Integer.parseInt(padBottom);
                        }
                    } else if (code.equals("11")) {
                        System.out.println("Received Score info");
                        scoreL = dataFragments[1];
                        scoreR = dataFragments[2];
                    }
                }else{
                    System.out.print("Error, bad formed data");
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Error, caused by: " + ioe);
            ioe.printStackTrace();
        }
    }
}