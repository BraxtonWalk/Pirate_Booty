import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameMenuView {

    protected JFrame gameMenu;
    private JPanel buttons;
    private JButton scoreboard;
    private JButton game;
    private JButton exit;
    private JTextField pirateJoke; //maybe we will use this----------------

    GameMenuView(){
        gameMenu = new JFrame();
        buttons = new JPanel();

        scoreboard = new JButton("Scoreboard");
        game = new JButton("Game");
        exit = new JButton("Exit");

        buttons.setLayout(new GridLayout(3,1));
        buttons.add(scoreboard);
        buttons.add(game);
        buttons.add(exit);

        gameMenu.add(buttons, BorderLayout.CENTER);


        gameMenu.setSize(400,400);
        gameMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameMenu.setVisible(false);
    }


    public void setScoreboardActionListener(ActionListener aL){
        scoreboard.addActionListener(aL);
    }

    public void setGameActionListener(ActionListener aL){
        game.addActionListener(aL);
    }

    public void setExitActionListener(ActionListener aL){
        exit.addActionListener(aL);
    }

}
