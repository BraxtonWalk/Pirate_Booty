
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameMenuView {

    private JFrame pirateGame;
    private JPanel betArea;
    private JButton betButton = new JButton("bet");
    protected JTextField betAmount = new JTextField(9);

    GameMenuView(){
        //makes frames
        pirateGame = new JFrame();
        betArea = new JPanel();
        //add buttons and text boxes to frame
        pirateGame.add(betArea,BorderLayout.SOUTH);
        betArea.add(betButton);
        betArea.add(betAmount);

        //sets frame size and makes visible
        pirateGame.setSize(400,400);
        pirateGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//closes view and client connection

        pirateGame.setVisible(false);

    }
    void setBetButtonListener(ActionListener aL){
        betButton.addActionListener(aL);
    }//listener for button



    public String getClientText(){//gets input text from user

        return betAmount.getText();
    }


}
