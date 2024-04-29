
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Model {
    protected Socket chat;
    protected String serverMsg;
    String IP = "127.0.0.1";
    int port = 5000;

    Model(){
        try {
            String msg = String.format("Client> connecting to %s:%d", IP, port);
            System.out.println(msg);
            chat = new Socket(IP, port);
            System.out.println("client> success");
        }catch(IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Server couldn't connect, close app and try again");
        }
    }

    public String clientSignIn(String userName, String password){
        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String msg2 = String.format("Client> sent request to server: %s %s", userName, password);
            String clientInfo = String.format("%s %s signIn", userName, password);
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
            System.out.println("Client> server response: " + serverMsg);


        }catch (IOException | NullPointerException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Couldn't connect to database, close app and try again");
        }

        return serverMsg;
    }
    public String clientCreateAccount(String userName, String password){
        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String msg2 = String.format("Client> sent request to server: %s %s", userName, password);
            System.out.println(msg2);
            String clientInfo = String.format("%s %s createAccount", userName, password);
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
            System.out.println("Client> server response: " + serverMsg);



        }catch (IOException | NullPointerException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Couldn't connect to database, close app and try again");
            return null;
        }

        return serverMsg;
    }
    public String clientUpdateData(String userName,String amount){
        try{
            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String msg2 = String.format("Client> sent request to server: %s %s", userName, amount);
            System.out.println(msg2);
            String clientInfo = String.format("%s %s updateData", userName, amount);
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
            System.out.println("Client> server response: " + serverMsg);


        }catch (IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Error updating data, try again");
        }

        return serverMsg;
    }

    public String clientUpdateList(){
        try {

            PrintWriter writer = new PrintWriter(chat.getOutputStream());
            String msg2 = "Client> sent request to server: updateList";
            System.out.println(msg2);
            String clientInfo = "updateList";
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
            System.out.println("Client> server response: " + serverMsg);
        } catch(IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Error updating list, try again");
        }

        return serverMsg;
    }
}
