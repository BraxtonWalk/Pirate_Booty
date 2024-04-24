
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller {

    private Model model = new Model();
    private CreateAccountView createAccountView = new CreateAccountView();
    private LoginAccountView loginAccountView = new LoginAccountView();
    private CoinGameView coinGameView = new CoinGameView();
    private DiceGameView diceGameView = new DiceGameView();
    private GameMenuView gameMenuView = new GameMenuView();
    private ScoreboardView scoreboardView = new ScoreboardView();




    Controller(){
        //action listeners for add and delete button
        //gameMenuView.setBetButtonListener(new AddMathActionListener());
        createAccountView.setCreateButtonActionListener(new createButtonActionListener());
        createAccountView.setLoginMenuButtonActionListener(new loginMenuButtonActionListener());
        loginAccountView.setLoginButtonActionListener(new loginButtonActionListener());
        loginAccountView.setCreateAccountMenuActionListener(new createAccountMenuActionListener());
        gameMenuView.setExitActionListener(new exitActionListener());
        gameMenuView.setCoinGameActionListener(new coinGameActionListener());
        gameMenuView.setDiceGameActionListener(new diceGameActionListener());
        gameMenuView.setScoreboardActionListener(new scoreboardActionListener());
        scoreboardView.setMenuActionListener(new menuActionListener_scoreboard());
        coinGameView.setMenuButtonListener(new menuActionListener_coinGame());
        coinGameView.setBetButtonListener(new betButtonActionListener_coinGame());
        diceGameView.setMenuButtonListener(new menuActionListener_diceGame());
        diceGameView.setBetButtonListener(new betButtonActionListener_diceGame());

    }


    class createButtonActionListener implements ActionListener { //create button action listener
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = createAccountView.getUsername().getText();
            String password = createAccountView.getPassword().getText();


            if(username.isEmpty() | password.isEmpty()){
                System.out.println("EMPTY TEXT FIELD");
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Must Enter Username or Password");
                createAccountView.getUsername().setText("");
                createAccountView.getPassword().setText("");
            }
            else{

                coinGameView.setPlayerName(username);
                coinGameView.setPlayerCurrency("500");
                diceGameView.setPlayerName(username);
                diceGameView.setPlayerCurrency("500");
                String serverAccept = model.clientCreateAccount(username,password);
                if(serverAccept != null){
                    if(serverAccept.equals("AccountExist")){
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Username already exists, please login or change username");
                        createAccountView.getUsername().setText("");
                        createAccountView.getPassword().setText("");
                    }
                    else {
                        createAccountView.createAccount.setVisible(false);
                        gameMenuView.gameMenu.setLocationRelativeTo(createAccountView.createAccount);
                        gameMenuView.gameMenu.setVisible(true);
                        createAccountView.getUsername().setText("");
                        createAccountView.getPassword().setText("");
                    }
                }
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
                try {
                    coinGameView.setPlayerName(username);
                    diceGameView.setPlayerName(username);
                    String serverAccept = model.clientSignIn(username, password);
                    String[] words = serverAccept.split(" ");
                    String playerCurrency = words[2];
                    System.out.println(serverAccept);
                    System.out.println(words[1]);
                    coinGameView.setPlayerCurrency(playerCurrency);
                    diceGameView.setPlayerCurrency(playerCurrency);


                    loginAccountView.loginAccount.setVisible(false);
                    gameMenuView.gameMenu.setLocationRelativeTo(loginAccountView.loginAccount);
                    gameMenuView.gameMenu.setVisible(true);
                    loginAccountView.getUsername().setText("");
                    loginAccountView.getPassword().setText("");
                } catch (NullPointerException ex){

                }

            }
            //TODO same as the above comment for the create account
        }
    }


    class exitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            System.exit(0);
        }

    }

    class coinGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            coinGameView.coinGame.setLocationRelativeTo(gameMenuView.gameMenu);
            coinGameView.coinGame.setVisible(true);
        }

    }

    class diceGameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            diceGameView.diceGame.setLocationRelativeTo(gameMenuView.gameMenu);
            diceGameView.diceGame.setVisible(true);
        }
    }

    class scoreboardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameMenuView.gameMenu.setVisible(false);
            scoreboardView.scoreboard.setLocationRelativeTo(gameMenuView.gameMenu);

            String fullList = model.clientUpdateList();

            List<String> myList = List.of(fullList.split(","));
            DefaultListModel<String> top3Model = new DefaultListModel<>();

            for (int i = 0; i < Math.min(3, myList.size()); i++) {
                top3Model.addElement(myList.get(i));
            }


            JList<String> list = new JList<>(top3Model);

            ListModel<String> model = list.getModel();
            int size = model.getSize();
            for (int i = 0; i < size; i++) {
                System.out.println("Element at index " + i + ": " + model.getElementAt(i));
            }

            scoreboardView.setTop3(list);

            scoreboardView.scoreboard.setVisible(true);
        }

    }

    class menuActionListener_scoreboard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            scoreboardView.scoreboard.setVisible(false);
            gameMenuView.gameMenu.setLocationRelativeTo(scoreboardView.scoreboard);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class menuActionListener_coinGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            coinGameView.setBetAmount("");
            coinGameView.coinGame.setVisible(false);
            gameMenuView.gameMenu.setLocationRelativeTo(coinGameView.coinGame);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class betButtonActionListener_coinGame implements ActionListener { //function for sending the data to model after bet button press
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = coinGameView.getPlayerName();
            String currency = coinGameView.getPlayerCurrency();
            String betAmount = coinGameView.getBetAmount();
            String serverAccept = null;

            String choice = coinGameView.getUserChoice().toString();
            String headsTails = null;
            Boolean win = false;

            if(Integer.parseInt(currency) <= 0){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Out of money, create new account!");
                coinGameView.setBetAmount("");
            }
            else if(Integer.parseInt(betAmount) > Integer.parseInt(currency)){
                JFrame error = new JFrame();
                JOptionPane.showMessageDialog(error, "Bet amount too large!");
                coinGameView.setBetAmount("");
            }
            else {

                double random = Math.random();
                if (random < .5) {
                    headsTails = "Heads";
                } else {
                    headsTails = "Tails";
                }

                if (choice.equals(headsTails)) {
                    win = true;
                    try {
                        int winAmount = Integer.parseInt(currency) + (Integer.parseInt(betAmount)); //maybe make this * 2
                        serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                    } catch (NumberFormatException ex) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                        coinGameView.setBetAmount("");
                    }
                } else {
                    try {
                        int winAmount = Integer.parseInt(currency) - Integer.parseInt(betAmount);
                        serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                    } catch (NumberFormatException ex) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                        coinGameView.setBetAmount("");
                    }

                }
                if(win.equals(true) && headsTails.equals("Heads")){
                    coinGameView.setStatus("winHeads");
                }
                else if(win.equals(true) && headsTails.equals("Tails")){
                    coinGameView.setStatus("winTails");
                }
                else if(win.equals(false) && headsTails.equals("Heads")){
                    coinGameView.setStatus("lossHeads");
                }
                else if(win.equals(false) && headsTails.equals("Tails")){
                    coinGameView.setStatus("lossTails");
                }

                try {
                    String[] words = serverAccept.split(" ");
                    String updatedCurrency = words[2];
                    coinGameView.setPlayerCurrency(updatedCurrency);
                    diceGameView.setPlayerCurrency(updatedCurrency);
                } catch (NullPointerException ex) {

                }
            }

        }
    }

    class menuActionListener_diceGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            diceGameView.setBetAmount("");
            diceGameView.diceGame.setVisible(false);
            gameMenuView.gameMenu.setLocationRelativeTo(diceGameView.diceGame);
            gameMenuView.gameMenu.setVisible(true);
        }
    }

    class betButtonActionListener_diceGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = diceGameView.getPlayerName();
            String currency = diceGameView.getPlayerCurrency();
            String betAmount = diceGameView.getBetAmount();
            String serverAccept = null;

            String choice = diceGameView.getUserChoice().toString();
            System.out.println("choice: " + choice);

            Boolean win = false;
            Random rand = new Random();
            Integer number = (rand.nextInt(6) + 1); //random # between 1 and 6 (dice roll)
            System.out.println(number);

            if(choice.equals(number.toString())){
                win = true;
                System.out.println("WIN");
                try {
                    int winAmount = Integer.parseInt(currency) + (Integer.parseInt(betAmount) * 6);
                    serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                } catch (NumberFormatException ex){
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                    diceGameView.setBetAmount("");
                }
            }else{
                System.out.println("LOSE");
                try {
                    int winAmount = Integer.parseInt(currency) - Integer.parseInt(betAmount);
                    serverAccept = model.clientUpdateData(username, String.valueOf(winAmount));
                } catch (NumberFormatException ex){
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error, "Incorrect bet amount, try again");
                    diceGameView.setBetAmount("");
                }

            }
            try {
                String[] words = serverAccept.split(" ");
                String updatedCurrency = words[2];
                diceGameView.setPlayerCurrency(updatedCurrency);
                coinGameView.setPlayerCurrency(updatedCurrency);
            } catch (NullPointerException ex){

            }
        }
    }
}
