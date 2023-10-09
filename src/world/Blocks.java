package world;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphs.Sprites;

public class Blocks extends Rectangle {
    
public Blocks(int x,int y) {
    super(x,y,32,32);
}

public void render(Graphics g){
    g.drawImage(Sprites.tile_wall, x, y, 32, 32, null);
}

}
