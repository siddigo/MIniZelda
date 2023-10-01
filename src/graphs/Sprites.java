package graphs;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
    
    public static BufferedImage spritesheet;

    public static BufferedImage player_Front;

    public Sprites() {
        
        try {
            spritesheet=ImageIO.read(getClass().getResource("/spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player_Front=Sprites.getSprite(0, 11, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int heigth){
        return spritesheet.getSubimage(x, y, width, heigth);
    }
}
