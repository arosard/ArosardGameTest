import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class GamePanel extends JPanel{
    
    //screen settings
    final int originalTileSize=16; //16x16 tile
    final int scale = 3;//how much bigger it will be

    final int tileSize = originalTileSize*scale;//48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWindth = tileSize*maxScreenCol;//768 pix
    final int screenHeight = tileSize*maxScreenRow;//576 pix

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWindth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);//make the painting faster
    }
}
