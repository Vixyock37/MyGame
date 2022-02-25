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

public class Hero extends GameObject {
    boolean left, up, right, down;
    boolean live = true;
    boolean machineGun = false, rpg = false, skates = false, immortal = false;
    private String direction = "W";
    private ArrayList<HeroBullet> bullets = new ArrayList<>();
    private int shooting_speed;

    String s;

    public Hero(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.shooting_speed = 1000;
//        direction.add('W');
    }

    public void reload() {
        HeroBullet bullet = new HeroBullet(this.x + 20, this.y + 20);
        bullet.setDirection(direction);
        if (rpg) bullet.width = bullet.height = 80;
        if (machineGun) this.setShooting_speed(100);
        else this.setShooting_speed(1000);
        bullets.add(bullet);
    }

    public int shoot() {
        reload();
        return this.shooting_speed;
    }

    @Override
    public void drawSelf(Graphics g) {
        if (skates) this.speed = 7;
        else this.speed = 3;
        if(immortal)
            this.width=this.height=0;
        else {
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
        if (live) {
            g.drawImage(img, (int) x, (int) y, null);
            if (left) {
                x -= speed;
            }
            if (right) {
                x += speed;
            }
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }
        } else {

        }
    }

    //定义键盘与方向
    //按下键位时,走向某个方向
    public void addDirection(KeyEvent e) {
        if ((left && e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_A && up)) {
            direction = "NW";
            up = true;
            left = true;
        } else if ((up && e.getKeyCode() == KeyEvent.VK_D) || (e.getKeyCode() == KeyEvent.VK_W && right)) {
            direction = "NE";
            right = true;
            up = true;
        } else if ((right && e.getKeyCode() == KeyEvent.VK_S) || (e.getKeyCode() == KeyEvent.VK_D && down)) {
            direction = "SE";
            right = true;
            down = true;
        } else if ((down && e.getKeyCode() == KeyEvent.VK_A) || (e.getKeyCode() == KeyEvent.VK_S && left)) {
            direction = "SW";
            down = true;
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            direction = "W";
            right = false;
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            direction = "N";
            down = false;
            up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            direction = "E";
            left = false;
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            direction = "S";
            up = false;
            down = true;
        }
    }

    //抬起某个键，取消相应的方向
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                direction.replace('W', 'x');
                left = false;
                break;
            case KeyEvent.VK_W:
                direction.replace('N', 'x');
                up = false;
                break;
            case KeyEvent.VK_D:
                direction.replace('E', 'x');
                right = false;
                break;
            case KeyEvent.VK_S:
                direction.replace('S', 'x');
                down = false;
                break;
            case KeyEvent.VK_R:
                this.changeRPG();
                break;
            case KeyEvent.VK_M:
                this.changeMachineGun_();
                break;
            case KeyEvent.VK_F:
                this.changeSkates();
                break;
            case KeyEvent.VK_SPACE:
                this.changeImmortal();
                break;
        }
    }

    public void setBullets(ArrayList<HeroBullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<HeroBullet> getBullets() {
        return bullets;
    }

    public int getShooting_speed() {
        return shooting_speed;
    }

    public void setShooting_speed(int shooting_speed) {
        this.shooting_speed = shooting_speed;
    }

    public void changeRPG() {
        rpg = !rpg;
    }

    public void changeMachineGun_() {
        machineGun = !machineGun;
    }

    public void changeSkates() { skates = !skates;  }

    public void changeImmortal() { immortal = !immortal;    }

}
