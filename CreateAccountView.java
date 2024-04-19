
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccountView {

    protected JFrame createAccount;
    private JButton createButton;
    private JButton loginMenu;
    protected JTextField username;
    protected JTextField password;
    private JPanel userInput;


    CreateAccountView(){
        createAccount = new JFrame();
        userInput = new JPanel();

        createButton = new JButton("Create Account");
        loginMenu = new JButton("Login Menu");

        userInput.setLayout(new GridLayout(3,2));
        username = new JTextField(15); //creating the text fields for the name and age
        password = new JTextField(15);
        userInput.add(new JLabel("Username:"));
        userInput.add(username);
        userInput.add(new JLabel("Password:"));
        userInput.add(password);
        userInput.add(createButton);
        userInput.add(loginMenu);


        createAccount.add(userInput, BorderLayout.CENTER);

        createAccount.setSize(400,400);
        createAccount.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createAccount.setVisible(false);
    }

    public void setCreateButtonActionListener(ActionListener aL){
        createButton.addActionListener(aL);
    }

    public void setLoginMenuButtonActionListener(ActionListener aL){
        loginMenu.addActionListener(aL);
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }
}
