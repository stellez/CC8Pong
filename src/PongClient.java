import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Steven on 31/08/2014.
 */
public class PongClient {
    public static void main(String args[]) throws IOException {
        Socket pongClientSocket = new Socket("localhost", 4502);
        System.out.println("Connection Accepted");
        PrintWriter out = new PrintWriter(pongClientSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(pongClientSocket.getInputStream()));
        String dataRecived;
        while((dataRecived = in.readLine()) != null){
            if(dataRecived.equals("00")){
                System.out.println("Ball Information Received");
            }else if(dataRecived.equals("01")){
                System.out.println("Racket Information Received");
            }else if(dataRecived.equals("10")){
                System.out.println("Score Information Received");
            }
            //System.out.println("The data received is: " + dataRecived);
            //System.out.println("Information Received");
        }
    }
}
