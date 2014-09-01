import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Ball extends Thread {
    private PrintWriter out;
    private int xPosition, yPosition;
    private String data, code;

    public Ball(PrintWriter outServer){
        this.out = outServer;
        xPosition = yPosition = 0;
        code = "00";
        data = code;
    }

    public void run(){
        try {
            if(out.checkError()){
                System.out.println("Im doing fine.");
            }
            out.println(data);
            Thread.sleep(500);
        }catch(Exception e){
            System.out.println("Error, " + e);
        }
    }

    public void prepareData(int x, int y){
        data = code + "," + xPosition + "," + yPosition;
    }
}
