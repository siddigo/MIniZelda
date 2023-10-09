package character;

import java.awt.Graphics;

import graphs.Sprites;
import world.World;
import world.Box;

public class Enemy extends Box{
    
    public int spd=4,shootCoolDown=0;

    public boolean rigth=false,up=false,down=false,left=false,shoot=false;
    public int dir=1;

    public int curAnimation=0;
    public int curFrames=0, targetFrames=15;

    public static int emeniesCount=0;
    private String id;

    public Enemy(int x, int y,boolean vertical){
        super(x,y,32,32);
        
        if (vertical){
            down=true;
        }
        else {
            rigth=true;
        }
        
        emeniesCount++;
        this.id="ENEMY-"+emeniesCount;

    }

    public void tick(){
        boolean movedVertical=false;
        boolean movedHorizontal=false;

        if(rigth){
            x+=spd;
            movedHorizontal=true;

            if (!World.isFree(this)) {
                x-=spd;
                movedHorizontal=false;
                left=true;
                rigth=false;

            }

        }else if (left) {
            x-=spd;
            movedHorizontal=true;
            
            if (!World.isFree(this)) {
                x+=spd;
                movedHorizontal=false;
                left=false;
                rigth=true;

            }
        }

        if(down){
            y+=spd;
            movedVertical=true;

            if (!World.isFree(this)) {
                y-=spd;
                movedVertical=false;
                up=true;
                down=false;

            }

        }else if (up) {
            y-=spd;
            movedVertical=true;

            if (!World.isFree(this)) {
                y+=spd;
                movedVertical=false;
                up=false;
                down=true;

            }
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

