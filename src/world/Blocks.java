package world;

import java.awt.Graphics;

import graphs.Sprites;

public class Blocks extends Box {
    
    private String id;
    public static int blocksCount=0;

    public Blocks(int x,int y) {
        super(x,y,32,32);

        blocksCount++;
        this.id="BLOCK-"+blocksCount;
    }

    public void render(Graphics g){
        g.drawImage(Sprites.tile_wall, x, y, 32, 32, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
