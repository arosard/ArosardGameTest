import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    
    //screen settings
    final int originalTileSize=16; //16x16 tile
    final int scale = 3;//how much bigger it will be

    final int tileSize = originalTileSize*scale;//48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWindth = tileSize*maxScreenCol;//768 pix
    final int screenHeight = tileSize*maxScreenRow;//576 pix

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWindth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);//make the painting faster
        this.addKeyListener(keyH);//adding the keyListener
        this.setFocusable(true);//Focuses the panel on receiving key inputs
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread!=null){
            //1 update informations
            update();
            //2 draw the updated informations
            repaint();
        }        
    }

    public void update(){

    }
    //standard function in JPanel
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
