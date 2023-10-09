package character;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphs.Sprites;
import world.Bullet;
import world.World;

public class Player extends Rectangle{
    
    public int spd=4,shootCoolDown=0;

    public boolean rigth,up,down,left,shoot=false;
    public int dir=1;

    public int curAnimation=0;
    public int curFrames=0, targetFrames=15;

    public Player(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){
        boolean movedVertical=false;
        boolean movedHorizontal=false;

        if(rigth & World.isFree(x+spd, y,32,32)) {
            x+=spd;
            movedHorizontal=true;
            dir=1;
        }else if (left & World.isFree(x-spd, y,32,32)) {
            x-=spd;
            movedHorizontal=true;
            dir=-1;
        }

        if(up & World.isFree(x, y-spd,32,32)) {
            y-=spd;
            movedVertical=true;
        } else if(down & World.isFree(x, y+spd,32,32)){
            y+=spd;
            movedVertical=true;
        }

        if (movedVertical|movedHorizontal) {
            curFrames++;
            if(curFrames==targetFrames){

                curFrames=0;
                curAnimation++;

                if(curAnimation==Sprites.player_Front.length){
                    curAnimation=0;
                }   
            }
        }

        if (shootCoolDown==0) {
            if(shoot){
                World.bullets.add(new Bullet(x+16, y+16, dir));
                shootCoolDown=15;
            }
        }
        else {
            shootCoolDown--;        
        }
        
        shoot=false;

    }

    public void render(Graphics g) {
        /*g.setColor(Color.blue);
        g.fillRect(x,y,width,height);*/
        g.drawImage(Sprites.player_Front[curAnimation], x, y, 32, 32, null);

    }
}
