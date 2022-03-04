import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTile("2D Adventure");

        window.setLocationRelativeTo(null); //to be displayed at the center of the screen
        window.setVisible(true);
    }
}