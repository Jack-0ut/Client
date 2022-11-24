import java.io.*;
import java.net.Socket;
/**
 * Class that connect client to server
 * and read your messages to send them
 * on server
 **/
public class ConnectInputMessage implements Runnable{
    private Socket serverConnect;
    private InputStream inputStreamServer;

    public InputStream getInputStreamServer() {
        return inputStreamServer;
    }

    public ConnectInputMessage() {
        try {
            serverConnect = new Socket("localhost",8887);
            inputStreamServer = serverConnect.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Can't connect to server!");
        }
    }

    @Override
    public void run() {
        // Read message from server
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStreamServer));
        String serverMessage;
        while (true){
            try {
                serverMessage = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read message from server");
            }
            if(serverMessage != null){
                // print client number to show that connection is set
                System.out.println(serverMessage +"\n");
                break;
            }
        }
        // Read message from user
        PrintWriter out;
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        String userMessage;
        while (true){
            System.out.println("Enter message:");
                // read client message from console
            try {
                userMessage = inputUser.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // send message to server
            try {
                out = new PrintWriter(serverConnect.getOutputStream(),true);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            out.println(userMessage);
            // if user enter 'exit' end loop and close thread
            if("exit".equals(userMessage)){
                break;
            }
        }
    }
}
