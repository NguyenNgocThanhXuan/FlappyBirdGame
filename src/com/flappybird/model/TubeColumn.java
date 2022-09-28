
package com.flappybird.model;

import com.flappybird.view.Window;
import java.awt.Graphics2D;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TubeColumn {

    private int base = Window.HEIGHT - 60;

    private List<Tube> tubes;
    private Random random;
    private int points = 0;
    private int speed = 5;
    private int changeSpeed = speed;

    public TubeColumn() {
        tubes = new ArrayList<>();
        random = new Random();
        initTubes();
    }

    private void initTubes() {
        int last = base;
        //random ra vi tri cot khoang cach o giua
        int randWay = random.nextInt(10);
        //chia man hinh thanh 20 khoang
        for (int i = 0; i < 20; i++) {
            Tube tempTube = new Tube(800, last);
//            System.out.println("\nVi tri i thu:"+i);
//            System.out.println("ranWay"+ randWay);
//            System.out.println("Vi tri cot "+last);
            tempTube.setDx(speed);
//            System.out.println(tempTube.getDx());
            last = tempTube.getY() - tempTube.getHeight();
//            System.out.println("last sau do: "+ last);
            //kiem tra xem khoảng i co nam ngoai vi tri da random k
            //neu nam ngoai thi them i
            if (i < randWay || i > randWay + 4) {
                tubes.add(tempTube); 
//                System.out.println("Cot duoc them");
            }

        }
//        System.out.println("Cot duoc them: " +tubes.size());

    }

    public void tick() {
    	//kiem tra vi tri cot co nam ngoai man hinh chua
        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).tick();
            if (tubes.get(i).getX() < 0) {
                tubes.remove(tubes.get(i));
            }
        }
        // tang toc do neu diem = changespeed
        if (tubes.isEmpty()) {
            this.points += 1;
            if (changeSpeed == points) {
                this.speed += 1;
                changeSpeed += 5;
//                System.out.println(speed);
                
            }
            initTubes();
        }

    }
    //ve cot
    public void render(Graphics2D g, ImageObserver obs) {
        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).render(g, obs);
        }

    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
