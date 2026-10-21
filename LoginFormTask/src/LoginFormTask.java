import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {
    // Todo: Add The Following Fields : username, password, role
    private String username;
    private String password;
    private String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // TODO: add getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class WelcomePage extends Frame {
    WelcomePage(User user) {
        super("Welcome");
        // TODO: build the page that says "Hello, username" and the role of the user
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        Label welcomeLabel = new Label("Hello, " + user.getUsername() + "! Role: " + user.getRole());
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(welcomeLabel);

        setSize(350, 200);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

public class LoginFormTask extends Frame {

    // TODO: add list of users to seed
    private List<User> usersList = new ArrayList<>();
    private int failedAttempts = 0; // Bonus counter

    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());
        Panel content = new Panel(new BorderLayout(10, 15));

        Label title = new Label("Welcome back", Label.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        content.add(title, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3, 2, 8, 8));
        form.add(new Label("Username:", Label.RIGHT));
        TextField username = new TextField(18);
        form.add(username);

        form.add(new Label("Password:", Label.RIGHT));
        TextField password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(new Label("Role:", Label.RIGHT));
        Choice role = new Choice();
        role.add("Student");
        role.add("TA");
        role.add("Professor");
        form.add(role);

        content.add(form, BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        Button cancel = new Button("Cancel");
        Button login = new Button("Login");
        buttons.add(cancel);
        buttons.add(login);
        content.add(buttons, BorderLayout.SOUTH);

        Label messageLabel = new Label("", Label.CENTER);
        messageLabel.setForeground(Color.RED);

        Panel mainContainer = new Panel(new BorderLayout());
        mainContainer.add(content, BorderLayout.CENTER);
        mainContainer.add(messageLabel, BorderLayout.SOUTH);

        add(mainContainer);

        login.addActionListener(e -> {
            // TODO: the login logic
            String uName = username.getText().trim();
            String uPass = password.getText().trim();
            String uRole = role.getSelectedItem();

            if (uName.isEmpty() || uPass.isEmpty()) {
                messageLabel.setText("Please fill in all fields!");
                return;
            }

            User foundUser = findUser(uName);
            if (foundUser == null || !foundUser.getPassword().equals(uPass) || !foundUser.getRole().equalsIgnoreCase(uRole)) {
                failedAttempts++;
                if (failedAttempts >= 3) {
                    login.setEnabled(false);
                    messageLabel.setText("Too many failed attempts. Login disabled.");
                } else {
                    messageLabel.setText("Invalid credentials! Attempt " + failedAttempts + " of 3.");
                }
            } else {
                messageLabel.setText("");
                new WelcomePage(foundUser).setVisible(true);
                dispose();
            }
        });

        cancel.addActionListener(e -> {
            // TODO: close the window
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(420, 360);
        setLocationRelativeTo(null);
    }

    private void loadUsers() {
        // TODO: add at least 3 users to the list one for each role
        usersList.add(new User("student1", "1234", "Student"));
        usersList.add(new User("ta1", "1234", "TA"));
        usersList.add(new User("prof1", "1234", "Professor"));
    }

    private User findUser(String name) {
        // TODO: search the list for the user
        for (User u : usersList) {
            if (u.getUsername().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);
    }
}