
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

public class Model {
    protected Socket chat;
    protected String serverMsg;
    String IP = "127.0.0.1";//ip adress
    int port = 5000; // port number
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
            //System.out.println(msg2);
            String clientInfo = String.format("%s %s signIn", userName, password);
            writer.println(clientInfo);
            writer.flush();


            InputStreamReader stream = new InputStreamReader(chat.getInputStream());
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
            System.out.println("Client> server response: " + serverMsg);
            //TODO read servers response and then decide of user can login or tries again meow



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
            e.printStackTrace();
        }

        return serverMsg;
    }

    public String clientUpdateList(){
        return null; //TODO need to add code to this so we can send the data into the server and have the server return the top 3
                     //TODO people with the highest currency in the database (server side sorts the data in descending order currently)
    }

    public String serverData(){

        return serverMsg;
    }

}
