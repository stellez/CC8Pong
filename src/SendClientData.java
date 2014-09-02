import java.io.PrintWriter;

/**
 * Created by Steven on 31/08/2014.
 */
public class SendClientData extends Thread{

    private PrintWriter outClient;
    private static String data;

    public SendClientData(PrintWriter outToServer){
        this.outClient = outToServer;
        data = "";
    }

    public void run(){
        outClient.println(data);
        outClient.flush();
    }

    public void sendData(String dataToSend){
        data = dataToSend;
        this.run();
    }
}
