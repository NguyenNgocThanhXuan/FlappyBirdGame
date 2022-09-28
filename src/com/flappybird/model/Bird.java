
package com.flappybird.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import com.flappybird.model.proxy.ProxyImage;
import com.flappybird.view.Window;

public class Bird extends GameObject {

    private ProxyImage proxyImage;
    private Tube[] tube;
    public Bird(int x, int y){
        super(x, y);
        if(proxyImage == null) {
            proxyImage = new ProxyImage("/assets/bird.gif");
        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        tube = new Tube[1];
        tube[0] = new Tube(900, Window.HEIGHT - 50);
        this.dy = 4;
    }
    @Override
    public void tick() {
        if(dy < 10) {
            dy += 2;
        }
        this.y += dy;
        tube[0].tick();
        checkWindowBorder();
    }
    public void jump() {
        if(dy > 0) {
            dy = 0;
        }
        dy -= 20;
    }
    //kiem tra duong bao cua cua so.ch bird nằm trong man hinh
    private void checkWindowBorder() {
        if(this.y > Window.HEIGHT - 50) {
            this.y = Window.HEIGHT - 50;
        }
        if(this.y < 0) {
            this.y = 0;
        }
    }
    //vẽ bird lên mh,
    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);
        
        tube[0].render(g, obs);
    }
    
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
