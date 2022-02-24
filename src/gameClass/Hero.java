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
    private Set<Character> direction = new LinkedHashSet<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private char lastkey = 'W';


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
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                direction.add('W');
                direction.remove('E');
                lastkey='W';
                right = false;
                left = true;
                break;
            case KeyEvent.VK_W:
                direction.add('N');
                direction.remove('S');
                lastkey='N';
                down = false;
                up = true;
                break;
            case KeyEvent.VK_D:
                direction.add('E');
                direction.remove('W');
                lastkey='E';
                left = false;
                right = true;
                break;
            case KeyEvent.VK_S:
                direction.add('S');
                direction.remove('N');
                lastkey='S';
                up = false;
                down = true;
                break;
        }
    }

    //抬起某个键，取消相应的方向
    public void minusDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                direction.remove('W');
                left = false;
                break;
            case KeyEvent.VK_W:
                direction.remove('N');
                up = false;
                break;
            case KeyEvent.VK_D:
                direction.remove('E');
                right = false;
                break;
            case KeyEvent.VK_S:
                direction.remove('S');
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
