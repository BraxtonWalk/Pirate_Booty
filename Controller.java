import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Model model = new Model();
    private CreateAccountView createAccountView = new CreateAccountView();
    private LoginAccountView loginAccountView = new LoginAccountView();
    private GameView gameView = new GameView();
    private GameMenuView gameMenuView = new GameMenuView();
    private ScoreboardView scoreboardView = new ScoreboardView();
    private User user = new User();



    Controller(){
        //action listeners for add and delete button
        //gameMenuView.setBetButtonListener(new AddMathActionListener());
        createAccountView.setCreateButtonActionListener(new createButtonActionListener());
        createAccountView.setLoginMenuButtonActionListener(new loginMenuButtonActionListener());
        loginAccountView.setLoginButtonActionListener(new loginButtonActionListener());
        loginAccountView.setCreateAccountMenuActionListener(new createAccountMenuActionListener());
        gameMenuView.setExitActionListener(new exitActionListener());
        gameMenuView.setGameActionListener(new gameActionListener());
        gameMenuView.setScoreboardActionListener(new scoreboardActionListener());
        scoreboardView.setMenuActionListener(new menuActionListener());
        gameView.setMenuButtonListener(new menuActionListener_game());

    }


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

                gameView.setPlayerName(username + ": ");
                gameView.setCurrency("500");
                //TODO fix this above code to actually set the currency correctly (idk why it isn't working, maybe something with the flow layout in gameview?)




                createAccountView.createAccount.setVisible(false);
                gameMenuView.gameMenu.setLocationRelativeTo(createAccountView.createAccount);
                gameMenuView.gameMenu.setVisible(true);
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
            createAccountView.createAccount.setLocationRelativeTo(loginAccountView.loginAccount);
            createAccountView.createAccount.setVisible(true);
        }
    }

    class loginMenuButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createAccountView.getUsername().setText("");
            createAccountView.getPassword().setText("");

            createAccountView.createAccount.setVisible(false);
            loginAccountView.loginAccount.setLocationRelativeTo(createAccountView.createAccount);
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


                gameView.setPlayerName(username + ": ");
                //gameView.setCurrency(); TODO need to get the currency from the users account on the database (make a new variable to store it)



                loginAccountView.loginAccount.setVisible(false);
                gameMenuView.gameMenu.setLocationRelativeTo(loginAccountView.loginAccount);
                gameMenuView.gameMenu.setVisible(true);
                loginAccountView.getUsername().setText("");
                loginAccountView.getPassword().setText("");

            }
            //TODO same as the above comment for the create account
        }
    }


    //TODO implement the functionality of these 3 functions
    class exitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            System.exit(0);
        }

    }

    class gameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            gameView.pirateGame.setLocationRelativeTo(gameMenuView.gameMenu);
            gameView.pirateGame.setVisible(true);
        }

    }

    class scoreboardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            scoreboardView.scoreboard.setLocationRelativeTo(gameMenuView.gameMenu);
            scoreboardView.scoreboard.setVisible(true);
        }

    }

    class menuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreboardView.scoreboard.setVisible(false);
            gameMenuView.gameMenu.setLocationRelativeTo(scoreboardView.scoreboard);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class menuActionListener_game implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameView.pirateGame.setVisible(false);
            gameMenuView.gameMenu.setLocationRelativeTo(gameView.pirateGame);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

}
