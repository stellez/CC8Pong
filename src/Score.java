import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Score {
    private PrintWriter out;
    private int scoreP1, scoreP2;
    private String data, code;

    public Score(PrintWriter outServer){
        this.out = outServer;
        scoreP1 = scoreP2 = 0;
        code = "010";
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
        data = code + "," + scoreP1 + "," + scoreP2;
    }
}
