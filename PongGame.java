import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends JPanel implements ActionListener, KeyListener {
    private int ballX = 100, ballY = 100; // Ball coordinates
    private int ballSpeedX = 2, ballSpeedY = 2; // Ball movement speed
    private int paddle1Y = 150, paddle2Y = 150; // Paddle coordinates
    private int paddleSpeed = 50; // Paddle movement speed
    private int player1Score = 0, player2Score = 0; // Player scores

    public PongGame() {
        Timer timer = new Timer(5, this); // Timer for animation
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Draw the background
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 600);

        // Draw the paddles
        g.setColor(Color.WHITE);
        g.fillRect(50, paddle1Y, 10, 100);
        g.fillRect(740, paddle2Y, 10, 100);

        // Draw the ball
        g.fillOval(ballX, ballY, 20, 20);

        // Draw the scores
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Player 1: " + player1Score, 50, 30);
        g.drawString("Player 2: " + player2Score, 620, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update ball position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Ball collision with top and bottom walls
        if (ballY <= 0 || ballY >= 580) {
            ballSpeedY = -ballSpeedY;
        }

        // Ball collision with paddles
        if (ballX <= 60 && (ballY >= paddle1Y && ballY <= paddle1Y + 100) ||
                ballX >= 710 && (ballY >= paddle2Y && ballY <= paddle2Y + 100)) {
            ballSpeedX = -ballSpeedX;
        }

        // Ball out of bounds
        if (ballX <= 0) {
            player2Score++;
            ballX = 400;
            ballY = 300;
            ballSpeedX = 2;
            ballSpeedY = 2;
        }
        if (ballX >= 800) {
            player1Score++;
            ballX = 400;
            ballY = 300;
            ballSpeedX = 2;
            ballSpeedY = 2;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= paddleSpeed;
        } else if (keyCode == KeyEvent.VK_S && paddle1Y < 500) {
            paddle1Y += paddleSpeed;
        }
        if (keyCode == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= paddleSpeed;
        } else if (keyCode == KeyEvent.VK_DOWN && paddle2Y < 500) {
            paddle2Y += paddleSpeed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        PongGame pongGame = new PongGame();
        frame.add(pongGame);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
