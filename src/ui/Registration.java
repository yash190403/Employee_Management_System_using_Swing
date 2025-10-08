package ui;



import DaoFile.UserDao;
import pck1.User;
import javax.swing.*;
import java.awt.*;

public class Registration extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Registration() {
        setTitle("User Registration");
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

        JButton registerBtn = new JButton("Register");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(registerBtn, gbc);

        JButton loginBtn = new JButton("Go to Login");
        gbc.gridy = 3;
        add(loginBtn, gbc);

        registerBtn.addActionListener(e -> registerUser());
        loginBtn.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        UserDao dao = new UserDao();
        boolean success = dao.registerUser(new User(username, password));

        if (success) {
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            dispose();
            new Login().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed! Username may already exist.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registration().setVisible(true));
    }
}
