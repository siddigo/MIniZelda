package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphs.Sprites;
import world.World;

public class Player extends Rectangle{
    
    public int spd=4;

    public boolean rigth,up,down,left;

    public Player(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){
        if(rigth & World.isFree(x+spd, y)) {
            x+=spd;
        }else if (left & World.isFree(x-spd, y)) {
            x-=spd;
        }

        if(up & World.isFree(x, y-spd)) {
            y-=spd;
        } else if(down & World.isFree(x, y+spd)){
            y+=spd;
        }
    }

    public void render(Graphics g) {
        /*g.setColor(Color.blue);
        g.fillRect(x,y,width,height);*/
        g.drawImage(Sprites.player_Front, x, y, 32, 32, null);
    }
}
