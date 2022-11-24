import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * Class that receive client message
 * and print it in console
 **/
public class ReceiveMessageFromServer implements Runnable{
    private InputStream inputStreamServer;

    public ReceiveMessageFromServer(InputStream inputStreamServer) {
        this.inputStreamServer = inputStreamServer;
    }

    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage;
        while (true){
            try {
                serverMessage = in.readLine();
                if(serverMessage != null){
                    System.out.println("\n" + serverMessage + "\nEnter message:");
                }
            } catch (IOException e) {
                System.out.println("Can't read client message!");
            }
        }

    }
}
