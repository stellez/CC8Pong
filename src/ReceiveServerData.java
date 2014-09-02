import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by mario on 1/09/14.
 */
public class ReceiveServerData extends Thread {
    BufferedReader infromClient;
    String racketY;

    public ReceiveServerData(BufferedReader in){
         this.infromClient = new BufferedReader(in);
    }
    public void run(){
        try {
            while(true) {
                racketY = infromClient.readLine();
                System.out.println("Reciviendo en el receiver: " + racketY);
                String[] data = racketY.split(",");
                String posRacket = data[1];
                String code = data[0];
                if(code.equals("01")){
                    System.out.println("Received left racket");
                }else if(code.equals("11")){
                    System.out.println("Received right racket");
                }

            }
        } catch (IOException ioe) {
            System.out.println("ERROR: Caused by: " + ioe );
        }
    }





}