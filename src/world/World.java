package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
    private int WIDTH,HEIGHT,SCALE;
   
    public static List<Blocks>blocos = new ArrayList<Blocks>();
    public static List<Bullet> bullets = new ArrayList<>();

    public World(int WIDTH, int HEIGHT, int SCALE){
       
        this.HEIGHT=HEIGHT;
        this.WIDTH=WIDTH;
        this.SCALE=SCALE;

        for(int xx=0;xx<(WIDTH/32);xx++){
            blocos.add(new Blocks(xx*32, 0));
        }
        for(int xx=0;xx<(WIDTH/32);xx++){
            blocos.add(new Blocks(xx*32, this.HEIGHT-32));
        }
        for(int yy=0;yy<(HEIGHT/32);yy++){
            blocos.add(new Blocks(0, yy*32));
        }
        for(int yy=0;yy<(HEIGHT/32);yy++){
            blocos.add(new Blocks(this.WIDTH-32, yy*32));
        }
        blocos.add(new Blocks(240,240));
        blocos.add(new Blocks(340,140));
    }

    public static boolean isFree(int x, int y, int w, int h){
        for(int i=0;i<blocos.size();i++){
            Blocks blocoAtual= blocos.get(i);
            if(blocoAtual.intersects(new Rectangle(x, y, w, h))){
                return false;
            } 
        }

        return true;
    }

    public void render(Graphics g){

        g.setColor(new Color(0,135,13));
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        for(int i=0;i<blocos.size();i++){
            blocos.get(i).render(g);
        }

        for(int i=0;i<bullets.size();i++){
            bullets.get(i).render(g);
        }
    }
     
    
    public void tick(){
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).tick();
        }
    }
}
