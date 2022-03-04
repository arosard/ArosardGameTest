import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();//size the window to the preferred size and layouts of itss components

        window.setLocationRelativeTo(null); //to be displayed at the center of the screen
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}