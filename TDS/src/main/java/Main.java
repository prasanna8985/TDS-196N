import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bank Withdrawal System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JLabel amountLabel = new JLabel("Amount to withdraw");
        amountLabel.setBounds(10, 110, 150, 25);
        panel.add(amountLabel);

        JTextField amountText = new JTextField(20);
        amountText.setBounds(160, 110, 165, 25);
        panel.add(amountText);

        JCheckBox taxCheckBox = new JCheckBox("Filed Tax Returns for 3 Years");
        taxCheckBox.setBounds(10, 140, 250, 25);
        panel.add(taxCheckBox);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 170, 100, 25);
        panel.add(withdrawButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                authenticate(username, password);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                double amount = Double.parseDouble(amountText.getText());
                boolean filedTax = taxCheckBox.isSelected();
                withdraw(username, password, amount, filedTax);
            }
        });
    }

    private static void authenticate(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/login";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User authenticatedUser = restTemplate.postForObject(url, user, User.class);
        // Handle authentication response
    }

    private static void withdraw(String username, String password, double amount, boolean filedTax) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/withdraw?amount=" + amount + "&filedTax=" + filedTax + "&username=" + username + "&password=" + password;
        String response = restTemplate.postForObject(url, null, String.class);
        // Handle withdrawal response
    }
}
