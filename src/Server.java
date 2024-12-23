
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.BufferUnderflowException;
import java.sql.*;

public class Server {
    protected static Socket conn;
    public static void main(String[] args) throws IOException {
        try {
            String IP = "127.0.0.1";//ip adress
            int port = 5000; // port number

            String serverMessage = "";
            String msg = String.format("server> connected to client Socket[addr=%s,port=%d, localport = %d", IP, 59893, port);
            ServerSocket server = new ServerSocket(5000);

            while(true) {

                System.out.println("Server> waiting for client to connect...");
                conn = server.accept();
                //TODO implement threads right here


                System.out.println(msg);
                InputStreamReader stream = new InputStreamReader(conn.getInputStream());
                BufferedReader reader = new BufferedReader(stream);
                PrintWriter writer = new PrintWriter(conn.getOutputStream());
                try {
                    while (true) {
                        System.out.println("server> waiting for client to send data");


                        String clientMSG = reader.readLine();
                        System.out.println("server> received: " + clientMSG);


                        String[] words = clientMSG.trim().split(" ");
                        String userName = null;
                        String passwordOrCurrency = null;
                        String userAction = null;
                        if(words.length == 3) {
                            userName = words[0];
                            passwordOrCurrency = words[1];
                            userAction = words[2];
                        }
                        else{
                            userAction = words[0];
                        }

                        String cmd = null;

                        try {
                            Connection connection  = null;
                            String uri = "jdbc:sqlite:CoinFlipUsers.db";
                            connection = DriverManager.getConnection(uri);
                            System.out.println("in da data");
                            Statement stm = connection.createStatement();


                            if(userAction.equals("signIn")) {
                                connection = DriverManager.getConnection(uri);
                                cmd = "SELECT userName, password, currency FROM users;";
                                ResultSet rs = connection.createStatement().executeQuery(cmd);
                                while (rs.next()) {

                                    String userNameDataBase = rs.getString("userName");
                                    String passwordDataBase = rs.getString("password");
                                    String userCurrencyDataBase = rs.getString("currency");
                                    if (userName.equals(userNameDataBase) && passwordOrCurrency.equals(passwordDataBase)) {

                                        serverMessage = String.format("%s %s %s", userName, passwordOrCurrency,userCurrencyDataBase);

                                        break;
                                    }
                                    //TODO create a statement where login failed
                                }
                                stm.execute(cmd);


                            }else if(userAction.equals("createAccount")){
                                connection = DriverManager.getConnection(uri);
                                String searchCMD = "SELECT userName FROM users;";
                                ResultSet rs = connection.createStatement().executeQuery(searchCMD);
                                while (rs.next()) {

                                    String userNameDataBase = rs.getString("userName");

                                    if (userName.equals(userNameDataBase)) {

                                        serverMessage = "AccountExist";

                                        break;
                                    }


                                }

                                if(!serverMessage.equals("AccountExist")){

                                    cmd = "INSERT INTO users (userName, password, currency) VALUES (?,?,?);";
                                    PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                                    preparedStatement.setString(1, userName);
                                    preparedStatement.setString(2, passwordOrCurrency);
                                    preparedStatement.setString(3, "500");
                                    preparedStatement.executeUpdate();
                                    serverMessage = String.format("%s %s 500", userName, passwordOrCurrency);
                                }


                            }else if(userAction.equals("updateData")){

                                connection = DriverManager.getConnection(uri);
                                cmd = "UPDATE users SET currency = ? WHERE userName = ?;";
                                PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                                preparedStatement.setString(1,passwordOrCurrency);
                                preparedStatement.setString(2,userName);

                                preparedStatement.executeUpdate();
                                serverMessage = String.format("%s meow %s", userName, passwordOrCurrency);

                            }
                            else if(userAction.equals("updateList")){

                                connection = DriverManager.getConnection(uri);
                                System.out.println("IN LIST CHANGER!!!!");

                                cmd = "SELECT userName, currency FROM users ORDER BY currency DESC;";
                                ResultSet rs = connection.createStatement().executeQuery(cmd);
                                String users = "";
                                while (rs.next()) {

                                    String userNameDataBase = rs.getString("userName");
                                    String currencyDataBase = rs.getString("currency");
                                    System.out.println(userNameDataBase);
                                    users += (userNameDataBase + " " + currencyDataBase + ",");
                                }
                                serverMessage = users;
                                stm.execute(cmd);

                            }
                            connection.close();

                        }catch(SQLException | NullPointerException ex){
                            ex.printStackTrace();
                        }

                        System.out.println("server> sent response: " + serverMessage );

                        PrintWriter printWriter = new PrintWriter(conn.getOutputStream());


                        printWriter.println(serverMessage);
                        printWriter.flush();
                    }
                }catch(IOException e){
                    conn.close();
                }

            }

        }catch(IOException e) {
            System.out.println("Server> waiting for client to connect...");
        }


    }
}
