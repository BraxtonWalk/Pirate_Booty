import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    //GameMenuView gameMenuView = new GameMenuView();

    private Model model = new Model();
    private CreateAccountView createAccountView = new CreateAccountView();
    private LoginAccountView loginAccountView = new LoginAccountView();
    private GameMenuView gameMenuView = new GameMenuView();



    Controller(){
        //action listeners for add and delete button
        //gameMenuView.setBetButtonListener(new AddMathActionListener());
        createAccountView.setCreateButtonActionListener(new createButtonActionListener());
        createAccountView.setLoginMenuButtonActionListener(new loginMenuButtonActionListener());
        loginAccountView.setLoginButtonActionListener(new loginButtonActionListener());
        loginAccountView.setCreateAccountMenuActionListener(new createAccountMenuActionListener());

    }
    /*class AddMathActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            double num1 = 0;//stores first num
            double num2 = 0;//stores second num
            int arraylength; // stores length of array
            String operation = null;
            //makes temp for input
            boolean errorCheck = true;
            boolean operationCheck = false;
            try {
                String s = gameMenuView.getClientText();
                String[] words = s.trim().split(" ");
                arraylength = words.length;
                if(arraylength != 3){
                    errorCheck = false;

                }else{
                    operation = words[1];
                    num1 = Integer.parseInt(words[0]);
                    num2 = Integer.parseInt(words[2]);
                }

                //checks for correct operation
                if(operation.equals("+") ||operation.equals("-") ||operation.equals("*") ||operation.equals("/") ||operation.equals("%")||operation.equals("^") ){
                    operationCheck = true;
                }
            } catch( NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException ex ){//catches error and updates errorAge
                errorCheck = false;
            }
            //checks to see if age or name is empty and if age has characters in it
           /* if( (gameMenuView.clientText.getText().isEmpty()) || !errorCheck || !operationCheck) {
                gameMenuView.serverText.setText("ERROR: BAD INPUT");
            }else {

                model.client(gameMenuView.getClientText());
                gameMenuView.serverText.setText(model.serverData());


            }



        }

    }*/


    class createButtonActionListener implements ActionListener { //create button action listener
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = createAccountView.getUsername().getText();
            String password = createAccountView.getPassword().getText();

            System.out.println("Username: " + username + " / Password: " + password);

            if(username.isEmpty() | password.isEmpty()){
                System.out.println("EMPTY TEXT FIELD");
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Must Enter Username or Password");
                createAccountView.getUsername().setText("");
                createAccountView.getPassword().setText("");
            }
            else{
                System.out.println("You entered a username and password");


                createAccountView.getUsername().setText("");
                createAccountView.getPassword().setText("");
            }
            //TODO make sure to check if the username is already in the database so that we prompt the user
            //TODO to create a new username since that one is already taken
        }

    }

    class createAccountMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginAccountView.getUsername().setText("");
            loginAccountView.getPassword().setText("");

            loginAccountView.loginAccount.setVisible(false);
            createAccountView.createAccount.setVisible(true);
        }
    }

    class loginMenuButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createAccountView.getUsername().setText("");
            createAccountView.getPassword().setText("");

            createAccountView.createAccount.setVisible(false);
            loginAccountView.loginAccount.setVisible(true);
        }

    }

    class loginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginAccountView.getUsername().getText();
            String password = loginAccountView.getPassword().getText();

            if(username.isEmpty() | password.isEmpty()){
                System.out.println("EMPTY TEXT FIELD");
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Must Enter Username or Password");
                loginAccountView.getUsername().setText("");
                loginAccountView.getPassword().setText("");
            }
            else{
                System.out.println("You entered a username and password");


                loginAccountView.getUsername().setText("");
                loginAccountView.getPassword().setText("");

            }
            //TODO same as the above comment for the create account
        }
    }


}
