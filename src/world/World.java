package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Enemy;
import character.Player;

public class World {
    private int WIDTH,HEIGHT,SCALE;
   
    public static List<Blocks>blocos = new ArrayList<Blocks>();
    public static List<Bullet> bullets = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static Player player = new Player(32, 32,32,32);


    public Random random = new Random();

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
        // Add random blocks
        for (int i = 0; i < 5; i++) {
            
            int xx=0;
            int yy=0;

            Blocks block = new Blocks(xx, yy);
            
            do {
                xx=random.nextInt(WIDTH-64)+32;
                yy=random.nextInt(HEIGHT-64)+32;

                block.setBounds(xx, yy, 32,32);

            } while (!World.isFree(block));

            blocos.add(block);
        }
        // Add random enemies
        for (int i = 0; i < 5; i++) {
            
            int xx=0;
            int yy=0;

            boolean vertical=random.nextBoolean();

            Enemy enemy = new Enemy(xx, yy, vertical);
            
            do {
                xx=random.nextInt(WIDTH-64)+32;
                yy=random.nextInt(HEIGHT-64)+32;

                enemy.setBounds(xx, yy, 32,32);

            } while (!World.isFree(enemy));

            enemies.add(enemy);
        }
    }

    public static boolean isFree(Box object){
        
        String idString=object.getId();

        if(player.getId().equals(idString)) {

            return true;
        }

        if(player.intersects(object)){

            return false;
        }       

        for(int i=0;i<blocos.size();i++){

            Blocks blocoAtual= blocos.get(i);

            String idAtual=blocoAtual.getId();

            if(idAtual.equals(idString)) {

                return true;
            }

            if(blocoAtual.intersects(object)){

                return false;
            } 
        }
        
        for(int i=0;i<enemies.size();i++){

            Enemy inimigoAtual= enemies.get(i);

            String idAtual=inimigoAtual.getId();

            if(idAtual.equals(idString)) {
                
                return true;
            }

            if(inimigoAtual.intersects(object)){
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
       
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).render(g);
        }
    }
     
    
    public void tick(){
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).tick();
        }
        
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).tick();
        }
    }
}
