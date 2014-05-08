import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Game UI
 */
public class GameUI extends JFrame {

    private GameBoard gameBoard;

    private List<JButton> buttonList;

    public GameUI() {
        gameBoard = new GameBoard();

        this.setVisible(true);
        this.setSize(400, 400);
        this.setTitle("TicTacToe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        final JLabel currentPlayerLabel = new JLabel("Player " + gameBoard.getCurrentPlayer(), SwingConstants.CENTER);
        currentPlayerLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        gamePanel.add(currentPlayerLabel, BorderLayout.PAGE_START);

        JPanel board = new JPanel(new GridLayout(3, 3));
        buttonList = new ArrayList<JButton>();
        for (int buttonRowIndex = 0; buttonRowIndex < 3; buttonRowIndex++) {
            for (int buttonColumnIndex = 0; buttonColumnIndex < 3; buttonColumnIndex++) {
                final JButton button = new JButton();
                button.setName(String.valueOf(buttonRowIndex) + "," + String.valueOf(buttonColumnIndex));
                button.setFont(new Font("Serif", Font.BOLD, 60));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rowIndex = Integer.valueOf(button.getName().split(",")[0]);
                        int columnIndex = Integer.valueOf(button.getName().split(",")[1]);
                        if (gameBoard.getCurrentPlayer() == Player.A) {
                            button.setText("O");
                        } else {
                            button.setText("X");
                        }
                        button.setEnabled(false);
                        if (gameBoard.move(rowIndex, columnIndex)) {
                            JOptionPane.showMessageDialog(null, "Player " + gameBoard.getCurrentPlayer() + " Wins!");
                            // Reset Game board
                            gameBoard = new GameBoard();
                            for (JButton b : buttonList) {
                                b.setEnabled(true);
                                b.setText("");
                            }
                            currentPlayerLabel.setText("Player " + gameBoard.getCurrentPlayer());
                        } else {
                            gameBoard.switchPlayer();
                            currentPlayerLabel.setText("Player " + gameBoard.getCurrentPlayer());
                        }
                    }
                });
                board.add(button);
                buttonList.add(button);
            }
        }

        gamePanel.add(board, BorderLayout.CENTER);

        this.add(gamePanel);
    }
}
