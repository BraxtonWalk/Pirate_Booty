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
        scoreboard.setFont(new Font("Poor Richard",Font.BOLD,20));
        coinGame = new JButton("Coin Flip");
        coinGame.setFont(new Font("Poor Richard",Font.BOLD,20));
        diceGame = new JButton("Dice Roll");
        diceGame.setFont(new Font("Poor Richard",Font.BOLD,20));
        exit = new JButton("Exit");
        exit.setFont(new Font("Poor Richard",Font.BOLD,20));

        buttons.setLayout(new GridLayout(1,4));
        buttons.add(scoreboard);
        buttons.add(coinGame);
        buttons.add(diceGame);
        buttons.add(exit);
        buttons.setPreferredSize(new Dimension(200,125));

        gameMenu.add(buttons, BorderLayout.SOUTH);


        gameMenu.setSize(600,600);
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
