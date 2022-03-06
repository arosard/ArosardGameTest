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

    //frames per second
    int FPS = 60;

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
    /*
    @Override
    public void run() {
        double drawInterval= 1000000000/FPS; //time between two frames in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread!=null){
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
    }*/
    //delta-accumulation game loop
    @Override
    public void run() {
        double drawInterval= (1000000000)/FPS; //time between two frames in nanoseconds
        double delta = 0;        
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += currentTime-lastTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer>=(1000000000)){
                System.out.println("FPS :"+drawCount);
                drawCount = 0;
                timer = 0;
            }
            lastTime=currentTime;
        }  
    }
    
    //update informations
    public void update(){
        if(keyH.upPressed==true){
            playerY-=playerSpeed;
        }else if(keyH.downPressed==true){
            playerY+=playerSpeed;
        }else if(keyH.leftPressed==true){
            playerX-=playerSpeed;
        }else if(keyH.rightPressed==true){
            playerX+=playerSpeed;
        }
    }
    //standard function in JPanel
    //draw the updated informations
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
