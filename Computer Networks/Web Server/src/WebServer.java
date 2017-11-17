import java.net.*;

public final class WebServer {

    public static void main(String args[]) throws Exception {

        ServerSocket listener = new ServerSocket(6789);
        while (true) {

            Socket newSocket = listener.accept();
            HttpRequest request = new HttpRequest(newSocket);
            Thread thread = new Thread(request);
            thread.start();

        }

    }

}
