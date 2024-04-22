
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CoinGameView {


    protected JFrame coinGame;
    private JComboBox<String> userChoice;
    private JPanel betArea;
    private JPanel playerInfo;
    private JLabel playerName;
    private JLabel playerCurrency;
    private JButton betButton = new JButton("Bet");
    private JButton menu = new JButton("Menu");
    private JTextField betAmount = new JTextField(9);
    private String[] options = {"Heads","Tails"};


    CoinGameView(){

        coinGame = new JFrame();
        betArea = new JPanel();
        playerInfo = new JPanel();
        userChoice = new JComboBox<String>(options);
        playerCurrency = new JLabel();
        playerName = new JLabel();


        coinGame.add(betArea,BorderLayout.SOUTH);
        coinGame.add(playerInfo,BorderLayout.WEST);
        coinGame.add(menu, BorderLayout.EAST);


        betArea.add(betAmount);
        betArea.add(betButton);
        betArea.add(userChoice);


        playerInfo.add(playerName,FlowLayout.LEFT);
        playerInfo.add(playerCurrency,FlowLayout.CENTER);




        coinGame.setSize(400,400);
        coinGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes view and client connection

        coinGame.setVisible(false);

    }


    void setBetButtonListener(ActionListener aL){
        betButton.addActionListener(aL);
    }//listener for button

    void setMenuButtonListener(ActionListener aL) { menu.addActionListener(aL); }


    public void setPlayerName(String username){
        playerName.setText(username);
    }
    public String getPlayerName(){
        return  playerName.getText().toString();
    }
    public String getClientText(){//gets input text from user

        return betAmount.getText();
    }

    public String getBetAmount() {
        return betAmount.getText();
    }
    public void setBetAmount(String amount) {
        betAmount.setText(amount);
    }

    public String getUserChoice() {
        return userChoice.getSelectedItem().toString();
    }

    public void setPlayerCurrency(String updatedAmount){playerCurrency.setText(updatedAmount);}
    public String getPlayerCurrency(){
        return playerCurrency.getText().toString();
    }
}
