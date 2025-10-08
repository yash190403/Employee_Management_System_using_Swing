package ui;


import DaoFile.UserDao;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("User Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(userLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(passLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginBtn = new JButton("Login");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(loginBtn, gbc);

        JButton registerBtn = new JButton("Go to Register");
        gbc.gridy = 3;
        add(registerBtn, gbc);

        loginBtn.addActionListener(e -> authenticateUser());
        registerBtn.addActionListener(e -> {
            dispose();
            new Registration().setVisible(true);
        });
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        UserDao dao = new UserDao();
        boolean valid = dao.authenticateUser(username, password);

        if (valid) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new SwingUI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}

