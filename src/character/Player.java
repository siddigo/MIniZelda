package character;

import java.awt.Graphics;

import graphs.Sprites;
import world.Bullet;
import world.World;
import world.Box;

public class Player extends Box{
    
    public int spd=4,shootCoolDown=0;

    public boolean rigth,up,down,left,shoot=false;
    public int dir=1;

    public int curAnimation=0;
    public int curFrames=0, targetFrames=15;

    private String id="PLAYER";

    public Player(int x, int y, int w, int h){
        super(x,y,w,h);
    }

    public void tick(){
        boolean movedVertical=false;
        boolean movedHorizontal=false;

        if(rigth & World.isFree(this)) {
            x+=spd;
            movedHorizontal=true;
            dir=1;
        }else if (left & World.isFree(this)) {
            x-=spd;
            movedHorizontal=true;
            dir=-1;
        }

        if(up & World.isFree(this)) {
            y-=spd;
            movedVertical=true;
        } else if(down & World.isFree(this)){
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
