import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private final JTextField playerWinsField, computerWinsField, tiesField;
    private final JTextArea gameLog;
    private final JLabel playerChoiceLabel, computerChoiceLabel;
    private final List<String> playerHistory = new ArrayList<>();
    private int playerWins = 0, computerWins = 0, ties = 0;
    private final Random random = new Random();
    private final Strategy[] strategies = {
            new RandomStrategy(),
            new LeastUsedStrategy(),
            new MostUsedStrategy(),
            new LastUsedStrategy()
    };
    private final CheatStrategy cheatStrategy = new CheatStrategy();

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main game panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setPreferredSize(new Dimension(200, 150));
        mainPanel.setBackground(Color.WHITE);

        JPanel playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerPanel.setPreferredSize(new Dimension(200, 150));
        playerPanel.setBackground(Color.WHITE);
        playerChoiceLabel = new JLabel(getResizedIcon("default.png"));
        playerPanel.add(playerChoiceLabel);

        JPanel computerPanel = new JPanel();
        computerPanel.setBorder(BorderFactory.createTitledBorder("Computer"));
        computerPanel.setPreferredSize(new Dimension(200, 150));
        computerPanel.setBackground(Color.WHITE);
        computerChoiceLabel = new JLabel(getResizedIcon("default.png"));
        computerPanel.add(computerChoiceLabel);

        mainPanel.add(playerPanel);
        mainPanel.add(computerPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Game stats panel
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Game Stats"));
        JLabel playerWinsLabel = new JLabel("Player Wins: ");
        JLabel computerWinsLabel = new JLabel("Computer Wins: ");
        JLabel tiesLabel = new JLabel("Ties: ");
        playerWinsField = new JTextField("0", 5);
        computerWinsField = new JTextField("0", 5);
        tiesField = new JTextField("0", 5);
        playerWinsField.setEditable(false);
        computerWinsField.setEditable(false);
        tiesField.setEditable(false);
        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);
        add(statsPanel, BorderLayout.EAST);

        // Container for button panel and game log
        JPanel bottomContainer = new JPanel(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));
        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton quitButton = new JButton("Quit");

        rockButton.addActionListener(e -> playRound("Rock"));
        paperButton.addActionListener(e -> playRound("Paper"));
        scissorsButton.addActionListener(e -> playRound("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);
        bottomContainer.add(buttonPanel, BorderLayout.NORTH);

        // Game Log Panel
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setPreferredSize(new Dimension(800, 150));
        logPanel.setBorder(BorderFactory.createTitledBorder("Game Log"));
        gameLog = new JTextArea(5, 50);
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);
        logPanel.add(scrollPane, BorderLayout.CENTER);
        bottomContainer.add(logPanel, BorderLayout.CENTER);

        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void playRound(String playerMove) {
        playerChoiceLabel.setIcon(getResizedIcon(playerMove + ".png"));

        Strategy strategy = (random.nextInt(10) == 0) ? cheatStrategy : strategies[random.nextInt(strategies.length)];
        String computerMove = strategy.determineMove(playerHistory);

        computerChoiceLabel.setIcon(getResizedIcon(computerMove + ".png"));
        playerHistory.add(playerMove);
        String result;

        if ((strategy instanceof CheatStrategy) ||
                (!playerMove.equals(computerMove) && !((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                        (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                        (playerMove.equals("Scissors") && computerMove.equals("Paper"))))) {
            result = computerMove + " beats " + playerMove + " (Computer wins " + strategy.getClass().getSimpleName() + ")";
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        } else {
            result = playerMove + " beats " + computerMove + " (Player wins " + strategy.getClass().getSimpleName() + ")";
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        }

        gameLog.append(result + "\n");
        JOptionPane.showMessageDialog(this, result, "Game Result", JOptionPane.INFORMATION_MESSAGE);
        clearImages();
    }

    private void clearImages() {
        playerChoiceLabel.setIcon(getResizedIcon("default.png"));
        computerChoiceLabel.setIcon(getResizedIcon("default.png"));
    }

    private ImageIcon getResizedIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
