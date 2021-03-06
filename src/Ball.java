import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Ball extends Thread {
    private PrintWriter outBuffer;
    private double xPosition, yPosition;
    private String data, code;

    public Ball(PrintWriter outServer){
        this.outBuffer = outServer;
       this.outBuffer.flush();
        xPosition = yPosition = 0;
        code = "00";
        data = code+",0,"+"0";
    }

    public void run(){
        try {
            while(true) {
                outBuffer.println(data);
                outBuffer.flush();
                Thread.sleep(100);
            }
        }catch(Exception e){
            System.out.println("Error, " + e);
        }
    }

    public void prepareData(double x, double y){
        xPosition = x;
        yPosition = y;
        data = code + "," + xPosition + "," + yPosition;
    }
}
