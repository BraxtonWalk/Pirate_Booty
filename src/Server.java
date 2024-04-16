
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.BufferUnderflowException;

public class Server {
    protected static Socket conn;
    protected static ServerSocket server;
    private static String username;

    public static void main(String[] args) throws IOException {

        try {
            server = new ServerSocket(5000); //creating the server
        } catch(IOException ex){
            ex.printStackTrace();
        }
        while(true) {
            try {
                System.out.println("server> waiting for client to connect...");
                conn = server.accept(); //waiting for client to connect
                System.out.println("server> connected to client " + conn);
            } catch (IOException e) {
                try {
                    conn.close();
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            while (true) {
                try {
                    System.out.println("server> waiting for client to send data...");

                    InputStreamReader stream = new InputStreamReader(conn.getInputStream()); //getting the input from the client
                    BufferedReader reader = new BufferedReader(stream);

                    String input = reader.readLine();
                    System.out.println("server> received: " + input);


                    PrintWriter writer = new PrintWriter(conn.getOutputStream()); //sending the total back to the client
                    writer.println(username);
                    System.out.println("server> sent response: " + username);

                    writer.flush();

                } catch (IOException e) {
                    try {
                        conn.close();
                        break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
