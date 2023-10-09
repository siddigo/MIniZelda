package graphs;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
    
    public static BufferedImage spritesheet;

    public static BufferedImage player_Front[];

    public static BufferedImage tile_wall;

    public Sprites() {
        
        try {
            spritesheet=ImageIO.read(getClass().getResource("/spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player_Front= new BufferedImage[2];
        
        player_Front[0]=Sprites.getSprite(1, 11, 16, 16);
        player_Front[1]=Sprites.getSprite(17, 11, 16, 16);

        tile_wall=Sprites.getSprite(307, 154, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int heigth){
        return spritesheet.getSubimage(x, y, width, heigth);
    }
}
