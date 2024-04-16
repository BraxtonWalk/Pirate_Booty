import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginAccountView {

    protected JFrame loginAccount;
    private JButton loginButton;
    private JButton createAccountMenu;
    protected JTextField username;
    protected JTextField password;
    private JPanel userInput;

    LoginAccountView(){
        loginAccount = new JFrame();
        userInput = new JPanel();
        loginButton = new JButton("Login");
        createAccountMenu = new JButton("Create Account Menu");

        userInput.setLayout(new GridLayout(2,2));
        username = new JTextField(15); //creating the text fields for the name and age
        password = new JTextField(15);
        userInput.add(new JLabel("Username:"));
        userInput.add(username);
        userInput.add(new JLabel("Password:"));
        userInput.add(password);

        loginAccount.add(loginButton,BorderLayout.SOUTH);
        loginAccount.add(createAccountMenu, BorderLayout.NORTH);
        loginAccount.add(userInput, BorderLayout.CENTER);

        loginAccount.setSize(400,400);
        loginAccount.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginAccount.setVisible(true);
    }

    public void setLoginButtonActionListener(ActionListener aL){
        loginButton.addActionListener(aL);
    }

    public void setCreateAccountMenuActionListener(ActionListener aL){
        createAccountMenu.addActionListener(aL);
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
