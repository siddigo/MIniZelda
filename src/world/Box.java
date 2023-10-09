package world;

import java.awt.Rectangle;

public abstract class Box extends Rectangle {
        
    private String id;

    public Box(int x,int y,int w, int h) {
        super(x,y,w,h);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
