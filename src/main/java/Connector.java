import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector {

    private Socket netSocket;

    /*
    Sets up connection with server.
     */
    public boolean establishConnection() {
        boolean trueFalse;

        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            netSocket = new Socket(hostName, 4242);
            System.out.println("Connection Successful");
            trueFalse = true;
        } catch (UnknownHostException ex) {
            System.out.println(ex);
            System.exit(0);
            trueFalse = false;
        } catch (IOException ex) {
            trueFalse = false;
        }
        return trueFalse;
    }

    public Socket getNetSocket() {
        return netSocket;
    }
}

