import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Racket {
    private PrintWriter out;
    private int rPosition, lPosition;
    private String data, code;

    public Racket(PrintWriter outServer){
        this.out = outServer;
        rPosition = lPosition = 0;
        code = "001";
        data = code;
    }

    public void run(){
        try {
            out.println(data);
        }catch(Exception e){
            System.out.println("Error, " + e);
        }
    }

    public void prepareData(int x, int y){
        data = code + "," + rPosition + "," + lPosition;
    }
}
