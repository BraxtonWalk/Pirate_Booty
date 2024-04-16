import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    protected Socket socket;
    protected String serverMsg;

    Model(){
        try {
            System.out.println("client> connecting to 127.0.0.1:5000...");
            socket = new Socket("127.0.0.1", 5000); //connecting to the port on the server
            System.out.println("client> success!");

            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:CoinFlipUsers.db"); //connecting to the database
                System.out.println("Connected to Database");

                String cmd = "CREATE TABLE IF NOT EXISTS users (" +  //creating a table for storing the data
                        "username String PRIMARY KEY," +
                        "password STRING," +
                        "currency INTEGER);";

                conn.createStatement().executeUpdate(cmd); //executing the create table command
                conn.close();

            } catch (SQLException | NumberFormatException | NullPointerException e) { //catching exceptions
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Error Connecting to Database");
            }

        }catch(IOException e){
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Error Connecting to Server");
        }

    }

    public void sendData(String input){ //client function to send data to server

        try {
            System.out.println("sent request to server: " + input);
            PrintWriter writer = new PrintWriter(socket.getOutputStream()); //writing the data to the server
            writer.println(input);
            writer.flush(); //forcing the data to send immediately
        }
        catch(IOException exception){
            exception.printStackTrace();
        }

    }

    public String receiveData(){ //client function to receive data from server

        try {
            InputStreamReader stream = new InputStreamReader(socket.getInputStream()); //Receiving input back from server
            BufferedReader reader = new BufferedReader(stream);

            serverMsg = reader.readLine();
            System.out.println("client> server response: " + serverMsg);
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
        return serverMsg;
    }

}