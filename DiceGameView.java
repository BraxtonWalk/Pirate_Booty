import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DiceGameView {

    protected JFrame diceGame;
    private JComboBox<String> userChoice;
    private JPanel betArea;
    private JPanel playerInfo;
    private JLabel playerName;
    private JLabel playerCurrency;
    private JButton betButton = new JButton("Bet");
    private JButton menu = new JButton("Menu");
    private JTextField betAmount = new JTextField(9);
    private String[] options = {"1", "2", "3", "4", "5", "6"};


    DiceGameView(){

        diceGame = new JFrame();
        betArea = new JPanel();
        playerInfo = new JPanel();
        userChoice = new JComboBox<String>(options);
        playerCurrency = new JLabel();
        playerName = new JLabel();

        betButton.setFont(new Font("Poor Richard",Font.BOLD,20));
        menu.setFont(new Font("Poor Richard",Font.BOLD,20));
        betAmount.setFont(new Font("Poor Richard",Font.BOLD,20));
        userChoice.setFont(new Font("Poor Richard",Font.BOLD,20));
        playerName.setFont(new Font("Poor Richard",Font.BOLD,20));
        playerCurrency.setFont(new Font("Poor Richard",Font.BOLD,20));

        diceGame.add(betArea,BorderLayout.SOUTH);
        diceGame.add(playerInfo,BorderLayout.WEST);
        diceGame.add(menu, BorderLayout.EAST);


        betArea.add(betAmount);
        betArea.add(betButton);
        betArea.add(userChoice);


        playerInfo.add(playerName,FlowLayout.LEFT);
        playerInfo.add(playerCurrency,FlowLayout.CENTER);


        diceGame.setSize(600,600);
        diceGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes view and client connection

        diceGame.setVisible(false);

    }


    void setBetButtonListener(ActionListener aL){
        betButton.addActionListener(aL);
    }

    void setMenuButtonListener(ActionListener aL) { menu.addActionListener(aL); }


    public void setPlayerName(String username){
        playerName.setText(username);
    }
    public String getPlayerName(){
        return  playerName.getText().toString();
    }
    public String getClientText(){

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
