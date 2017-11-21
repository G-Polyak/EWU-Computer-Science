import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Server to process ping requests over UDP.
 */
public class PingClient {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Required arguments: host, port");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        DatagramSocket socket = new DatagramSocket(2001);
        socket.setSoTimeout(1000);

        int i = 0;
        long time = 0;
        while (i < 10) {

            String s = "PING " + i + " " + time + "ms \r\n";
            byte[] data = s.getBytes();
            DatagramPacket request = new DatagramPacket(data, data.length);
            request.setAddress(InetAddress.getLoopbackAddress()); //getByName(host) and
            request.setPort(port);                                //getByAddress(host.getBytes())
                                                                  //weren't working :(
            time = System.currentTimeMillis();
            socket.send(request);

            DatagramPacket serverReply = new DatagramPacket(new byte[1024], 1024);
            try {
                socket.receive(serverReply);
                printData(serverReply);
            } catch (Exception e) {
                System.out.println("Packet lost");
            }
            time = System.currentTimeMillis() - time;
            i++;

        }
    }

    /*
     * Print ping data to the standard output stream.
     */
    private static void printData(DatagramPacket request) throws Exception {
        // Obtain references to the packet's array of bytes.
        byte[] buf = request.getData();

        // Wrap the bytes in a byte array input stream,
        // so that you can read the data as a stream of bytes.
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);

        // Wrap the byte array output stream in an input stream reader,
        // so you can read the data as a stream of characters.
        InputStreamReader isr = new InputStreamReader(bais);

        // Wrap the input stream reader in a bufferred reader,
        // so you can read the character data a line at a time.
        // (A line is a sequence of chars terminated by any combination of \r and \n.)
        BufferedReader br = new BufferedReader(isr);

        // The message data is contained in a single line, so read this line.
        String line = br.readLine();

        // Print host address and data received from it.
        System.out.println(
                "Received from " +
                        request.getAddress().getHostAddress() +
                        ": " +
                        line);
    }
}