import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Ball extends Thread {
    private PrintWriter outBuffer;
    private int xPosition, yPosition;
    private String data, code;

    public Ball(PrintWriter outServer){
        this.outBuffer = outServer;
        xPosition = yPosition = 0;
        code = "00";
        data = code;
    }

    public void run(){
        try {
            outBuffer.println(data);
            Thread.sleep(500);
            outBuffer.flush();
        }catch(Exception e){
            System.out.println("Error, " + e);
        }
    }

    public void prepareData(int x, int y){
        data = code + "," + xPosition + "," + yPosition;
    }
}
