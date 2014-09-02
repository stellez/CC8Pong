import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Racket extends Thread {
    private PrintWriter out;
    private int rPosition, lPosition;
    private String dataLeft, dataRight, codeLeft,codeRight, event;

    public Racket(){
        rPosition = lPosition = 0;
        codeLeft = "01";
        codeRight = "11";
        dataLeft = codeLeft;
        dataRight = codeRight;
    }

    public void prepareDataLeftRacket(int x, int y){
        dataLeft = codeLeft + "," + rPosition + "," + lPosition;
    }
    public void prepareDataRighRacket(int x, int y){
        dataRight = codeRight + "," + rPosition + "," + lPosition;
    }
}
