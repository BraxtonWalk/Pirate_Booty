import javax.swing.*;
import java.awt.*;

public class BankruptView {
    protected JFrame bankrupt;
    private Icon bankruptImage;
    private JTextField broke;


    BankruptView(){
        bankrupt = new JFrame();
        broke = new JTextField("YOU BROKE BOY");
        broke.setFont(new Font("Poor Richard",Font.BOLD,80));
        bankrupt.add(broke, BorderLayout.CENTER);
        bankrupt.setSize(800,800);
        bankrupt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bankrupt.setVisible(false);
    }
}
