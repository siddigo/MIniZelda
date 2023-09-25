package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
    
    public int spd=4;

    public boolean rigth,up,down,left;

    public Player(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){
        if(rigth) {
            x+=spd;
        }else if (left) {
            x-=spd;
        }

        if(up) {
            y-=spd;
        } else if(down){
            y+=spd;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y,width,height);
    }
}
