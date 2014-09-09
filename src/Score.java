import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class Score{
    private PrintWriter out;
    private String data, code;

    public Score(PrintWriter outServer){
        this.out = outServer;
        this.out.flush();
        code = "11";
        data = code+",0,0";
    }

    public void sendScore(int topScore, int bottomScore){
        try {
            data = code + "," + topScore + "," + bottomScore;
            out.println(data);
            out.flush();
        }catch(Exception e){
            System.out.println("Error, " + e);
            e.printStackTrace();
        }
    }
}
