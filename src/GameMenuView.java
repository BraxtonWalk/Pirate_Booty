import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameMenuView {

    protected JFrame gameMenu;
    private JPanel buttons;
    private JButton scoreboard;
    private JButton coinGame;
    private JButton diceGame;
    private JButton exit;
    private JTextField pirateJoke; //maybe we will use this----------------

    GameMenuView(){
        gameMenu = new JFrame();
        buttons = new JPanel();

        scoreboard = new JButton("Scoreboard");
        coinGame = new JButton("Coin Flip Game");
        diceGame = new JButton("Dice Roll Game");
        exit = new JButton("Exit");

        buttons.setLayout(new GridLayout(4,1));
        buttons.add(scoreboard);
        buttons.add(coinGame);
        buttons.add(diceGame);
        buttons.add(exit);

        gameMenu.add(buttons, BorderLayout.WEST);


        gameMenu.setSize(400,400);
        gameMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameMenu.setVisible(false);
    }


    public void setScoreboardActionListener(ActionListener aL){
        scoreboard.addActionListener(aL);
    }

    public void setCoinGameActionListener(ActionListener aL){
        coinGame.addActionListener(aL);
    }

    public void setDiceGameActionListener(ActionListener aL) { diceGame.addActionListener(aL);}

    public void setExitActionListener(ActionListener aL){
        exit.addActionListener(aL);
    }

}
