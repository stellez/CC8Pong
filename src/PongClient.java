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
        SendClientData sendClientData = new SendClientData(out);
        ReceiveClientData receiveClientData = new ReceiveClientData(in);
        receiveClientData.start();
        int i=0;
        try {
            while (true) {
                sendClientData.sleep(1000);
                sendClientData.sendData("11,"+ i++);
                //System.out.println("The data received is: " + dataRecived);
                //System.out.println("Information Received");
                System.out.println("Information was send to the server");
            }
        }catch(Exception e){
            System.err.println("Error, caused by: " + e);
        }
    }
}
