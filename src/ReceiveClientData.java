import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Steven on 01/09/2014.
 */
public class ReceiveClientData extends Thread {

    private BufferedReader inClient;
    private String dataReceived;

    public ReceiveClientData(BufferedReader inFromServer){
        this.inClient = inFromServer;
        this.dataReceived = "";
    }

    public void run(){
        String[] dataFragments;
        String code;
        String ballX;
        String ballY;
        String racketL;
        String racketR;
        String scoreL;
        String scoreR;
        try {
            while(true){
                dataReceived = inClient.readLine();
                dataFragments = dataReceived.split(",");
                code = dataFragments[0];
                if(code.equals("00")){
                    System.out.println("Received Ball info");
                    ballX = dataFragments[1];
                    ballY = dataFragments[2];
                }else if(code.equals("01") || code.equals("11")){
                    System.out.println("Received Raquet info");
                    if(code.equals("01")){
                        racketL = dataFragments[1];
                    }else{
                        racketR = dataFragments[1];
                    }
                }else if(code.equals("10")){
                    System.out.println("Received Score info");
                    scoreL = dataFragments[1];
                    scoreR = dataFragments[2];
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Error, caused by: " + ioe);
            ioe.printStackTrace();
        }
    }
}
