import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView extends User {


    protected JFrame pirateGame;
    private JComboBox<String> userChoice;
    private JPanel betArea;
    private JPanel playerInfo;
    private JLabel playerName;
    private JLabel playerCurrency;
    private JButton betButton = new JButton("Bet");
    private JButton menu = new JButton("Menu");
    private JTextField betAmount = new JTextField(9);
    private String[] options = {"Options","Heads","Tails"};

    GameView(){

        pirateGame = new JFrame();
        betArea = new JPanel();
        playerInfo = new JPanel();
        userChoice = new JComboBox<String>(options);
        playerCurrency = new JLabel();
        playerName = new JLabel();


        pirateGame.add(betArea,BorderLayout.SOUTH);
        pirateGame.add(playerInfo,BorderLayout.WEST);
        pirateGame.add(menu, BorderLayout.EAST);


        betArea.add(betAmount);
        betArea.add(betButton);
        betArea.add(userChoice);


        playerInfo.add(playerName,FlowLayout.LEFT);
        playerInfo.add(playerCurrency,FlowLayout.CENTER);




        pirateGame.setSize(400,400);
        pirateGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes view and client connection

        pirateGame.setVisible(false);

    }


    void setBetButtonListener(ActionListener aL){
        betButton.addActionListener(aL);
    }//listener for button

    void setMenuButtonListener(ActionListener aL) { menu.addActionListener(aL); }

    void sendServCurrency(){

    }

    void getServCurrency(){

    }

    public void setPlayerName(String username){ //TODO figure out how to set this text to the player name
        playerName.setText(username);
    }
   public String getClientText(){//gets input text from user

        return betAmount.getText();
    }


}
