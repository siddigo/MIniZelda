package game;

import character.*;
import graphs.Sprites;
import world.World;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
    public static int WIDTH = 640, HEIGHT = 480;
    public static int SCALE = 3;

    public Player player;
    public World world;


    public  Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        new Sprites();

        world = new World(WIDTH, HEIGHT, SCALE);
        this.player=World.player;

    }

    public void tick(){
        player.tick();
        world.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        world.render(g);
        player.render(g);

        bs.show();
    }
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        JFrame frame = new JFrame();
        
        frame.add(game);
        frame.setTitle("Mini Zelda");

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();

    }

    @Override
    public void run() {
        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_S){
            player.shoot=true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            player.rigth=true;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            player.left=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            player.up=true;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            player.down=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            player.rigth=false;
       }
       else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            player.left=false;
       }
       if(e.getKeyCode()==KeyEvent.VK_UP){
            player.up=false;
       }
       else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            player.down=false;
       }
    }
}
