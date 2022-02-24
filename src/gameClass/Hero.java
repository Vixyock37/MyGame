package gameClass;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ：VIxyock
 * @description：英雄主角对象
 */

public class Hero extends GameObject{
    boolean left,up,right,down;
    boolean live = true;
    private String direction= "W";
    private ArrayList<Bullet> bullets = new ArrayList<>();


    String s;
    public Hero(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
//        direction.add('W');
    }

    public void reload(){
        Bullet bullet = new Bullet(this.x, this.y);
        bullet.setDirection(direction);
        bullets.add(bullet);
    }

    public void shoot() {
        reload();
    }

    @Override
    public void drawSelf(Graphics g) {
        if(live) {
            g.drawImage(img, (int)x, (int)y, null);
            if(left) {
                x -= speed;
            }
            if(right) {
                x += speed;
            }
            if(up) {
                y -= speed;
            }
            if(down) {
                y += speed;
            }
        }else {

        }
    }

    //定义键盘与方向
    //按下键位时,走向某个方向
    public void addDirection(KeyEvent e) {
        if((left&&e.getKeyCode()==KeyEvent.VK_W)||(e.getKeyCode()==KeyEvent.VK_A&&up)) {direction="NW";up=true;left = true;}
        else if((up&&e.getKeyCode()==KeyEvent.VK_D)||(e.getKeyCode()==KeyEvent.VK_W&&right)) {direction="NE";right=true;up = true;}
        else if((right&&e.getKeyCode()==KeyEvent.VK_S)||(e.getKeyCode()==KeyEvent.VK_D&&down)) {direction="SE";right=true;down = true;}
        else if((down&&e.getKeyCode()==KeyEvent.VK_A)||(e.getKeyCode()==KeyEvent.VK_S&&left)) {direction="SW";down=true;left = true;}
        else if(e.getKeyCode()==KeyEvent.VK_A){direction="W";right = false;left = true;}
        else if(e.getKeyCode()==KeyEvent.VK_W){direction="N";down = false;up = true;}
        else if(e.getKeyCode()==KeyEvent.VK_D){direction="E";left = false;right = true;}
        else if(e.getKeyCode()==KeyEvent.VK_S){direction="S";up = false;down = true;}
//        switch(e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                direction.add('W');
//                direction.remove('E');
//                lastkey='W';
//                right = false;
//                left = true;
//                break;
//            case KeyEvent.VK_W:
//                direction.add('N');
//                direction.remove('S');
//                lastkey='N';
//                down = false;
//                up = true;
//                break;
//            case KeyEvent.VK_D:
//                direction.add('E');
//                direction.remove('W');
//                lastkey='E';
//                left = false;
//                right = true;
//                break;
//            case KeyEvent.VK_S:
//                direction.add('S');
//                direction.remove('N');
//                lastkey='S';
//                up = false;
//                down = true;
//                break;
//        }
    }

    //抬起某个键，取消相应的方向
    public void minusDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                direction.replace('W','x');
                left = false;
                break;
            case KeyEvent.VK_W:
                direction.replace('N','x');
                up = false;
                break;
            case KeyEvent.VK_D:
                direction.replace('E','x');
                right = false;
                break;
            case KeyEvent.VK_S:
                direction.replace('S','x');
                down = false;
                break;
        }
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
