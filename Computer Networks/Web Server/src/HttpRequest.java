import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

final class HttpRequest implements Runnable {

    private final static String CRLF = "\r\n";
    private Socket socket;

    HttpRequest(Socket socket) throws Exception {
        this.socket = socket;
    }

    public void run() {

        try {
            processRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processRequest() throws Exception {

        InputStream inputStream = socket.getInputStream();
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        String requestLine = reader.readLine();

        System.out.println();
        System.out.println(requestLine);

        String headerLine;
        while ((headerLine = reader.readLine()).length() != 0) {
            System.out.println(headerLine);
        }

        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();
        String fileName = tokens.nextToken();

        fileName = "." + fileName;
        FileInputStream fInStream = null;
        boolean fileExists = true;
        try {
            fInStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            fileExists = false;
        }

        String statusLine;
        String contentTypeLine;
        String entityBody = null;
        if (fileExists) {

            statusLine = "HTTP/1.0 200 OK";
            contentTypeLine = "Content-type: " +
                    contentType(fileName) + CRLF;

        } else {

            statusLine = "HTTP/1.0 404 Not Found";
            contentTypeLine = "text/html" + CRLF;
            entityBody = "<HTML>" +
                    "<HEAD><TITLE>Not Found</TITLE></HEAD>" +
                    "<BODY>Not Found</BODY></HTML>";

        }
        outputStream.writeBytes(statusLine);
        outputStream.writeBytes(contentTypeLine);
        outputStream.writeBytes(CRLF);
        if (fileExists) {
            sendBytes(fInStream, outputStream);
            fInStream.close();
        } else {
            outputStream.writeBytes(entityBody);
        }

        outputStream.close();
        reader.close();
        socket.close();

    }

    private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception {

        byte[] buffer = new byte[1024];
        int bytes;

        while ((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }

    }

    private static String contentType(String fileName) {

        if (fileName.endsWith(".htm") || fileName.endsWith(".html")) {
            return "text/html";
        }
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (fileName.endsWith(".gif")) {
            return "image/gif";
        }
        return "application/octet-stream";

    }

}