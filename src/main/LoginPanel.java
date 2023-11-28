package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    static final int DEFAULT_TILE_SIZE = 16;
    static final int SCALE = 2;
    static final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    static final int QTD_PER_ROW = 15;
    static final int SCREEN_WIDTH = TILE_SIZE * QTD_PER_ROW;
    static final int SCREEN_HEIGHT = TILE_SIZE * QTD_PER_ROW;
    static final int TOTAL_GAME_TILES = SCREEN_HEIGHT * SCREEN_WIDTH / QTD_PER_ROW;

    static final int DELAY = 150;
    Timer timer;
    GameFrame gf;

    public LoginPanel(GameFrame gf) {
        this.gf = gf;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust spacing as needed

        g.setFont(new Font("Ink Free", Font.BOLD, 60));
        String login = "Login";
        FontMetrics metrics = getFontMetrics(g.getFont());
        int x = ((SCREEN_WIDTH - metrics.stringWidth(login)) / 2);
        int y = SCREEN_HEIGHT / 2 - SCREEN_HEIGHT / 4;
        g.setColor(Color.gray);
        g.drawString(login, x + 5, y + 5);

        // titulo
        g.setColor(Color.white);
        g.drawString(login, x, y);
        g.setFont(new Font("Ink Free", Font.BOLD, 20));

        JTextField textField = new JTextField("Email");
        JPasswordField passwordField = new JPasswordField("Senha");
        Dimension textFieldSize = new Dimension(TILE_SIZE * 6, TILE_SIZE);
        textField.setPreferredSize(textFieldSize);
        passwordField.setPreferredSize(textFieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textField, gbc);

        gbc.gridy = 1;
        add(passwordField, gbc);

        Dimension tamBttn = new Dimension(TILE_SIZE * 6, TILE_SIZE);

        JButton bttn0 = new JButton("Login");
        bttn0.setPreferredSize(tamBttn);

        gbc.gridy = 2;
        add(bttn0, gbc);

        bttn0.addActionListener(e -> {
            System.out.println("Verificar login");
            User user = new User(textField.getText(), new String(passwordField.getPassword()));

            System.out.println(user.getEmail());
            System.out.println(user.getSenha());
            gf.changePage(1);
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
