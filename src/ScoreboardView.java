import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScoreboardView {

    protected JFrame scoreboard;
    private JPanel panel;
    private JButton menu;
    private JList top3;

    ScoreboardView(){
        scoreboard = new JFrame();
        panel = new JPanel();
        menu = new JButton("Menu");
        top3 = new JList<String>();

        panel.setLayout(new GridLayout(2,1));
        panel.add(top3);
        panel.add(menu);

        scoreboard.add(panel, BorderLayout.CENTER);


        scoreboard.setSize(400,400);
        scoreboard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scoreboard.setVisible(false);
    }

    public void setMenuActionListener(ActionListener aL) {
        menu.addActionListener(aL);
    }

    public void setTop3(JList top3) {
        this.top3 = top3;
    }
}
